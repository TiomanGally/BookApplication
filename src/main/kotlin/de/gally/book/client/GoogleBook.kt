package de.gally.book.client

import io.micronaut.core.annotation.Introspected

@Introspected
data class GoogleBook(
        val kind: String,
        val totalItems: Int,
        val items: List<Item>?
)

@Introspected
data class Item(
        val volumeInfo: VolumeInfo
)

@Introspected
data class VolumeInfo(
        val title: String,
        val description: String?,
        val authors: List<String>?,
        val categories: List<String>?,
        val publishedDate: String?,
        val publisher: String?,
        val pageCount: Int?,
        val imageLinks: ImageLinks?,
        val language: String?
)

@Introspected
data class ImageLinks(val smallThumbnail: String, val thumbnail: String)