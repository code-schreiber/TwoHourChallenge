package com.toolslab.challenge.view.main

import android.view.View
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.challenge.base_repository.model.ItemViewModel
import kotlinx.android.synthetic.main.item_feed.view.*
import org.amshove.kluent.shouldEqual
import org.junit.Test

class FeedAdapterTest {

    private val viewModel1 = ItemViewModel("title1", "location1", "thumbnail1", "fullSize1")
    private val viewModel2 = ItemViewModel("title2", "location2", "thumbnail2", "fullSize2")
    private val viewModels = listOf(viewModel1, viewModel2)

    private val mockView: View = mock()
    private val mockNameTextView: TextView = mock()
    private val mockLocationTextView: TextView = mock()
    private val mockThumbnailDraweeView: SimpleDraweeView = mock()

    private val underTest = FeedAdapter(viewModels)

    @Test
    fun onBindViewHolder() {
        whenever(mockView.item_feed_name).thenReturn(mockNameTextView)
        whenever(mockView.item_feed_description).thenReturn(mockLocationTextView)
        whenever(mockView.item_feed_thumbnail).thenReturn(mockThumbnailDraweeView)
        val viewHolder = FeedAdapter.ViewHolder(mockView)

        underTest.onBindViewHolder(viewHolder, 1)

        verify(mockNameTextView).text = viewModel2.title
        verify(mockLocationTextView).text = viewModel2.location
        verify(mockThumbnailDraweeView).setImageURI(viewModel2.thumbnailImageUrl)
    }

    @Test
    fun getItemCount() {
        underTest.itemCount shouldEqual viewModels.size
    }

}
