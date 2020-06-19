package de.gally.book.client

import de.gally.BaseWebClient
import io.micronaut.http.HttpRequest
import io.reactivex.Maybe
import org.slf4j.LoggerFactory
import javax.inject.Singleton


@Singleton
class BookClient(
        private val config: BookClientConfig
) : BaseWebClient(config.baseUrl) {

    private val logger = LoggerFactory.getLogger(this::class.java.simpleName)

    /** Requests a Book from an API by its [isbn] */
    fun requestBookByIsbn(isbn: String): Maybe<GoogleBook> {
        val req = HttpRequest.GET<String>(config.path.setParam(Params.ID, isbn))

        logger.info("Making [${req.method}] with [${req.uri}]")

        val res = client
                .retrieve(req, GoogleBook::class.java)
                .firstElement()
                .handleError(TargetSystem.GOOGLE_BOOK_API)
                as Maybe<GoogleBook>

        return res.flatMap { if (it.totalItems == 0) Maybe.empty() else Maybe.just(it) }
    }
}