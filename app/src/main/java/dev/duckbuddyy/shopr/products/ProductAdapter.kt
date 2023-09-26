package dev.duckbuddyy.shopr.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.duckbuddyy.shopr.databinding.ItemProductListBinding
import dev.duckbuddyy.shopr.model.Product

class ProductAdapter(
    private val onItemClicked: (Product) -> Unit
) : ListAdapter<Product, ProductAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position) ?: return
        holder.bind(product)
    }

    inner class ViewHolder(
        private val binding: ItemProductListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) = binding.apply {
            root.setOnClickListener { onItemClicked(product) }
            tvName.text = product.name
            tvPrice.text = "$ ${product.price}"
        }
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ) = oldItem === newItem

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ) = oldItem.productId == newItem.productId
    }
}