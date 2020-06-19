package de.gally

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("de.gally")
                .mainClass(Application.javaClass)
                .start()
    }
}