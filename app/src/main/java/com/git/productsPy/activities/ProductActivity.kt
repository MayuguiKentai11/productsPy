package com.git.productsPy.activities

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.git.myapplication.R
import com.git.productsPy.adapters.ProductAdapter
import com.git.productsPy.communication.ApiResponse
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
        rvProducts.layoutManager = LinearLayoutManager(this@ProductActivity)

        supportActionBar?.setDisplayShowHomeEnabled(true)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onResume() {
        super.onResume()

        btLoad.setOnClickListener {

            loadProducts {
                products ->
                rvProducts.adapter = ProductAdapter(products, this)

            }
        }
    }


    private fun loadProducts(onComplete : (List<Product>)-> Unit){

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productApiService : ProductApiService = retrofit.create(ProductApiService::class.java)

        val request = productApiService.getProducts()


        request.enqueue(object : Callback<ApiResponse>{

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if(response.isSuccessful){
                    val productApi:ApiResponse = response.body()!!


                    val productList = mutableListOf<Product>()

                    productApi.results?.forEach{
                        productList.add(it.toProduct())
                    }

                    onComplete(productList)

                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })

    }


    // Calling from Adapter and executes a behavior depending of the emission context.
    override fun onItemClick(product: Product) {
        val dao = AppDatabase.getInstance(this).getDao()

        dao.insertProduct(product)

        Toast.makeText(this, "The product with name: " + product.title + "was successfully added to the favorite list!", Toast.LENGTH_SHORT).show()

    }

}