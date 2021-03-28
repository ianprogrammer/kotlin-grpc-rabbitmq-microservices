package com.ian.payment.rabbit

import com.ian.payment.PaymentResponse
import io.micronaut.rabbitmq.annotation.RabbitClient


@RabbitClient(value = "payment.status")
interface PaymentStatusPublisher {
    fun sendStatus(response: PaymentResponse)
}
