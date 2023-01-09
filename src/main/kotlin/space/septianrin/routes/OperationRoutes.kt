package space.septianrin.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import space.septianrin.models.OperationNumber
import space.septianrin.models.OperationResponse

fun Route.operationRouting() {
    route("/add") {
        post {
            val operationNumber = call.receive<OperationNumber>().numberList
            call.respond(
                OperationResponse(
                    HttpStatusCode.OK.value,
                    HttpStatusCode.OK.description,
                    operationNumber.sum().toString()
                )
            )
        }
    }
    route("/subtract") {
        post {
            val operationNumber = call.receive<OperationNumber>().numberList
            var result = operationNumber[0]
            for (item in operationNumber.subList(1, operationNumber.size)) {
                result -= item
            }
            call.respond(OperationResponse(HttpStatusCode.OK.value, HttpStatusCode.OK.description, result.toString()))
        }
    }
    route("/multiply") {
        post {
            val operationNumber = call.receive<OperationNumber>().numberList
            var result = 1.0
            for (item in operationNumber) {
                result *= item
            }
            call.respond(OperationResponse(HttpStatusCode.OK.value, HttpStatusCode.OK.description, result.toString()))
        }
    }
    route("/divide") {
        post {
            val operationNumber = call.receive<OperationNumber>().numberList
            if (operationNumber.size == 2) {
                val result = operationNumber[0] / operationNumber[1]
                call.respond(
                    OperationResponse(
                        HttpStatusCode.OK.value,
                        HttpStatusCode.OK.description,
                        result.toString()
                    )
                )
            } else {
                call.respond(
                    OperationResponse(
                        HttpStatusCode.BadRequest.value,
                        "Allowed input parameter is two parameter",
                        null
                    )
                )
            }
        }
    }
    route("/spliteq") {
        post {
            val operationNumber = call.receive<OperationNumber>().numberList
            if (operationNumber.size == 2) {
                val resultBuilder = (operationNumber[0] / operationNumber[1]).toInt()
                val result = mutableListOf<String>()
                for (i in 1..operationNumber[1].toInt()) {
                    result.add(resultBuilder.toString())
                }
                call.respond(
                    OperationResponse(
                        HttpStatusCode.OK.value,
                        HttpStatusCode.OK.description,
                        result.toString()
                    )
                )
            } else {
                call.respond(
                    OperationResponse(
                        HttpStatusCode.BadRequest.value,
                        "Allowed input parameter is two parameter",
                        null
                    )
                )
            }
        }
    }
    route("/splitnum") {
        post {
            val operationNumber = call.receive<OperationNumber>().numberList
            var result = operationNumber[0]
            for (item in operationNumber.subList(1, operationNumber.size)) {
                result -= item
            }
            if (result > 0) {
                call.respond(
                    OperationResponse(
                        HttpStatusCode.OK.value,
                        HttpStatusCode.OK.description,
                        result.toString()
                    )
                )
            } else {
                call.respond(
                    OperationResponse(
                        HttpStatusCode.BadRequest.value,
                        "There's no remainder for those parameter",
                        null
                    )
                )
            }
        }
    }


}