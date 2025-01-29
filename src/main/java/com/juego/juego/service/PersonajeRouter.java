package com.juego.juego.service;

import com.juego.juego.controller.handler.PersonajeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PersonajeRouter {

    @Bean
    public RouterFunction personajerouter(PersonajeHandler personajeHandler){
        return route()
                .GET("/api/personaje", personajeHandler::obtenerTodoslosPersonajes)
                .GET("/api/personaje/{id}", personajeHandler::obtenerPersonajeporID)
                .POST("api/personaje/registrar-personaje", personajeHandler::registrarPersonaje)
                .PUT("/api/personaje/actualizar-personaje/{id}", personajeHandler::actualizarPersonaje)
                .DELETE("/api/personaje/eliminar-personaje/{id}", personajeHandler::eliminarPersonaje)
                .build();
    }
}
