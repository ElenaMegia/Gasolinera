package com.example.Gasolinera.logica;


import com.example.Gasolinera.gasolinera.Coches;
import com.example.Gasolinera.gasolinera.Barra_Gasolinera;
import com.example.Gasolinera.gasolinera.ColaDePago;
import com.example.Gasolinera.utilidades.Log;


import java.util.LinkedList;

public class Principal {

    public void Principal(String[] args) {

        // Se crea una lsita para la cola de pago:
        LinkedList<ColaDePago> colaDePago= new LinkedList();
        // Se crea el Array para contener los 50 coches:
        Coches[] coche = new Coches[50];
        // Se crea una sola instancia de Barra_Gasolinera:
        Barra_Gasolinera surtidor = new Barra_Gasolinera();
        // Se crea una sola instancia de Log:
        Log log = new Log();
        // Se crea la instancia del manejeador de excepciones para los Thread:
        ManejadorExcepciones manejador=new ManejadorExcepciones(log);


        // Se crean los 50 coches:
        for(int i=0; i<coche.length; i++){
            /* El filósofo coge el tenedor de la izquierda
             *  y el de la derecha se contabiliza con el módulo(%)
             *  porque cuando llega a cuatro el siguiente es cero
             */
            // Ahora al filósofo se le pasa: un ID, un tenedor Dercho, un tenedor Izdo, el comensal, los componentes gráficos correspondientes y un log
            coche[i] = new Coches(i, colaDePago.get(i), surtidor,  log);
        }

        // Se echa a andar todos los Coches:
        for(int i=0; i<coche.length; i++){
            coche[i].setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler) manejador);
            coche[i].start();
        }


    }





}
