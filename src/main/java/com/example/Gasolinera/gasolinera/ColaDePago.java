package com.example.Gasolinera.gasolinera;


import java.util.LinkedList;
import java.util.Random;
import com.example.Gasolinera.utilidades.Log;
import com.example.Gasolinera.gasolinera.Coches;
import com.example.Gasolinera.logica.Principal;

public class ColaDePago {

    private Random random =new Random ();

    private boolean libre =false;




    public ColaDePago(){

    }

    public static synchronized boolean entrarColaPago(int id_f, Log log) throws InterruptedException{

        while(!libre) {
            this.wait();
        }
        System.out.println("El coche " + (id_f+1) + " entra en la caja para pagar la gasolina " );

        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gráfica:
        libre = false;
        return true;
    }

    public static synchronized void salirDelaCola(int id_f, Log log) throws InterruptedException {
        libre = true;
        System.out.println("El coche " + (id_f+1) + " sale de la gasolinera " );
        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gráfica:
        this.notify();
    }



}
