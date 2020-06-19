package de.gally.book

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface BookRepository : CrudRepository<Book, Long> {
    
    /** Finds a new Book by its [title] */
    fun findByTitle(title: String): Book?
}