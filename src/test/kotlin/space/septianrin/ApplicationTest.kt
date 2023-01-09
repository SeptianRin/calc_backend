package space.septianrin

import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import space.septianrin.models.OperationNumber
import kotlin.test.*

class OrderRouteTests {
    @Test
    fun testAdd() = testApplication {
        val response = client.post("/add"){
            contentType(ContentType.Application.Json)
            setBody("""{"numberList": [20,2]}""")
        }

        assertEquals(
            """{"status":200,"message":"OK","data":"22.0"}""",
            response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testSubstract() = testApplication {
        val response = client.post("/subtract"){
            contentType(ContentType.Application.Json)
            setBody("""{"numberList": [20,2]}""")
        }

        assertEquals(
            """{"status":200,"message":"OK","data":"18.0"}""",
            response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testDivide() = testApplication {
        val response = client.post("/divide"){
            contentType(ContentType.Application.Json)
            setBody("""{"numberList": [20,2]}""")
        }

        assertEquals(
            """{"status":200,"message":"OK","data":"10.0"}""",
            response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testDivideUnique() = testApplication {
        val response = client.post("/divide"){
            contentType(ContentType.Application.Json)
            setBody("""{"numberList": [20,2,2,2]}""")
        }

        assertEquals(
            """{"status":400,"message":"Allowed input parameter is two parameter","data":null}""",
            response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testMultiply() = testApplication {
        val response = client.post("/multiply"){
            contentType(ContentType.Application.Json)
            setBody("""{"numberList": [20,2]}""")
        }

        assertEquals(
            """{"status":200,"message":"OK","data":"40.0"}""",
            response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testSplitEq() = testApplication {
        val response = client.post("/spliteq"){
            contentType(ContentType.Application.Json)
            setBody("""{"numberList": [20,2]}""")
        }

        assertEquals(
            """{"status":200,"message":"OK","data":"[10, 10]"}""",
            response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testSplitEqMany() = testApplication {
        val response = client.post("/spliteq"){
            contentType(ContentType.Application.Json)
            setBody("""{"numberList": [20,5]}""")
        }

        assertEquals(
            """{"status":200,"message":"OK","data":"[4, 4, 4, 4, 4]"}""",
            response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testSplitNum() = testApplication {
        val response = client.post("/splitnum"){
            contentType(ContentType.Application.Json)
            setBody("""{"numberList": [20,2]}""")
        }

        assertEquals(
            """{"status":200,"message":"OK","data":"18.0"}""",
            response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testSplitNumUnique() = testApplication {
        val response = client.post("/splitnum"){
            contentType(ContentType.Application.Json)
            setBody("""{"numberList": [20,10,10]}""")
        }

        assertEquals(
            """{"status":400,"message":"There's no remainder for those parameter","data":null}""",
            response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }

}