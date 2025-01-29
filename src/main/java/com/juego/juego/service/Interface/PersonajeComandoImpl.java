package com.juego.juego.service.Interface;

import com.juego.juego.model.Personaje;
import reactor.core.publisher.Mono;

public interface PersonajeComandoImpl {

    Mono<Personaje> registrarPersonaje(Personaje personaje);
    Mono<Personaje> actualizarPersonaje(String id, Personaje personaje);
    Mono<Void> eliminarPersonaje(String id);
}
