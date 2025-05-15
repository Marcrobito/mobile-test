package com.superformula

import com.superformula.seed.seedRoutes
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

/**
 * Configures all HTTP routes for the Ktor application.
 *
 * This function sets up the main routing block and registers
 * all endpoint groups, including the [seedRoutes].
 *
 * It should be called from the application's main module.
 */
fun Application.configureRouting() {
    routing {
        seedRoutes()
    }
}