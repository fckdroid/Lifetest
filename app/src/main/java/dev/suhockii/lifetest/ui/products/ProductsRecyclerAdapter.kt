package dev.suhockii.lifetest.ui.products

import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.bumptech.glide.Glide

import dev.suhockii.lifetest.R
import dev.suhockii.lifetest.model.Product
import dev.suhockii.lifetest.util.AppUtils
import kotlin.properties.Delegates

internal class ProductsRecyclerAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductsRecyclerAdapter.RecyclerViewHolder>() {

    private var onProductClickListener: OnProductClickListener by Delegates.notNull()

    init {
        onProductClickListener = object : OnProductClickListener {
            override fun onItemClick(view: View) =
                onProductClickListener.onItemClick(view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        view.setOnClickListener { clickedView -> onProductClickListener.onItemClick(clickedView) }

        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val product = products[position]
        holder.tvName.text = product.name
        holder.tvPrice.text = product.price.toString()

        val ivProduct = holder.ivProduct

        Glide.with(ivProduct.context)
            .load(product.imageUrl)
            .listener(AppUtils.getGlideListener(holder.progressBar))
            .into(ivProduct)

        ViewCompat.setTransitionName(ivProduct, product.id)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun getProductAt(clickedPos: Int): Product {
        return products[clickedPos]
    }

    fun setOnProductClickListener(onProductClickListener: OnProductClickListener): ProductsRecyclerAdapter {
        this.onProductClickListener = onProductClickListener
        return this
    }

    internal interface OnProductClickListener {
        fun onItemClick(view: View)
    }

    internal inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivProduct: ImageView = itemView.findViewById(R.id.item_iv_product)
        var tvName: TextView = itemView.findViewById(R.id.item_tv_name)
        var tvPrice: TextView = itemView.findViewById(R.id.item_tv_price)
        var progressBar: ProgressBar = itemView.findViewById(R.id.item_progressbar)
    }
}
