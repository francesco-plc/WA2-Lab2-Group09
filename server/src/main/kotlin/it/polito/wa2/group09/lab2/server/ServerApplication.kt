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

}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
