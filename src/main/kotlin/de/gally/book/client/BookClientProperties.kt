package de.gally.book.client

import io.micronaut.context.annotation.ConfigurationProperties
import java.net.URL

@ConfigurationProperties("external.book")
class BookClientConfig {
    lateinit var baseUrl: URL
    lateinit var path: String
}