package com.ian.cart.rabbit

import com.ian.cart.PaymentResponse
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitListener
import org.slf4j.LoggerFactory


@RabbitListener
class PaymentStatusQueueConsumer {
    @Queue("payment.status.cart.service")
    fun paymentData(paymentResponse: PaymentResponse) {
        logger.info("## Getting payment data ##")
        logger.info("Order Id: " + paymentResponse.getOrderId())
        logger.info("Order Status: " + paymentResponse.getStatus())
        logger.info("##############################################")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(PaymentStatusQueueConsumer::class.java)
    }
}