package com.git.productsPy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.git.productsPy.R
import com.git.productsPy.models.Product
import com.squareup.picasso.Picasso

class ProductAdapter(private val products : List<Product>,
    private val clickListener : OnItemClickListener) : Adapter<ProductAdapter.ProductViewHolder>() {
            inner class ProductViewHolder(itemView : View) : ViewHolder(itemView){
                private val tvTitle : TextView = itemView.findViewById(R.id.tvTitleProduct)

                private val tvPrice : TextView = itemView.findViewById(R.id.tvPriceProduct)

                private val ivImage : ImageView = itemView.findViewById(R.id.ivLogoProduct)

                private val btLike : ImageButton = itemView.findViewById(R.id.btLike)

                fun bind(product : Product, clickListener: OnItemClickListener){
                    tvTitle.text = product.title

                    tvPrice.text = product.price.toString()

                    Picasso.get().load(product.image)
                        .into(ivImage)
                    btLike.setOnClickListener {
                        clickListener.onItemClick(product)
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_product, parent, false)

        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position] , clickListener)
    }

    interface OnItemClickListener{
        fun onItemClick(product: Product)
    }
}