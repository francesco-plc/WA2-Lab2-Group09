package it.polito.wa2.group09.lab2.server

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class Controller(val ticketService: TicketService) {

    @PostMapping("/validate")
    fun posting(@RequestBody ticketDTO: TicketDTO): ResponseEntity<Unit>{
        if(ticketDTO.zone.isEmpty()) return ResponseEntity(HttpStatus.FORBIDDEN )
        if(ticketDTO.token.isEmpty()) return ResponseEntity( HttpStatus.FORBIDDEN )
    return try {
        ticketService.validateTicket(ticketDTO.zone,ticketDTO.token)
        ResponseEntity(HttpStatus.OK)
    } catch (t : Throwable){
        ResponseEntity( HttpStatus.FORBIDDEN )
        }
    }

}
data class TicketDTO( val zone : String, val token : String)