package com.example.Gasolinera.gasolinera;

import java.util.Random;

import com.example.Gasolinera.utilidades.Log;


public class Coches extends Thread{

    //Variable para generar numeros aleatorios:
    private Random random = new Random();

    //Id de los coches
    private int id;

    // Variables para la cola para pagar "tenedor"
    private ColaDePago colaDePago;

    private Barra_Gasolinera surtidor;

    private Log log; // Para escribir en el Log


    // Variable pública y estática para que se pueda detener el método run() de esta clase:
    public static boolean finalizado = false;

    public Coches(int id, ColaDePago colaDePago, Barra_Gasolinera surtidor, Log log){
        this.id = id;
        this.colaDePago=colaDePago;
        this.surtidor = surtidor;
        this.log = log; // Puede ser null y por consiguiente no escribir el log
    }


    @Override
    public void run() {

        while(true){ // Se repite infinitamente While
            try { // try / catch
                // Obtener el cochce para que entre:
                surtidor.entradaASurtidor(id, log);
                // Obtener entrada en la cola de pago:
                if (!colaDePago.entrarColaPago(id, log)){
                    // -----------------------------------------------------------------------------------------------------------------
                    // Si no se consigue surtidor: el coche no podra entrar a la gasolinera, tendra que volver a casilla de salida y volver a pasar la entrada:
                    System.out.println("El coche " + (id+1) + " tendrá que esperar a que algun surtidor este libre " + (id+1) + " tendrá que esperar a que los coches de los surtidores terminen de repostar.");
                    // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gráfica:
                    // Como no ha conseguido el Tenedor izquierdo suelta el comensal
                    surtidor.salirSurtidor(id, log);
                    // Y ahora el Filósofo piensa *********************
                    System.out.println("El coche " + (id+1) + " está esperando su turno.");
                    try {
                        // El tiempo que tarda el filósofo en pensar, entre 100 y 1000 milisegundos:
                        Coches.sleep(random.nextInt(1000) + 100);
                    } catch (InterruptedException ex) {
                        System.out.println("Error. Descripción: " + ex.toString());
                    }
                    // Fin de espera del coche *********************
                    continue; // Se vuelve a poner en la casilla de salida y volver a pedir la entrada en la gasolinera.
                    // -----------------------------------------------------------------------------------------------------------------
                }
                // Si ha conseguido repostar en el surtidor. El coche tiene que ir a la cola para pagar:

                // Y ahora el reposta  =========================================================================================

                System.out.println("El coche " + (id+1) + " está repostando.");

                // Simular el tiempo que tarda el coche en repostar, entre 5 y 10 minutos que los pasamos a  nanosegundos:
                try {
                    sleep(random.nextInt(5000) + 10000);
                } catch (InterruptedException ex) {
                    System.out.println("Error. Descripción: " + ex.toString());

                } // Fin de Simular el tiempo que tarda el coche en repostar
                // Fin de Ahora el Coche paga ===================================================================================
                System.out.println("El cliente del coche " + (id+1) + " va a pagar.");
                colaDePago.entrarColaPago(id,log);
                try {
                    sleep(3000);
                } catch (InterruptedException ex) {
                    System.out.println("Error. Descripción: " + ex.toString());
                }
                // Sale de la cola:
                colaDePago.salirDelaCola(id, log);
                // Suelta el vehiculo:
                surtidor.salirSurtidor(id, log);
                // Ahora el Coche espera *********************************************************************************************
                System.out.println("El coche " + (id+1) + " sale de la gasolinera.");

                // Fin de Ahora el Filósofo piensa **************************************************************************************
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                System.err.println("Se ha producido un error. Descripción: " + ex.toString());

            } // Fin del try / catch

            if(finalizado){ // Si se ha pulsado el botón en la interface de 'Pausar' (public static boolean finalizado = true):
                break; // Se sale
            }

        }  // Fin de Se repite infinitamente While

        // Se ha pulsado el botón de la interface 'Pausar' (public static boolean finalizado = true):
        System.out.println("La gasolinera ha cerrado,todos los coches han repostado \nPulsar Iniciar para continuar.\n\n");



    }


}
