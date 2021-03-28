package com.ian.customer.resource

import com.ian.customer.CustomerResponse
import com.ian.customer.CustomerServiceGrpc
import com.ian.customer.FindCustomerByIdRequest
import com.ian.customer.service.contracts.CustomerService
import io.grpc.stub.StreamObserver
import io.grpc.Status;
import io.micronaut.grpc.annotation.GrpcService

@GrpcService
class CustomerResource(private val customerService: CustomerService) : CustomerServiceGrpc.CustomerServiceImplBase() {

    override fun findCustomerById(
        request: FindCustomerByIdRequest,
        responseObserver: StreamObserver<CustomerResponse>
    ) {

        runCatching {
            val customer = customerService.findById(request.id)
            val response = CustomerResponse.newBuilder()
                .setId(customer.id)
                .setName(customer.name)
                .setEmail(customer.email)
                .setCardNumber(customer.cardNumber)
                .build()

            responseObserver.onNext(response)
            responseObserver.onCompleted()
        }.onFailure {
            responseObserver.onError(
                Status.NOT_FOUND.withDescription(it.message).asRuntimeException()
            );
        }.getOrThrow()
    }
}