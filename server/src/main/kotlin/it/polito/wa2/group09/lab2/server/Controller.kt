package it.polito.wa2.group09.lab2.server

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.security.Key


@RestController
class Controller(val ticketService: TicketService) {
//    @GetMapping("/validate")
//    fun validate() : Payload {
//        return Payload("200")
//    }
    @PostMapping("/validate")
    fun posting(@RequestBody ticketDTO: TicketDTO): ResponseEntity<String>{
        if(ticketDTO.zone.isEmpty()) return ResponseEntity("Invalid Zone!" , HttpStatus.FORBIDDEN )
        if(ticketDTO.token.isEmpty()) return ResponseEntity("Missing Token!" , HttpStatus.FORBIDDEN )
        try {
            ticketService.validateTicket(ticketDTO.zone,ticketDTO.token)
            return ResponseEntity("Ok",HttpStatus.OK)
        }
        catch (t : Throwable){
            return ResponseEntity("Invalid Token!" , HttpStatus.FORBIDDEN )
        }

    }

}
data class TicketDTO( val zone:String, val token : String)