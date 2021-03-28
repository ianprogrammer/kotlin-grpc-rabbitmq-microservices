package com.ian.payment.config

import com.ian.payment.PaymentRequest
import com.ian.payment.exception.SerializerDeserializerException
import io.micronaut.core.type.Argument
import io.micronaut.rabbitmq.bind.RabbitConsumerState
import io.micronaut.rabbitmq.intercept.MutableBasicProperties
import io.micronaut.rabbitmq.serdes.RabbitMessageSerDes
import org.slf4j.LoggerFactory
import javax.inject.Singleton


@Singleton
class PaymentRequestSerializerDeserializer : RabbitMessageSerDes<PaymentRequest> {
    override fun deserialize(
        consumerState: RabbitConsumerState,
        argument: Argument<PaymentRequest>?
    ): PaymentRequest {
        runCatching {
            println("consumerState.body")
            return PaymentRequest.parseFrom(consumerState.body)
        }.onFailure {
            logger.error("Something went wrong on deserialize ${it.message}")
        }.getOrThrow()

    }

    override fun serialize(data: PaymentRequest?, properties: MutableBasicProperties): ByteArray {
        if(data == null){
            throw SerializerDeserializerException("Dados não podem ser nulos.");
        }
        return data!!.toByteArray()
    }

    override fun supports(type: Argument<PaymentRequest>): Boolean {
       return type.type.isAssignableFrom(PaymentRequest::class.java)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(PaymentRequestSerializerDeserializer::class.java)
    }

}