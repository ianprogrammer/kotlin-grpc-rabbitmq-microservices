package com.ian.cart.integration

import com.ian.cart.config.ChannelFactory
import com.ian.cart.dto.ProductDTO
import com.ian.product.FindProductByIdRequest
import com.ian.product.ProductResponse
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class ProductServiceIntegration(private val channelFactory: ChannelFactory) {

    fun getProduct(productId: Long): ProductDTO {
        logger.info("Getting product data")
        val productResponse: ProductResponse = channelFactory.productStub()
            .findProductById(
                FindProductByIdRequest.newBuilder()
                    .setId(productId)
                    .build()
            )
        logger.info("Product Found!")

        return ProductDTO(productResponse.id, productResponse.name, productResponse.price)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ProductServiceIntegration::class.java)
    }


}