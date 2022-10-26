package com.example.Gasolinera.gasolinera;


import java.util.Random;
import com.example.Gasolinera.utilidades.Log;


public class ColaDePago {

    private Random random =new Random ();

    private int id; //Id cola de pago
    private boolean libre =true;


    public ColaDePago(int id){
        this.id=id;
    }

    public synchronized boolean entrarColaPago(int id_f, Log log) throws InterruptedException{
        while(!libre)
            this.wait();
        System.out.println("El coche " + (id_f+1) + " entra en la caja para pagar la gasolina " + (id+1));

        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gráfica:
        libre = false;
        return true;
    }

    public synchronized void salirDelaCola(int id_f, Log log) throws InterruptedException {
        libre = true;
        System.out.println("El coche " + (id_f+1) + " sale de la gasolinera " + (id+1));
        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gráfica:
        this.notify();
    }



}
