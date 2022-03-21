package it.polito.wa2.group09.lab2.server

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UnitTest {

    @Autowired
    lateinit var ticketService: TicketService

    @Test
    fun illegalZone() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            ticketService.validateTicket("7", "aaa.bbb.ccc")
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

}