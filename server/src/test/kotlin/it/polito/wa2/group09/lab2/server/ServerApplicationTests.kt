package it.polito.wa2.group09.lab2.server

import io.jsonwebtoken.Jwts
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import java.net.URI
import java.net.http.HttpRequest
import java.security.Key
import java.util.*

@SpringBootTest
class ServerApplicationTests {
    protected val port = 8080

    @Autowired
    lateinit var ticketService: TicketService

    @Test
    fun illegalZone() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            ticketService.validateTicket("7", "")
        }
    }

}


