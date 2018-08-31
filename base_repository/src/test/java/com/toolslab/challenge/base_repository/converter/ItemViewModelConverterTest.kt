package com.toolslab.challenge.base_repository.converter

import com.toolslab.challenge.base_network.model.Data
import com.toolslab.challenge.base_network.model.Feed
import com.toolslab.challenge.base_network.model.Item
import org.amshove.kluent.shouldEqual
import org.junit.Test

class ItemViewModelConverterTest {

    private val underTest = ItemViewModelConverter()

    private val item1 = Item("", "title1", "image1-{imageId}", "location1")
    private val item2 = Item("", "title2", "", "location2")
    private val item3 = Item("", "title3", null, "location3")
    private val feed = Feed(Data("", "", 0, 0, listOf(item1, item2, item3)), null)

    @Test
    fun convert() {
        val itemViewModel = underTest.convert(feed)

        itemViewModel.size shouldEqual 3
        itemViewModel[0].apply {
            title shouldEqual item1.title
            location shouldEqual item1.locationName
            thumbnailImageUrl shouldEqual "image1-35"
            fullSizeImageUrl shouldEqual "image1-57"
        }
        itemViewModel[1].apply {
            title shouldEqual item2.title
            location shouldEqual item2.locationName
            thumbnailImageUrl shouldEqual item2.image
            fullSizeImageUrl shouldEqual item2.image
        }
        itemViewModel[2].apply {
            thumbnailImageUrl shouldEqual ""
            fullSizeImageUrl shouldEqual ""
        }
    }

}
