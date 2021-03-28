package com.ian.customer.service.contracts

import com.ian.customer.dto.CustomerDTO


interface CustomerService {
    fun findById(id: Long): CustomerDTO
}
