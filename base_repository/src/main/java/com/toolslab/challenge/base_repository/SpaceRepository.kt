package com.toolslab.challenge.base_repository

import com.toolslab.challenge.base_network.service.ApiService
import com.toolslab.challenge.base_repository.converter.ItemViewModelConverter
import com.toolslab.challenge.base_repository.model.ItemViewModel
import io.reactivex.Single
import javax.inject.Inject

class SpaceRepository @Inject constructor() {

    @Inject
    internal lateinit var apiService: ApiService

    @Inject
    internal lateinit var errorHandler: ErrorHandler

    @Inject
    internal lateinit var itemViewModelConverter: ItemViewModelConverter

    fun listItems(): Single<List<ItemViewModel>> =
            apiService.getFeed()
                    .onErrorResumeNext {
                        errorHandler.handle(it)
                    }
                    .map {
                        itemViewModelConverter.convert(it)
                    }

}
