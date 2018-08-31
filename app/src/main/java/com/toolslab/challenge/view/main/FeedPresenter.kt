package com.toolslab.challenge.view.main

import com.toolslab.challenge.base_mvp.BasePresenter
import com.toolslab.challenge.base_repository.exception.NoConnectionException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class FeedPresenter @Inject constructor() :
        BasePresenter<FeedContract.View>(), FeedContract.Presenter {

    @Inject
    internal lateinit var compositeDisposable: CompositeDisposable

    @Inject
    internal lateinit var feedInteractor: FeedInteractor

    override fun onBound(view: FeedContract.View) {
        compositeDisposable.add(feedInteractor.listItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Timber.d("${it.size} items found")
                            view.setViewModels(it)
                        },
                        {
                            Timber.e(it)
                            when (it) {
                                is NoConnectionException -> view.showNoConnectionError()
                                else -> view.showDefaultError()
                            }
                        }
                )
        )
    }

    override fun onUnbound(view: FeedContract.View) {
        compositeDisposable.clear()
    }

    override fun onItemClicked(position: Int) {
        // TODO implement
    }

}
