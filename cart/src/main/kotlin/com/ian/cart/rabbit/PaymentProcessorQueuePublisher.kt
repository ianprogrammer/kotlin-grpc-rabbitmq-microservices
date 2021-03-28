package com.ian.cart.rabbit

import com.ian.cart.PaymentRequest
import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.RabbitClient


@RabbitClient(value = "payment.processor")
interface PaymentProcessorQueuePublisher {
    @Binding("payment.processor.solicitation")
    fun send(paymentRequest: PaymentRequest)
}
