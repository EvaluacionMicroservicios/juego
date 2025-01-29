package com.juego.juego.service;

import com.juego.juego.model.Personaje;
import com.juego.juego.repository.PersonajeRepository;
import com.juego.juego.service.Interface.PersonajeComandoImpl;
import com.juego.juego.service.Interface.PersonajeConsultaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonajeService implements PersonajeConsultaImpl, PersonajeComandoImpl {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Override
    public Flux<Personaje> obtenerTodoslosPersonajes(){
        return personajeRepository.findAll();
    }

    @Override
    public Mono<Personaje> obtenerPersonajeporID(String id){
        return  personajeRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontro personaje")));
    }

    @Override
    public Mono<Personaje> registrarPersonaje(Personaje personaje){
        return personajeRepository.save(personaje);
    }

    @Override
    public Mono<Personaje> actualizarPersonaje(String id, Personaje personaje){
        return personajeRepository.findById(id).flatMap(existingpersonaje -> {
            existingpersonaje.setNombre(personaje.getNombre() != null ? personaje.getNombre() : existingpersonaje.getNombre());
            existingpersonaje.setVida(personaje.getVida() != 0 ? personaje.getVida() : existingpersonaje.getVida());
            existingpersonaje.setMana(personaje.getMana() != 0 ? personaje.getMana() : existingpersonaje.getMana());
            return personajeRepository.save(existingpersonaje);
        }).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro personaje")));
    }

    @Override
    public Mono<Void> eliminarPersonaje(String id){
        return personajeRepository.deleteById(id);
    }
}
