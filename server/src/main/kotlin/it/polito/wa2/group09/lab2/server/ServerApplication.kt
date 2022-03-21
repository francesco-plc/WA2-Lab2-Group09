package it.polito.wa2.group09.lab2.server

import io.jsonwebtoken.security.Keys
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.security.Key


@SpringBootApplication
class ServerApplication{
    @Bean
    fun key() : Key = Keys.hmacShaKeyFor("laboratorio2webapplications2ProfessorGiovanniMalnati".toByteArray())
   /* @Bean
    fun token(key : Key) : CommandLineRunner{
        return CommandLineRunner {
            val jws = Jwts.builder().setSubject("Joe").setExpiration(Date()).signWith(key).compact()


            val objectMapper = ObjectMapper()
            val requestBody: String = objectMapper
                .writeValueAsString(jws)
            val client = HttpClient.newBuilder().build();
            val request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/validate"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            println(response.body())
        }
    }*/
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)

}
