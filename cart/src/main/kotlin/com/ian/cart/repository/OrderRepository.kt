package com.ian.cart.repository

import com.ian.cart.entity.Order
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository


@Repository
interface OrderRepository : JpaRepository<Order, Long>
