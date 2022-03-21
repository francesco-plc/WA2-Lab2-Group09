package it.polito.wa2.group09.lab2.server

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


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
    return try {
        ticketService.validateTicket(ticketDTO.zone,ticketDTO.token)
        ResponseEntity("Ok",HttpStatus.OK)
    } catch (t : Throwable){
        ResponseEntity("Invalid Token!" , HttpStatus.FORBIDDEN )
        }
    }

}
data class TicketDTO( val zone : String, val token : String)