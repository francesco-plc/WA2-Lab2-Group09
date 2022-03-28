package it.polito.wa2.group09.lab2.server



import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServerApplicationTests {
    @LocalServerPort
    protected val port: Int = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun rejectInvalidZone() {
        val baseUrl = "http://localhost:$port"
        /** {"sub": "1234567890","vz": "123","exp": 1716239022}*/
        val t = TicketDTO("7", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwidnoiOiIxMjMiLCJleHAiOjE3MTYyMzkwMjJ9.xtBlm0TSgP29xnsRqdedEZ91WEPwymg8SjTqfw1rprY")
        val request = HttpEntity(t)
        val response = restTemplate.postForEntity<String>("$baseUrl/validate",request)
        assertEquals(HttpStatus.FORBIDDEN,response.statusCode)
    }
    @Test
    fun rejectEmptyToken() {
        val baseUrl = "http://localhost:$port"
        val t = TicketDTO("1", "")
        val request = HttpEntity(t)
        val response = restTemplate.postForEntity<String>("$baseUrl/validate",request)
        assertEquals(HttpStatus.FORBIDDEN, response.statusCode)
    }
    @Test
    fun rejectInvalidToken() {
        val baseUrl = "http://localhost:$port"
        val t = TicketDTO("1", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.-DRSwRSZVPXaXVQZ3zkj3wqibKWgvOsA600QJCNKdxI")
        val request = HttpEntity(t)
        val response = restTemplate.postForEntity<String>("$baseUrl/validate",request)
        assertEquals(HttpStatus.FORBIDDEN, response.statusCode)
    }
    @Test
    fun acceptValidToken() {
        val baseUrl = "http://localhost:$port"
        /** {"sub": "1234567890","vz": "123","exp": 1716239022}*/
        val t = TicketDTO("1", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwidnoiOiIxMjMiLCJleHAiOjE3MTYyMzkwMjJ9.xtBlm0TSgP29xnsRqdedEZ91WEPwymg8SjTqfw1rprY")
        val request = HttpEntity(t)
        val response = restTemplate.postForEntity<String>("$baseUrl/validate",request)
        assertEquals(HttpStatus.OK,response.statusCode )
    }

    @Test
    fun rejectInvalidSub() {
        val baseUrl = "http://localhost:$port"
        /** {"sub": "1234567890","vz": "123","exp": 1716239022}*/
        val t = TicketDTO("1",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwidnoiOiIxMjMiLCJleHAiOjE3MTYyMzkwMjJ9.xtBlm0TSgP29xnsRqdedEZ91WEPwymg8SjTqfw1rprY")
        val request = HttpEntity(t)
        val response1 = restTemplate.postForEntity<String>("$baseUrl/validate", request)
        val response2 = restTemplate.postForEntity<String>("$baseUrl/validate", request)
        assertEquals(HttpStatus.FORBIDDEN, response2.statusCode)
    }
}