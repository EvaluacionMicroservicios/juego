package com.juego.juego.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "personaje")
public class Personaje {

    @Id
    private String id;
    private String nombre;
    private int vida;
    private int mana;

    public Personaje() {
    }

    public Personaje(String id, String nombre, int vida, int mana) {
        this.id = id;
        this.nombre = nombre;
        this.vida = vida;
        this.mana = mana;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getMana() {
        return mana;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
