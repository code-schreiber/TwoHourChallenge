package com.toolslab.challenge.view.main

import android.support.v7.widget.LinearLayoutManager
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.verify
import com.toolslab.challenge.base_repository.model.ItemViewModel
import org.amshove.kluent.mock
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test


class FeedActivityTest {

    private val viewModel1 = ItemViewModel("title1", "location1", "thumbnail1", "fullSize1")
    private val viewModel2 = ItemViewModel("title2", "location2", "thumbnail2", "fullSize2")
    private val viewModels = listOf(viewModel1, viewModel2)

    private val underTest = FeedActivity()

    @Before
    fun setUp() {
        underTest.recyclerView = mock()
    }

    @Test
    fun setViewModels() {
        val captor = argumentCaptor<FeedAdapter>()

        underTest.setViewModels(viewModels)

        verify(underTest.recyclerView).setHasFixedSize(true)
        verify(underTest.recyclerView).layoutManager = any<LinearLayoutManager>()
        verify(underTest.recyclerView).adapter = captor.capture()
        captor.firstValue.viewModels shouldEqual viewModels
    }

}
