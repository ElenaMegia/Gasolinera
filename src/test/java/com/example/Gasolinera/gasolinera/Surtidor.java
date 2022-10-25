package com.example.Gasolinera.gasolinera;


import java.util.Random;
import com.example.Gasolinera.utilidades.Log;
import com.example.Gasolinera.vistas.Clase10Control;

public class Surtidor {

    private Random random = new Random();
    // ID del Surtidor
    private int id;
    // Está ocupado el surtidor o no?:
    private boolean libre = true;

    public Surtidor(int id){
        this.id = id;
    }

    public synchronized void entrarColaSurtidor(int id_f, Log log) throws InterruptedException{
        while(!libre)
            this.wait();
        System.out.println("El coche " + (id_f+1) + " reposta en el surtidor " + (id+1));
        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gráfica:
        if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El coche " + (id_f+1) + " reposta en el surtidor " + (id+1));
        libre = false;
    }

    public synchronized void soltarSurtidor(int id_f, Log log) throws InterruptedException {
        libre = true;
        System.out.println("El coche " + (id_f+1) + " suelta el surtidor " + (id+1));
        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gráfica:
        if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El coche " + (id_f+1) + " suelta el surtidor " + (id+1));
        this.notify();
    }

}
