package com.ian.product.resource

import com.ian.product.FindProductByIdRequest
import com.ian.product.ProductResponse
import com.ian.product.ProductServiceGrpc
import com.ian.product.service.contracts.ProductService
import io.grpc.stub.StreamObserver
import io.micronaut.grpc.annotation.GrpcService


@GrpcService
class ProductResource(private val productService:ProductService) : ProductServiceGrpc.ProductServiceImplBase() {

    override fun findProductById(request: FindProductByIdRequest, responseObserver: StreamObserver<ProductResponse>) {
        println("ASDFASDFASDFSA")
        val (id, name, price) = productService.findById(request.id)
        responseObserver.onNext(
            ProductResponse.newBuilder()
                .setId(id)
                .setName(name)
                .setPrice(price)
                .build()
        )
        responseObserver.onCompleted()
    }
}