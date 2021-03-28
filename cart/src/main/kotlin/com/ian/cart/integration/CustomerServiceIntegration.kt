package com.ian.cart.integration

import com.ian.cart.config.ChannelFactory
import com.ian.cart.dto.CustomerDTO
import com.ian.customer.CustomerResponse
import com.ian.customer.FindCustomerByIdRequest
import org.slf4j.LoggerFactory
import javax.inject.Singleton


@Singleton
class CustomerServiceIntegration(private val channelFactory: ChannelFactory) {

    fun getCustomer(customerId: Long): CustomerDTO {
        logger.info("Getting customer data")
        val customerResponse: CustomerResponse = channelFactory.customerStub()
            .findCustomerById(
                FindCustomerByIdRequest.newBuilder()
                    .setId(customerId)
                    .build()
            )
        logger.info("Customer found!")
        return CustomerDTO(
            customerResponse.id,
            customerResponse.name,
            customerResponse.email,
            customerResponse.cardNumber
        )
    }

    companion object {
        private val logger = LoggerFactory.getLogger(CustomerServiceIntegration::class.java)
    }
}