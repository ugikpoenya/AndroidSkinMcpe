package com.ugikpoenya.sampleappskinmcpe

import android.view.View
import com.squareup.picasso.Picasso
import com.ugikpoenya.sampleappskinmcpe.databinding.ItemGridViewsBinding
import com.ugikpoenya.skinmcpe.SkinModel
import com.xwray.groupie.viewbinding.BindableItem


class GridViewHolder(var skinModel: SkinModel) : BindableItem<ItemGridViewsBinding>() {
    override fun getLayout(): Int = R.layout.item_grid_views
    override fun initializeViewBinding(view: View): ItemGridViewsBinding = ItemGridViewsBinding.bind(view)
    override fun bind(viewHolder: ItemGridViewsBinding, position: Int) {
        Picasso.get()
            .load(skinModel.screenshot)
            .fit()
            .into(viewHolder.imageView)
    }
}
