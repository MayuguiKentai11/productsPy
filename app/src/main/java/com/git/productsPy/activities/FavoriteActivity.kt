package com.git.productsPy.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.git.productsPy.R
import com.git.productsPy.adapters.ProductAdapter
import com.git.productsPy.db.AppDatabase
import com.git.productsPy.models.Product

class FavoriteActivity : AppCompatActivity(), ProductAdapter.OnItemClickListener {

    private lateinit var rvFavorite: RecyclerView

    @SuppressLint("MissingInflateId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_favorite)

        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayShowHomeEnabled(true)

        rvFavorite = findViewById(R.id.rvFavorite)
    }

    override fun onResume() {
        super.onResume()
        loadProduct {
            product ->
            rvFavorite.adapter = ProductAdapter(product, this)
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }

    }

    private fun loadProduct(onComplete : (List<Product>)->Unit){

        val dao = AppDatabase.getInstance(this).getDao()

        onComplete(dao.getProducts())
    }


    override fun onItemClick(product: Product) {
        val dao = AppDatabase.getInstance(this).getDao()

        dao.deleteProduct(product)

        Toast.makeText(this, "Product" + product.title + " was deleted successfully from favorite list", Toast.LENGTH_SHORT).show()

        loadProduct {
            products ->
            rvFavorite.adapter = ProductAdapter(products, this)
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }


}