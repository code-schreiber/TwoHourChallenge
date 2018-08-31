package com.toolslab.challenge.view.main

import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.challenge.base_repository.model.ItemViewModel
import io.reactivex.Single
import org.amshove.kluent.mock
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

class FeedInteractorTest {

    private val mockItems: List<ItemViewModel> = mock()

    private val underTest = FeedInteractor()

    @Before
    fun setUp() {
        underTest.spaceRepository = mock()
    }

    @Test
    fun listItems() {
        whenever(underTest.spaceRepository.listItems()).thenReturn(Single.just(mockItems))

        val items = underTest.listItems().blockingGet()

        items shouldEqual mockItems
    }

}
