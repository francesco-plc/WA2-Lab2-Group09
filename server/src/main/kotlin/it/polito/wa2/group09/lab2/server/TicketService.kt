package it.polito.wa2.group09.lab2.server

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.security.Key
import java.util.concurrent.ConcurrentHashMap


@Service
class TicketService(val key : Key) {
    var sub_map: ConcurrentHashMap<String, Int> = ConcurrentHashMap()
    fun validateTicket(zone : String , token : String){
            try {
                val jwt = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
                val zones : String = jwt.body["vz"].toString()
                val sub: String = jwt.body["sub"].toString()

                if(!zones.contains(zone)) throw IllegalArgumentException("Illegal zone")
                /* putIfAbsent try to insert the sub in the map.
                the function return null if the value was not in the map
                or the value associated to the key that is always 1.
                */
                if(sub_map.putIfAbsent(sub, 1) == 1) throw IllegalArgumentException("Ticket already used!")
                /* val exp : Long = jwt.body["exp"].toString().toLong()
                   val now : Long = System.currentTimeMillis()/1000
                   if(exp-now <= 0) throw IllegalArgumentException("Expired ticket")*/
            }catch (e : JwtException){
                throw IllegalArgumentException("${e.message}")
            }
    }

}