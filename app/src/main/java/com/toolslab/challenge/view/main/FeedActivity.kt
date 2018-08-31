package com.toolslab.challenge.view.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.toolslab.challenge.R
import com.toolslab.challenge.base_repository.model.ItemViewModel
import com.toolslab.challenge.view.base.BaseActivity
import javax.inject.Inject

class FeedActivity : BaseActivity(), FeedContract.View {

    @BindView(R.id.activity_feed_recycler_view)
    internal lateinit var recyclerView: RecyclerView

    @Inject
    internal lateinit var presenter: FeedContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        ButterKnife.bind(this)
        presenter.bind(this)
    }

    override fun onDestroy() {
        presenter.unbind(this)
        super.onDestroy()
    }

    override fun setViewModels(viewModels: List<ItemViewModel>) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FeedAdapter(viewModels)
    }

}
