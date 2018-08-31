package com.toolslab.challenge.view.main

import com.nhaarman.mockito_kotlin.*
import com.toolslab.challenge.base_repository.exception.NoConnectionException
import com.toolslab.challenge.base_repository.model.ItemViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.util.concurrent.TimeUnit


class FeedPresenterTest {

    private val mockViewModels: List<ItemViewModel> = mock()
    private val mockView: FeedContract.View = mock()

    private val underTest = FeedPresenter()

    companion object {

        @BeforeClass
        @JvmStatic
        fun setUpRxSchedulers() {
            val immediate = object : Scheduler() {

                override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit) =
                        super.scheduleDirect(run, 0, unit)

                override fun createWorker() =
                        ExecutorScheduler.ExecutorWorker { it.run() }
            }

            RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
            RxJavaPlugins.setIoSchedulerHandler { immediate }
        }
    }

    @Before
    fun setUp() {
        underTest.compositeDisposable = mock()
        underTest.feedInteractor = mock()
    }

    @Test
    fun onBound() {
        whenever(underTest.feedInteractor.listItems()).thenReturn(Single.just(mockViewModels))

        underTest.bind(mockView)

        verify(mockView).setViewModels(mockViewModels)
        verify(underTest.compositeDisposable).add(any())
    }

    @Test
    fun onBoundWithNoConnectionException() {
        whenever(underTest.feedInteractor.listItems()).thenReturn(Single.error(NoConnectionException(Exception())))

        underTest.bind(mockView)

        verify(mockView).showNoConnectionError()
        verify(underTest.compositeDisposable).add(any())
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun onBoundWithDefaultException() {
        whenever(underTest.feedInteractor.listItems()).thenReturn(Single.error(Exception()))

        underTest.bind(mockView)

        verify(mockView).showDefaultError()
        verify(underTest.compositeDisposable).add(any())
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun onUnbound() {
        underTest.unbind(mockView)

        verify(underTest.compositeDisposable).clear()
        verifyZeroInteractions(underTest.feedInteractor)
        verifyZeroInteractions(mockView)
    }

}
