package com.ian.payment.service

import com.ian.payment.PaymentRequest
import com.ian.payment.PaymentResponse
import com.ian.payment.rabbit.PaymentStatusPublisher
import org.slf4j.LoggerFactory
import javax.inject.Singleton


@Singleton
class PaymentProcessorService(val paymentStatusPublisher: PaymentStatusPublisher) {
    fun processor(paymentRequest: PaymentRequest) {
        logger.info("###### PROCESSANDO PAGAMENTO ######")
        paymentStatusPublisher.sendStatus(
            PaymentResponse.newBuilder()
                .setOrderId(paymentRequest.orderId)
                .setClientId(paymentRequest.clientId)
                .setStatus("PAGAMENTO APROVADO")
                .build()
        )
    }
    companion object {
        private val logger = LoggerFactory.getLogger(PaymentProcessorService::class.java)
    }
}
