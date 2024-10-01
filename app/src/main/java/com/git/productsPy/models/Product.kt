package com.git.productsPy.models

import android.icu.text.DecimalFormat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Product(
    @PrimaryKey
    val id : Int? = null,
    @ColumnInfo("title")
    val title : String?,
    @ColumnInfo("price")
    val price : Double?, // Or BigDecimal
    @ColumnInfo("image")
    val image : String?
)