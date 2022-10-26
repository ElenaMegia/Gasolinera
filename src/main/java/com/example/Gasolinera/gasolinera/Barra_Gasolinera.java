package com.example.Gasolinera.gasolinera;

import  com.example.Gasolinera.utilidades.Log;

public class Barra_Gasolinera {

    private int surtidor =4; // Es el número de coches que pueden entrar a la gasolinera

    public synchronized void entradaASurtidor(int id_f, Log log) throws InterruptedException{
        while(surtidor ==0){ // Si no hay surtidores libres toca esperar
            this.wait();
        }
        System.out.println("El coche " + (id_f+1) + " va al surtidor " + surtidor);
        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gráfica:
        surtidor--; // Conteo de comensales
    }

    public synchronized void salirSurtidor(int id_f, Log log) throws InterruptedException{
        surtidor++; // Conteo de surtidores
        System.out.println("El coche " + (id_f+1) + " ya NO esta en el surtidor " + surtidor);
        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gráfica:
        this.notify(); // Notificación al siguiente de que hay comensal disponible.
    }
}
