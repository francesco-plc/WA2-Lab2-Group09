package it.polito.wa2.group09.lab2.server



import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServerApplicationTests {
    @LocalServerPort
    protected val port: Int = 0

    @Autowired
    lateinit var ticketService: TicketService

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun illegalZone() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            ticketService.validateTicket("7", "")
        }
    }

    @Test
    fun expiredToken() {
        Assertions.assertThrows(IllegalArgumentException::class.java) { //this token is set on Jan 18 2018
            ticketService.validateTicket(
                "1",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.-DRSwRSZVPXaXVQZ3zkj3wqibKWgvOsA600QJCNKdxI"
            )
        }
    }

    @Test
    fun invalidToken() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            ticketService.validateTicket("1", "abc")
        }
    }

    @Test
    fun rejectInvalidZone() {
        val baseUrl = "http://localhost:$port"
        val t = TicketDTO("", "aaa.bbb.ccc")
        val request = HttpEntity(t)
        val response = restTemplate.postForEntity<String>("$baseUrl/validate",request)
        assert(response.statusCode === HttpStatus.FORBIDDEN)
    }
    @Test
    fun rejectEmptyToken() {
        val baseUrl = "http://localhost:$port"
        val t = TicketDTO("1", "")
        val request = HttpEntity(t)
        val response = restTemplate.postForEntity<String>("$baseUrl/validate",request)
        assert(response.statusCode === HttpStatus.FORBIDDEN)
    }
    @Test
    fun acceptValidToken() {
        val baseUrl = "http://localhost:$port"
        val t = TicketDTO("1", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNzc2MjM5MDIyLCJleHAiOjE3NzYyMzkwMjIsInZ6IjoiMTIzIn0.V40jee26UUl3J0p5KT8QD7U9f7h4eLaxJLmiL_z0eFA")
        val request = HttpEntity(t)
        val response = restTemplate.postForEntity<String>("$baseUrl/validate",request)
        assert(response.statusCode === HttpStatus.OK)
    }

}