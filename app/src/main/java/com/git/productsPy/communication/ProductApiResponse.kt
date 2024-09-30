package com.git.productsPy.communication

import android.app.Person
import com.git.productsPy.models.Product

class ProductResponse(
    private var id : Int?,
    private var title : String?,
    private var price: Double?,
    private var description : String?,
    private var image : String?
){

    fun toProduct() : Product{
        return Product(
            id = id,
            title = title,
            price = price,
            description = description,
            image = image
        )
    }
}