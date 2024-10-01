package com.git.productsPy.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.git.productsPy.models.Product

@Database(entities = [Product::class], version = 2)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getDao() : ProductDao

    companion object{
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "products_v3.db").allowMainThreadQueries().build()
            }

            return INSTANCE !!
        }
    }
}