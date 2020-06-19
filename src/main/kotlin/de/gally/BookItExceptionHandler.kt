package de.gally

import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Produces
import io.micronaut.http.client.exceptions.HttpClientException
import io.micronaut.http.server.exceptions.ExceptionHandler
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [Throwable::class])
class BookItExceptionHandler : ExceptionHandler<Throwable, HttpResponse<BookItExceptionHandler.Message>> {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun handle(request: HttpRequest<*>?, exception: Throwable?): HttpResponse<Message> {
        if (request != null) {
            val httpMethod = request.method
            val methodName = request.methodName
            val path = request.path

            logger.error("Received error in [$methodName] on [$path] with method [$httpMethod]: \n$exception")
        }


        return when (exception) {
            is BookItException -> when (exception) {
                is BookAlreadyExistException -> HttpResponse.status(HttpStatus.CONFLICT, exception.message)
                else -> HttpResponse.serverError()
            }
            is HttpClientException -> HttpResponse.serverError(Message("Client could not resolve URI"))
            else -> HttpResponse.status(HttpStatus.I_AM_A_TEAPOT)
        }
    }

    data class Message(val message: String, val timeStamp: String = LocalDateTime.now().toString())
}

open class BookItException(message: String) : Throwable(message)
class BookAlreadyExistException(message: String) : BookItException(message)