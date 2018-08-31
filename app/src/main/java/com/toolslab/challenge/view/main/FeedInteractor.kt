package com.toolslab.challenge.view.main

import android.support.annotation.CheckResult
import com.toolslab.challenge.base_repository.SpaceRepository
import javax.inject.Inject

class FeedInteractor @Inject constructor() {

    @Inject
    internal lateinit var spaceRepository: SpaceRepository

    @CheckResult
    internal fun listItems() = spaceRepository.listItems()

}
