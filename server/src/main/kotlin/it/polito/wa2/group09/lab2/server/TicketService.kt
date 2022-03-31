package it.polito.wa2.group09.lab2.server

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.security.Key
import java.util.concurrent.ConcurrentHashMap


@Service
class TicketService(val key : Key) {
    var subMap: ConcurrentHashMap<String, Int> = ConcurrentHashMap()
    fun validateTicket(zone : String , token : String){
            try {
               //jwt automatically control that the expiry timestamp (named “exp”) is still valid
                val jwt = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)

                val zones : String = jwt.body["vz"].toString()
                val sub: String = jwt.body["sub"].toString()

                if(!zones.contains(zone)) throw IllegalArgumentException("Illegal zone")
                /* putIfAbsent try to insert the sub in the map.
                the function return null if the value was not in the map
                or the value associated to the key that is always 1.
                */
                if(subMap.putIfAbsent(sub, 1) == 1) throw IllegalArgumentException("Ticket already used!")

            }catch (e : JwtException){
                throw IllegalArgumentException("${e.message}")
            }
    }

}