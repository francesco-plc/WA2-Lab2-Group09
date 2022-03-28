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
            /** {"sub": "1234567890","vz": "123","exp": 1716239022}*/
            ticketService.validateTicket(
                "7",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwidnoiOiIxMjMiLCJleHAiOjE3MTYyMzkwMjJ9.xtBlm0TSgP29xnsRqdedEZ91WEPwymg8SjTqfw1rprY"
            )
        }
        assertEquals("Illegal zone", exception.message);
    }

    @Test
    fun expiredToken() {
        val exception: IllegalArgumentException  = Assertions.assertThrows(IllegalArgumentException::class.java) { //this token is set on Jan 18 2018
            /** {"sub": "1234567891", "vz": "123", "exp": 1316239022}*/
            ticketService.validateTicket(
                "1",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkxIiwidnoiOiIxMjMiLCJleHAiOjEzMTYyMzkwMjJ9.z3EF1CKUZCWvV7RMkK4UlkQxS7Tl7DQSYRO_NJ6YruY"
            )
        }
        assertContains(exception.message.toString(),"JWT expired at 2011-09-17T05:57:02Z.")
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
            /** {"sub": "1234567890","vz": "123","exp": 1716239022}*/
            ticketService.validateTicket(
                "1",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwidnoiOiIxMjMiLCJleHAiOjE3MTYyMzkwMjJ9.xtBlm0TSgP29xnsRqdedEZ91WEPwymg8SjTqfw1rprY"
            )
        }
    }

    @Test
    fun invalidSub() {
        val exception: IllegalArgumentException  = Assertions.assertThrows(IllegalArgumentException::class.java) {
            /** {"sub": "1234567890","vz": "123","exp": 1716239022}*/
            ticketService.validateTicket(
                "1",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwidnoiOiIxMjMiLCJleHAiOjE3MTYyMzkwMjJ9.xtBlm0TSgP29xnsRqdedEZ91WEPwymg8SjTqfw1rprY"
            )
            ticketService.validateTicket(
                "1",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwidnoiOiIxMjMiLCJleHAiOjE3MTYyMzkwMjJ9.xtBlm0TSgP29xnsRqdedEZ91WEPwymg8SjTqfw1rprY"
            )
        }
        assertContains(exception.message.toString(),"Ticket already used!")
    }

}