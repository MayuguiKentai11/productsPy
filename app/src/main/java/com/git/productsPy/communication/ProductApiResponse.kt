package com.git.productsPy.communication

import com.git.productsPy.models.Product

class ProductResponse(
    private var id : Int,
    private var title : String,
    private var price: Double,
    private var image : String
){
    fun toProduct() : Product{
        return Product(
            id = id,
            title = title,
            price = price,
            image = image
        )
    }
}