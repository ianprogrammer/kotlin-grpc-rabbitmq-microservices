package com.ian.cart.service

import com.ian.cart.PaymentRequest
import com.ian.cart.entity.Order
import com.ian.cart.integration.CustomerServiceIntegration
import com.ian.cart.rabbit.PaymentProcessorQueuePublisher
import org.slf4j.LoggerFactory
import javax.inject.Singleton


@Singleton
class PaymentService(
    private val customerServiceIntegration: CustomerServiceIntegration,
    private val paymentProcessorQueuePublisher: PaymentProcessorQueuePublisher
) {
    fun sendPaymentProcessor(order: Order) {
        val (id, name, _, cardNumber) = customerServiceIntegration
            .getCustomer(order.id)

        println("$id -- $name -- $cardNumber")


        paymentProcessorQueuePublisher.send(
            PaymentRequest.newBuilder()
                .setOrderId(order.id)
                .setClientId(id)
                .setClientName(name)
                .setCardNumber(cardNumber)
                .setAmount(order.amount)
                .build()
        )
        logger.info("Sending data to payment service")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(PaymentService::class.java)
    }
}
