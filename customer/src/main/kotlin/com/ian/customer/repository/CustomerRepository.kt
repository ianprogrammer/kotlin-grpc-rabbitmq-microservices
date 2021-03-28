package com.ian.customer.repository

import com.ian.customer.entity.Customer
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository


@Repository
interface CustomerRepository : JpaRepository<Customer, Long>