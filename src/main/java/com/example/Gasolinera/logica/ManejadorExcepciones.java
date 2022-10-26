package com.example.Gasolinera.logica;

import com.example.Gasolinera.utilidades.Log;


public class ManejadorExcepciones {


    public class uncaughtException implements Thread.UncaughtExceptionHandler {
        //implementa el método uncaughtException()
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.printf("Thread que lanzó la excepción: %s \n", t.getName());
            //muestra en consola el hilo que produce la exceción
            e.printStackTrace();
            //muestra en consola la pila de llamadas

            // Añado este código para que los saque en el Log de la interface gráfica

            // Fin Añado este código
        }
    }

    public ManejadorExcepciones(Log log) {
        this.log = log;
    }

    // Variable que recibe la clase Log
    Log log;
    // Fin Añado este código
}