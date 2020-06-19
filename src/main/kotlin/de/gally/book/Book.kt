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
//        val publisher: String?,
//        val publishedDate: String?,
//        val description: String?,
//        val categories: List<String>?,
//        val rating: Double,
//        val isRead: Boolean,
//        val isRent: Boolean,
//        val edition: Int,
//        val imageLinks: String,
//        val pageCount: String,
//        val isSeries: Serie,
//        val language: String
        @Id
        @GeneratedValue
        var id: Long
)

data class Serie(val isSeries: Boolean, val isAnthology: Boolean, val volume: Int)