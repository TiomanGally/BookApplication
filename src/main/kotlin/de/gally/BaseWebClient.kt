package de.gally

import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.reactivex.Maybe
import org.slf4j.LoggerFactory
import java.net.URL

abstract class BaseWebClient(private val baseUrl: URL) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    /** Initialize a WebClient for making requests */
    val client: RxHttpClient by lazy {
        RxHttpClient.create(baseUrl)
    }

    /** Default value for a not handled [HttpClientResponseException] */
    private val defaultErrorHandler: HttpClientResponseException.() -> Unit = {
        logger.info("Unhandled Exception was thrown with status [${this.status}] and body [${this.response.body()}]")
    }

    /** Holds all params which may be replaced in URL */
    object Params {
        const val ID = ":isbn"
    }

    /** Replaces the [Params] with the value */
    fun String.setParam(old: String, new: String) = this.replace(old, new)

    /** Handles Basic [HttpClientResponseException] errors */
    fun <T> Maybe<T>.handleError(targetSystem: TargetSystem, error: HttpClientResponseException.() -> Unit = defaultErrorHandler): Maybe<T> {
        return this.doOnError {
            logger.info("Error occurred while requesting $targetSystem")
            when (it) {
                is HttpClientResponseException -> {
                    when (it.status) {
                        else -> error(it)
                    }
                }
            }
        }
    }

    /** Specifies the [TargetSystem] which should received that request*/
    enum class TargetSystem {
        GOOGLE_BOOK_API;
    }
}