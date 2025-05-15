package com.superformula.seed

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import io.ktor.server.routing.get

/**
 * Defines the `/seed` route for the Ktor server.
 *
 * This route handles a GET request and responds with a newly generated [Seed] object,
 * which includes a unique UUID and an expiration timestamp 30 seconds in the future.
 *
 * Example request:
 * ```
 * GET /seed
 * ```
 *
 * Example response:
 * ```json
 * {
 *   "seed": "e482e2fa-37fd-4e3d-b240-9d7c1df01c85",
 *   "expires_at": "2025-05-13T20:49:31.281215Z"
 * }
 * ```
 */
fun Route.seedRoutes() {
    route("/seed") {
        get {
            call.respond(Seed.getNewSeed())
        }
    }
}