package com.example.Gasolinera.logica;


import com.example.Gasolinera.gasolinera.Coches;
import com.example.Gasolinera.gasolinera.Barra_Gasolinera;
import com.example.Gasolinera.gasolinera.ColaDePago;
import com.example.Gasolinera.utilidades.Log;


import java.util.LinkedList;

public class Principal {


    public void Principal(String[] args) {
        Coches[] coche = new Coches[50];

        Barra_Gasolinera[] surtidor = new Barra_Gasolinera[4];
        // Se crea una sola instancia de Log:
        Log log = new Log();
        // Se crea la instancia del manejeador de excepciones para los Thread:
        ManejadorExcepciones manejador=new ManejadorExcepciones(log);

        //Se crean los surtidores
        for (int i = 0; i < surtidor.length ; i++) {
            surtidor[i] = new Barra_Gasolinera(i);
        }

        // Se crean los 50 coches:
        for(int i=0; i<coche.length; i++){
            // Ahora al filósofo se le pasa: un ID, un tenedor Dercho, un tenedor Izdo, el comensal, los componentes gráficos correspondientes y un log
            coche[i] = new Coches(i, surtidor[i], log);
        }


        // Se echa a andar todos los Coches:
        for(int i=0; i<coche.length; i++){
            coche[i].setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler) manejador);
            coche[i].start();
        }


    }





}
