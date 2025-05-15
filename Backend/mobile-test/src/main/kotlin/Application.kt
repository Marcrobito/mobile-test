package com.superformula

import com.superformula.responses.ErrorResponse
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

/**
 * Entry point for launching the Ktor application using Netty.
 *
 * @param args Command-line arguments.
 */
fun main(args: Array<String>) {
    EngineMain.main(args)
}

/**
 * Main application module that sets up Ktor features and routes.
 *
 * - Installs JSON content negotiation via Kotlinx Serialization.
 * - Handles 404 errors by returning a structured [ErrorResponse].
 * - Registers route handlers using [configureRouting].
 */
fun Application.module() {
    if (pluginOrNull(ContentNegotiation) == null) {
        install(ContentNegotiation) {
            json()
        }
    }

    if (pluginOrNull(StatusPages) == null) {
        install(StatusPages) {
            status(HttpStatusCode.NotFound) { call, _ ->
                call.respond(HttpStatusCode.NotFound, ErrorResponse("Not Found", 404))
            }
        }
    }

    configureRouting()
}