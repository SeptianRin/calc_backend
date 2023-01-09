package space.septianrin.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import space.septianrin.routes.operationRouting

fun Application.configureRouting() {
    routing {
        operationRouting()
    }
}
