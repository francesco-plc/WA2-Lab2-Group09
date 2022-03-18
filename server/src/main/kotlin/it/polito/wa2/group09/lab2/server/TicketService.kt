package it.polito.wa2.group09.lab2.server

import io.jsonwebtoken.JwsHeader
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.security.Key


@Service
class TicketService(val key : Key) {
    fun validateTicket(zone : String , token : String){
            val jwt = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            val zones : String = jwt.body.get("vz").toString()
            println(jwt.body.get("exp"))
            val exp : Long = jwt.body.get("exp").toString().toLong()
            val now : Long = System.currentTimeMillis()/1000
            if(exp-now <= 0) throw IllegalArgumentException("Expired ticket")
            if(!zones.contains(zone)) throw IllegalArgumentException("Illegal zone")
    }

}