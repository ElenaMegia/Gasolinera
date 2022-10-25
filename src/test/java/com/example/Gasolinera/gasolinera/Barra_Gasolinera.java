package com.example.Gasolinera.gasolinera;

import  com.example.Gasolinera.utilidades.Log;
import com.example.Gasolinera.vistas.Clase10Control;
public class Barra_Gasolinera {

    private int vehiculo=49; // Es el número de coches total de filósofos menos 1

    public synchronized void cogerComensal(int id_f, Log log) throws InterruptedException{
        while(vehiculo==0){ // Si no hay comensales libres toca esperar
            this.wait();
        }
        System.out.println("El coche " + (id_f+1) + " es el vehiculo " + vehiculo);
        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gráfica:
        if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" Elcoche " + (id_f+1) + " es el vehiculo " + vehiculo);
        vehiculo--; // Conteo de comensales
    }

    public synchronized void soltarVehiculo(int id_f, Log log) throws InterruptedException{
        vehiculo++; // Conteo de comensales
        System.out.println("El Filósofo " + (id_f+1) + " ya NO es el comensal " + comensal);
        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gráfica:
        if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El Filósofo " + (id_f+1) + " ya NO es el comensal " + comensal);
        this.notify(); // Notificación al siguiente de que hay comensal disponible.
    }
}
