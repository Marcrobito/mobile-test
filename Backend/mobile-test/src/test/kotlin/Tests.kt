import com.superformula.module
import com.superformula.seed.Seed
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import java.time.Instant
import kotlin.test.Test

class Tests {
    @Test
    fun `given a non-existing route when requested then returns 404 with error message`() = testApplication {
        application { module() }

        val response = client.get("/non-existent")

        assertEquals(HttpStatusCode.NotFound, response.status)
        assertTrue(response.bodyAsText().contains("Not Found"))
    }

    @Test
    fun `given GET request to seed when endpoint is hit then response is 200 with expected fields`() = testApplication {
        application { module() }

        val response = client.get("/seed")

        assertEquals(HttpStatusCode.OK, response.status)
        val body = response.bodyAsText()
        assertTrue(body.contains("seed"))
        assertTrue(body.contains("expires_at"))
    }

    @Test
    fun `given a new seed when generated then expiration is at least 30 seconds ahead`() {
        val seed = Seed.getNewSeed()
        val expiration = Instant.parse(seed.expiresAt)
        val now = Instant.now()

        assertTrue(expiration.isAfter(now.plusSeconds(25)))
    }
}