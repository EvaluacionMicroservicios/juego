package com.juego.juego.repository;

import com.juego.juego.model.Personaje;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends ReactiveMongoRepository<Personaje, String> {
}
