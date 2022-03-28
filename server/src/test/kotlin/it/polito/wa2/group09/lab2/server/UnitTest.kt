package it.polito.wa2.group09.lab2.server

import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertContains


@SpringBootTest
class UnitTest {

    @Autowired
    lateinit var ticketService: TicketService

    @Test
    fun illegalZone() {
        val exception: IllegalArgumentException= Assertions.assertThrows(IllegalArgumentException::class.java) {
            ticketService.validateTicket("7", "aaa.bbb.ccc")
        }
        assertEquals("Token is not valid!", exception.message);
    }

    @Test
    fun expiredToken() {
        val exception: IllegalArgumentException  = Assertions.assertThrows(IllegalArgumentException::class.java) { //this token is set on Jan 18 2018
            ticketService.validateTicket(
                "1",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MTYyMzkwMjIsInZ6IjoiMTIzIn0.kdaASj1f1DILzjU0W_wXjY28os_lL6JbNHed00EgCK4"
            )
        }
        println(exception)
        assertContains(exception.message.toString(),"JWT expired at 2018-01-18T01:30:22Z.")
    }

    @Test
    fun invalidToken() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            ticketService.validateTicket("1", "abc")
        }
    }

    @Test
    fun validToken() {
        Assertions.assertDoesNotThrow()
        {
            ticketService.validateTicket(
                "1",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNzc2MjM5MDIyLCJleHAiOjE3NzYyMzkwMjIsInZ6IjoiMTIzIn0.V40jee26UUl3J0p5KT8QD7U9f7h4eLaxJLmiL_z0eFA"
            )
        }
    }

}