package com.git.productsPy.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.git.myapplication.R

class MainActivity : AppCompatActivity() {

    private lateinit var btProducts : Button

    private lateinit var btFavorites : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main) // important

        btProducts = findViewById(R.id.btProducts)

        btFavorites = findViewById(R.id.btFavorites)

        btProducts.setOnClickListener{
            val intent = Intent(this, ProductActivity::class.java)

            startActivity(intent)
        }

        btFavorites.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)

            startActivity(intent)
        }

    }
}