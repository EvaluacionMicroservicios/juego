package com.juego.juego.service.Interface;

import com.juego.juego.model.Personaje;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonajeConsultaImpl {

    Flux<Personaje> obtenerTodoslosPersonajes();
    Mono<Personaje> obtenerPersonajeporID(String id);
}
