package com.ian.customer.service.impl

import com.ian.customer.dto.CustomerDTO
import com.ian.customer.entity.Customer
import com.ian.customer.exceptions.NotFoundException
import com.ian.customer.repository.CustomerRepository
import com.ian.customer.service.contracts.CustomerService
import org.slf4j.LoggerFactory
import javax.inject.Singleton


@Singleton
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {
    override fun findById(id: Long): CustomerDTO {

        val (id1,name,email,cardNumber) = customerRepository.findById(id)
            .orElseThrow { NotFoundException() }
        logger.info("Customer found")

        return CustomerDTO(id1, name, email, cardNumber)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(CustomerServiceImpl::class.java)
    }
}
