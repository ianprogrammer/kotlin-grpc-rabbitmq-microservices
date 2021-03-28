package com.ian.cart.resource

import com.ian.cart.OrderRequest
import com.ian.cart.OrderResponse
import com.ian.cart.OrderServiceGrpc
import com.ian.cart.entity.Order
import com.ian.cart.service.OrderService
import com.ian.cart.service.PaymentService
import io.grpc.stub.StreamObserver
import io.micronaut.grpc.annotation.GrpcService
import io.reactivex.internal.util.QueueDrainHelper.request

@GrpcService
class OrderResource(
    private val paymentService: PaymentService,
    private val orderService: OrderService
) : OrderServiceGrpc.OrderServiceImplBase() {

    override fun order(request: OrderRequest, responseObserver: StreamObserver<OrderResponse>) {
        val order: Order = orderService.createOrder(request)

        paymentService.sendPaymentProcessor(order)

        responseObserver.onNext(
            OrderResponse.newBuilder()
                .setOrderConfirmation("The payment of your order is processing")
                .build()
        )
        responseObserver.onCompleted()
    }
}