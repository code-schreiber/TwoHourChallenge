package com.toolslab.challenge.base_repository.converter

import com.toolslab.challenge.base_network.model.Feed
import com.toolslab.challenge.base_repository.model.ItemViewModel
import javax.inject.Inject

class ItemViewModelConverter @Inject constructor() : Converter<Feed, List<ItemViewModel>> {

    private val imageUrlPlaceholder = "{imageId}"
    private val thumbnailValue = "35"
    private val fullSizeValue = "57"

    override fun convert(source: Feed) =
            source.data.items.map {
                val imageUrl = it.image ?: ""
                ItemViewModel(it.title,
                        it.locationName,
                        imageUrl.replace(imageUrlPlaceholder, thumbnailValue),
                        imageUrl.replace(imageUrlPlaceholder, fullSizeValue))
            }

}
