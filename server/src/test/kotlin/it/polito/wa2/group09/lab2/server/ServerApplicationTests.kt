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
        val t = TicketDTO("", "aaa.bbb.ccc")
        val request = HttpEntity(t)
        val response = restTemplate.postForEntity<String>("$baseUrl/validate",request)
        assertEquals("Invalid Zone!", response.body)
        assertEquals(HttpStatus.FORBIDDEN,response.statusCode)
    }
    @Test
    fun rejectEmptyToken() {
        val baseUrl = "http://localhost:$port"
        val t = TicketDTO("1", "")
        val request = HttpEntity(t)
        val response = restTemplate.postForEntity<String>("$baseUrl/validate",request)
        assertEquals("Missing Token!", response.body)
        assertEquals(HttpStatus.FORBIDDEN,response.statusCode)
    }
    @Test
    fun rejectInvalidToken() {
        val baseUrl = "http://localhost:$port"
        val t = TicketDTO("1", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.-DRSwRSZVPXaXVQZ3zkj3wqibKWgvOsA600QJCNKdxI")
        val request = HttpEntity(t)
        val response = restTemplate.postForEntity<String>("$baseUrl/validate",request)
        assertEquals("Invalid Token!", response.body)
        assertEquals(HttpStatus.FORBIDDEN,response.statusCode)
    }
    @Test
    fun acceptValidToken() {
        val baseUrl = "http://localhost:$port"
        val t = TicketDTO("1", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNzc2MjM5MDIyLCJleHAiOjE3NzYyMzkwMjIsInZ6IjoiMTIzIn0.V40jee26UUl3J0p5KT8QD7U9f7h4eLaxJLmiL_z0eFA")
        val request = HttpEntity(t)
        val response = restTemplate.postForEntity<String>("$baseUrl/validate",request)
        assertEquals("Ok", response.body)
        assertEquals(HttpStatus.OK,response.statusCode )
    }

}