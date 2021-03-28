package com.ian.cart.config

import com.google.protobuf.InvalidProtocolBufferException
import com.ian.cart.PaymentResponse
import com.ian.cart.exception.SerializerDeserializerException
import io.micronaut.core.type.Argument
import io.micronaut.rabbitmq.bind.RabbitConsumerState
import io.micronaut.rabbitmq.intercept.MutableBasicProperties
import io.micronaut.rabbitmq.serdes.RabbitMessageSerDes
import javax.inject.Singleton


@Singleton
class PaymentResponseSerializerDeserializer : RabbitMessageSerDes<PaymentResponse> {
    override fun deserialize(
        consumerState: RabbitConsumerState,
        argument: Argument<PaymentResponse>
    ): PaymentResponse {
        return try {
            PaymentResponse.parseFrom(consumerState.body)
        } catch (e: InvalidProtocolBufferException) {
            throw SerializerDeserializerException(e.message)
        }
    }

    override fun serialize(data: PaymentResponse?, properties: MutableBasicProperties): ByteArray {
        if (data == null) {
            throw SerializerDeserializerException("Data can't be null")
        }
        return data.toByteArray()
    }

    override fun supports(type: Argument<PaymentResponse?>): Boolean {
        return type.type.isAssignableFrom(PaymentResponse::class.java)
    }
}