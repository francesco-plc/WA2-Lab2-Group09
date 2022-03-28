package it.polito.wa2.group09.lab2.server

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.security.Key
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap


@Service
class TicketService(val key : Key) {
    fun validateTicket(zone : String , token : String){
            try {
                val jwt = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
                val zones : String = jwt.body["vz"].toString()
                if(!zones.contains(zone)) throw IllegalArgumentException("Illegal zone")
                /* val exp : Long = jwt.body["exp"].toString().toLong()
                   val now : Long = System.currentTimeMillis()/1000
                   if(exp-now <= 0) throw IllegalArgumentException("Expired ticket")*/
            }catch (e : JwtException){
                throw IllegalArgumentException("${e.message}")
            }
    }

}