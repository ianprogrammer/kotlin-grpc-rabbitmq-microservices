package com.ian.payment.rabbit

import com.ian.payment.PaymentRequest
import com.ian.payment.service.PaymentProcessorService
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitListener


@RabbitListener
class PaymentQueueConsumer(private val paymentProcessorService: PaymentProcessorService) {
    @Queue("payment.processor.solicitation")
    fun paymentData(message: PaymentRequest) {
        paymentProcessorService.processor(message)
    }
}