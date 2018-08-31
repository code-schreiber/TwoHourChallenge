package com.toolslab.challenge.base_repository

import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.toolslab.challenge.base_network.model.Feed
import com.toolslab.challenge.base_repository.model.ItemViewModel
import io.reactivex.Single
import org.amshove.kluent.mock
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

class SpaceRepositoryTest {

    private val mockFeed: Feed = mock()
    private val mockItems: List<ItemViewModel> = mock()

    private val underTest = SpaceRepository()

    @Before
    fun setUp() {
        underTest.apiService = mock()
        underTest.errorHandler = mock()
        underTest.itemViewModelConverter = mock()
    }

    @Test
    fun listItems() {
        whenever(underTest.apiService.getFeed()).thenReturn(Single.just(mockFeed))
        whenever(underTest.itemViewModelConverter.convert(mockFeed)).thenReturn(mockItems)

        val result = underTest.listItems().blockingGet()

        result shouldEqual mockItems
        verifyZeroInteractions(underTest.errorHandler)
    }

    @Test
    fun listItemsWithError() {
        val exception = Exception()
        val handledException = Exception("a handled exception")
        whenever(underTest.apiService.getFeed()).thenReturn(Single.error(exception))
        whenever(underTest.errorHandler.handle<Feed>(exception)).thenReturn(Single.error(handledException))

        val testObserver = underTest.listItems().test()
        testObserver.awaitTerminalEvent()

        testObserver.values() shouldEqual emptyList()
        testObserver.errorCount() shouldEqual 1
        testObserver.errors()[0] shouldEqual handledException
        verifyZeroInteractions(underTest.itemViewModelConverter)
    }

}
