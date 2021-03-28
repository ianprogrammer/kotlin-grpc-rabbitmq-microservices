package com.ian.cart.entity

import lombok.Builder
import javax.persistence.*

@Entity
@Table(name = "tb_order")
data class Order(
    val clientId: Long,
    val productId: Long,
    val quantity: Int,
    val amount: Double,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
)
