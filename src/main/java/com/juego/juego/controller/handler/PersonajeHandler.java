package com.juego.juego.controller.handler;

import com.juego.juego.model.Personaje;
import com.juego.juego.service.Interface.PersonajeComandoImpl;
import com.juego.juego.service.Interface.PersonajeConsultaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PersonajeHandler {

    private final PersonajeConsultaImpl personajeConsulta;
    private final PersonajeComandoImpl personajeComando;

    @Autowired
    public PersonajeHandler(PersonajeConsultaImpl personajeConsulta, PersonajeComandoImpl personajeComando){
        this.personajeConsulta = personajeConsulta;
        this.personajeComando = personajeComando;
    }

    public Mono<ServerResponse> obtenerTodoslosPersonajes(ServerRequest request){
        return ServerResponse.ok()
                .body(personajeConsulta.obtenerTodoslosPersonajes(), Personaje.class);
    }

    public Mono<ServerResponse> obtenerPersonajeporID(ServerRequest request){
        String id = request.pathVariable("id");
        return personajeConsulta.obtenerPersonajeporID(id)
                .flatMap(personaje -> ServerResponse.ok()
                        .bodyValue(personaje)).switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).build());
    }

    public Mono<ServerResponse> registrarPersonaje(ServerRequest request){
        Mono<Personaje> personajeMono = request.bodyToMono(Personaje.class);
        return  personajeMono.flatMap(personaje ->
                ServerResponse.status(HttpStatus.CREATED)
                        .body(personajeComando.registrarPersonaje(personaje),Personaje.class));
    }

    public Mono<ServerResponse> actualizarPersonaje(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Personaje> personajeMono = request.bodyToMono(Personaje.class);
        return personajeMono.flatMap(personaje ->
                ServerResponse.ok().body(personajeComando.actualizarPersonaje(id, personaje), Personaje.class));
    }

    public Mono<ServerResponse> eliminarPersonaje(ServerRequest request){
        String id = request.pathVariable("id");
        return personajeComando.eliminarPersonaje(id)
                .then(ServerResponse.noContent().build());
    }
}
