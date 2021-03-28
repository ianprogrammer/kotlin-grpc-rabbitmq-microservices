package com.ian.cart.config

import com.ian.customer.CustomerServiceGrpc
import com.ian.product.ProductServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Inject
import javax.inject.Singleton


@Factory
class ChannelFactory(
    @Inject
    @GrpcChannel("customer")
    private val customerChannel: ManagedChannel, @Inject
    @GrpcChannel("product")
    private val productChannel: ManagedChannel
) {


    @Singleton
    fun customerStub(): CustomerServiceGrpc.CustomerServiceBlockingStub {
        return CustomerServiceGrpc.newBlockingStub(customerChannel)
    }

    @Singleton
    fun productStub(): ProductServiceGrpc.ProductServiceBlockingStub {
        return ProductServiceGrpc.newBlockingStub(productChannel)
    }
}