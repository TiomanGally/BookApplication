package de.gally.book

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "book")
data class Book(
        val title: String,
//        val author: List<String>?,
        val publisher: String?,
        val publishedDate: String?,
        val description: String?,
//        val categories: List<String>?,
        val rating: Double? = null,
        val isRead: Boolean? = null,
        val isRent: Boolean? = null,
        val edition: Int? = null,
        val imageLinks: String? = null,
        val pageCount: String? = null,
//        val isSeries: Serie? = null,
        val language: String? = null,
        @Id
        @GeneratedValue
        var id: Long
)

data class Serie(val isSeries: Boolean, val isAnthology: Boolean, val volume: Int)
