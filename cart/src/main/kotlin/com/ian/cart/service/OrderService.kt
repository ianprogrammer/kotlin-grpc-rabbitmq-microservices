package com.ian.cart.service

import com.ian.cart.OrderRequest
import com.ian.cart.dto.ProductDTO
import com.ian.cart.entity.Order
import com.ian.cart.integration.ProductServiceIntegration
import com.ian.cart.repository.OrderRepository
import javax.inject.Singleton


@Singleton
class OrderService(
    private val orderRepository: OrderRepository,
    private val productServiceIntegration: ProductServiceIntegration
) {
    fun createOrder(request: OrderRequest): Order {

        val productDTO: ProductDTO = productServiceIntegration
            .getProduct(request.product.productId)

        return orderRepository.save(
            Order(request.customer.id,request.product.productId,request.product.quantity,productDTO.price * request.product.quantity, 0)
        )
    }
}