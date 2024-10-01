package com.git.productsPy.activities

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.git.productsPy.R
import com.git.productsPy.adapters.ProductAdapter
import com.git.productsPy.communication.ApiResponse
import com.git.productsPy.communication.ProductResponse
import com.git.productsPy.db.AppDatabase
import com.git.productsPy.models.Product
import com.git.productsPy.network.ProductApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductActivity : AppCompatActivity(), ProductAdapter.OnItemClickListener {

    private lateinit var btLoad: Button

    private lateinit var rvProducts : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_product)

        setSupportActionBar(findViewById(R.id.toolbar2))

        btLoad = findViewById(R.id.btLoad)

        rvProducts = findViewById(R.id.rvProducts)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)

    }


    override fun onResume() {
        super.onResume()

        btLoad.setOnClickListener {

            loadProducts {
                products ->
                rvProducts.adapter = ProductAdapter(products, this)
                rvProducts.layoutManager = LinearLayoutManager(this@ProductActivity)
            }
        }
    }


    private fun loadProducts(onComplete : (List<Product>) -> Unit){
        // Retrofit is the HttpClient for Android and Java
        val retrofit = Retrofit.Builder().baseUrl("https://fakestoreapi.com/").addConverterFactory(
            GsonConverterFactory.create()).build()

        val productApiService : ProductApiService = retrofit.create(ProductApiService::class.java)

        val request = productApiService.getProducts()

        request.enqueue(object : Callback<List<ProductResponse>>{
            override fun onResponse(call: Call<List<ProductResponse>>, response: Response<List<ProductResponse>>) {
                if(response.isSuccessful){
                    println("funciono!")

                    val apiResponse : List<ProductResponse> = response.body() !!

                    val productList = mutableListOf<Product>()

                    apiResponse?.forEach {
                        productList.add(it.toProduct())
                    }

                    onComplete(productList)
                }else{
                    println("Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<ProductResponse>>, t: Throwable) {
                println("Error: ${t.message}")
                t.printStackTrace()
            }
        })

    }

    override fun onItemClick(product: Product) {
        val dao = AppDatabase.getInstance(this).getDao()

        dao.insertProduct(product)

        Toast.makeText(this, product.title + "has been added to the favorite list!", Toast.LENGTH_SHORT).show()

    }

}