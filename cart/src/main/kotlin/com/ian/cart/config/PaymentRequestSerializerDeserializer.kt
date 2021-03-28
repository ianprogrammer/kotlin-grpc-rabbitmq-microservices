package com.ian.cart.config

import com.google.protobuf.InvalidProtocolBufferException
import com.ian.cart.PaymentRequest
import com.ian.cart.exception.SerializerDeserializerException
import io.micronaut.core.type.Argument
import io.micronaut.rabbitmq.bind.RabbitConsumerState
import io.micronaut.rabbitmq.intercept.MutableBasicProperties
import io.micronaut.rabbitmq.serdes.RabbitMessageSerDes
import javax.inject.Singleton


@Singleton
class PaymentRequestSerializerDeserializer : RabbitMessageSerDes<PaymentRequest> {
    override fun deserialize(consumerState: RabbitConsumerState, argument: Argument<PaymentRequest>): PaymentRequest {
        return try {
            PaymentRequest.parseFrom(consumerState.body)
        } catch (e: InvalidProtocolBufferException) {
            throw SerializerDeserializerException(e.message)
        }
    }

    override fun serialize(data: PaymentRequest?, properties: MutableBasicProperties): ByteArray {
        if (data == null) {
            throw SerializerDeserializerException("Data can't be null")
        }
        return data.toByteArray()
    }

    override fun supports(type: Argument<PaymentRequest>): Boolean {
        return type.type.isAssignableFrom(PaymentRequest::class.java)
    }
}