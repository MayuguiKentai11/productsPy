package com.git.productsPy.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.git.productsPy.models.Product

@Dao
interface ProductDao {
    @Insert
    fun insertProduct(product : Product)

    @Query("SELECT* FROM Product")
    fun getProducts() : List<Product>

    @Delete
    fun deleteProduct(product: Product)

}