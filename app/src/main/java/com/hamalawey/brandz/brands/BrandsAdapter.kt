package com.hamalawey.brandz.brands

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hamalawey.brandz.R
import com.hamalawey.brandz.utils.overrideFonts
import com.hamalawey.domain.entities.brand.Item
import kotlinx.android.synthetic.main.brand_item.view.*
import kotlinx.android.synthetic.main.fragment_brand.view.*


class BrandsAdapter(private val font: String, private val color: Int, private val clickListener: (Int) -> Unit ) :
    ListAdapter<Item, BrandsAdapter.ViewHolder>(
        CategoryDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.brand_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), font, color, clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(
            item: Item,
            font: String,
            color: Int,
            clickListener: (Int) -> Unit
        ) {
            overrideFonts(itemView.context, itemView, font)
            itemView.item_name_textview.text = item.name
            itemView.promotion.text = item.promotion.sub_title

            val unwrappedDrawable = AppCompatResources.getDrawable(itemView.context, R.drawable.rounded_corners_gray_bg)
            val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable!!)
            DrawableCompat.setTint(wrappedDrawable, color)

            itemView.add_to_cart_btn.background = itemView.context.getDrawable(R.drawable.rounded_corners_gray_bg)
            if(item.promotion.title != null){
                itemView.promo_badge.isVisible = true
                itemView.promo_badge.text = item.promotion.title
                itemView.promo_badge.setBackgroundColor(color)
            } else{
                itemView.promo_badge.isVisible = false
            }
            itemView.item_price.text = item.price.currency + " " + item.price.amount
            Glide.with(itemView.context).load(item.thumbnail).into(itemView.item_image)
            itemView.item_image.setOnClickListener {
                clickListener(item.id)
            }
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return oldItem == newItem
        }
    }
}