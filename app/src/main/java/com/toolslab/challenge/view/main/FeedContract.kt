package com.toolslab.challenge.view.main

import com.toolslab.challenge.base_mvp.BaseView
import com.toolslab.challenge.base_mvp.MvpPresenter
import com.toolslab.challenge.base_repository.model.ItemViewModel

interface FeedContract {

    interface Presenter : MvpPresenter<View> {
        fun onItemClicked(position: Int)
    }

    interface View : BaseView {
        fun setViewModels(viewModels: List<ItemViewModel>)
    }

}
