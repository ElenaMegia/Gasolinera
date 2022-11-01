package com.example.Gasolinera.gasolinera;

import  com.example.Gasolinera.utilidades.Log;

public class Barra_Gasolinera {


    private int respostando = (int) (Math.random() * (130-50)+50);
    private int id;
    private boolean vacio = true;
    public Barra_Gasolinera(int id) {
        this.id = id;
    }

    public synchronized boolean entradaASurtidor(int id_f, Log log) throws InterruptedException{
        while(!vacio){ // Si no hay surtidores libres toca esperar
            this.wait(respostando);
        }
        System.out.println("El coche " + (id_f+1) + " va al surtidor " + id);
        vacio = false;
        return true;
    }

    public synchronized void salirSurtidor(int id_f, Log log) throws InterruptedException{
        vacio=true;
        System.out.println("El coche " + (id_f+1) + " ya NO esta en el surtidor " + id);
        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gráfica:
        this.notify(); // Notificación al siguiente de que hay comensal disponible.
    }
}
