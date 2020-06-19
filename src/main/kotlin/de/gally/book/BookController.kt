package de.gally.book

import de.gally.BookAlreadyExistException
import de.gally.book.client.BookClient
import de.gally.book.client.GoogleBook
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.reactivex.Maybe

@Controller("/api/books", consumes = [MediaType.APPLICATION_JSON])
class BookController(
        private val client: BookClient,
        private val bookRepository: BookRepository
) {
    @Get("/{isbn}")
    fun retrieveBook(@PathVariable("isbn") isbn: String): Maybe<MutableHttpResponse<GoogleBook>> = client
            .requestBookByIsbn(isbn)
            .map { HttpResponse.ok(it) }
            .defaultIfEmpty(HttpResponse.notFound())

    @Get
    fun getAll(): MutableHttpResponse<Iterable<Book>> = bookRepository
            .findAll()
            .let { HttpResponse.ok(it) }

    @Get("/{title}")
    fun getByTitle(@PathVariable("title") title: String): HttpResponse<Book> = bookRepository
            .findByTitle(title)
            ?.let { HttpResponse.ok(it) }
            ?: HttpResponse.notFound()

    @Put("/{title}")
    fun updateBook(@PathVariable("title") title: String, @Body book: Book): HttpResponse<Book> = bookRepository
            .findByTitle(title)
            ?.let { bookRepository.update(book.copy(title = it.title)) }
            ?.let { HttpResponse.ok(it) }
            ?: HttpResponse.notFound()

    @Post
    fun saveBook(@Body book: Book): HttpResponse<Book> = bookRepository
            .findByTitle(book.title)
            ?.let { throw BookAlreadyExistException("Book with title [${it.title}] does already exist") }
            ?: HttpResponse.ok(bookRepository.save(book))

    @Delete("/{title}")
    fun delete(@PathVariable("title") title: String): MutableHttpResponse<Unit> {
        return bookRepository.findByTitle(title)
                ?.let { bookRepository.delete(it) }
                ?.let { HttpResponse.ok<Unit>() }
                ?: HttpResponse.notFound<Unit>()
    }
}
