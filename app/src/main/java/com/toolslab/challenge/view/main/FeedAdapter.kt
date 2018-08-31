package com.toolslab.challenge.view.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toolslab.challenge.R
import com.toolslab.challenge.base_repository.model.ItemViewModel
import kotlinx.android.synthetic.main.item_feed.view.*

internal class FeedAdapter(internal val viewModels: List<ItemViewModel>)
    : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    internal class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val title = view.item_feed_name
        internal val location = view.item_feed_description
        internal val thumbnail = view.item_feed_thumbnail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val viewModel = viewModels[position]
        viewHolder.apply {
            title.text = viewModel.title
            location.text = viewModel.location
            thumbnail.setImageURI(viewModel.thumbnailImageUrl)
        }
    }

    override fun getItemCount() = viewModels.size

}
