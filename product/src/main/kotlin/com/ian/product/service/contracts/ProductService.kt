package com.ian.product.service.contracts

import com.ian.product.dto.ProductDTO


interface ProductService {
    fun findById(id: Long): ProductDTO
}
