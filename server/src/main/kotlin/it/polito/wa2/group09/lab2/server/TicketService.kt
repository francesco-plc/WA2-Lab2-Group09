package it.polito.wa2.group09.lab2.server

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.security.Key


@Service
class TicketService(val key : Key) {
    fun validateTicket(zone : String , token : String){
            try {
                val jwt = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
                val zones : String = jwt.body["vz"].toString()
                println(jwt.body["exp"])
                val exp : Long = jwt.body["exp"].toString().toLong()
                val now : Long = System.currentTimeMillis()/1000
                if(exp-now <= 0) throw IllegalArgumentException("Expired ticket")
                if(!zones.contains(zone)) throw IllegalArgumentException("Illegal zone")
            }catch (e : JwtException){
                throw IllegalArgumentException("Token is not valid!")
            }
    }

}