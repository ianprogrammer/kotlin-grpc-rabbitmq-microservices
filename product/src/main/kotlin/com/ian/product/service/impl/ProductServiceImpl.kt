package com.ian.product.service.impl

import com.ian.product.dto.ProductDTO
import com.ian.product.entity.Product
import com.ian.product.exception.NotFoundException
import com.ian.product.repository.ProductRepository
import com.ian.product.service.contracts.ProductService
import org.slf4j.LoggerFactory
import java.util.function.Supplier
import javax.inject.Singleton


@Singleton
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    override fun findById(id: Long): ProductDTO {

        val (id1, name, price) = productRepository.findById(id)
            .orElseThrow { NotFoundException() }

        logger.info("Product not found")

        return ProductDTO(id1, name, price)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ProductServiceImpl::class.java)
    }
}
