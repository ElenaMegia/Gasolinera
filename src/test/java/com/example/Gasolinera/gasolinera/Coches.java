package com.example.Gasolinera.gasolinera;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;
import logica.Principal;
import utilidades.Log;
import vistas.Clase10Control;

public class Coches extends Thread{

    //Variable para generar numeros aleatorios:
    private Random random = new Random();

    //Id de los coches
    private int id;

    // Variables para la cola para pagar "tenedor"
    private Surtidor surtidor;

    private Barra_Gasolinera vehiculo;

    private Log log; // Para escribir en el Log


    // Variable pública y estática para que se pueda detener el método run() de esta clase:
    public static boolean finalizado = false;

    public Coches(int id, Surtidor surtidor, Barra_Gasolinera vehiculo,  Log log){
        this.id = id;
        this.surtidor=surtidor;
        this.vehiculo=vehiculo;
        this.log = log; // Puede ser null y por consiguiente no escribir el log
    }


    @Override
    public void run() {

        while(true){ // Se repite infinitamente While
            try { // try / catch
                // Obtener el cochce para que entre:
                vehiculo.entradaVehiculo(id, log);
                // Obtener entrada en la cola de pago:
                if (!surtidor.entrarColaSurtidor(id, log)){
                    // -----------------------------------------------------------------------------------------------------------------
                    // Si no se consigue surtidor: el coche no podra entrar a la gasolinera, tendra que volver a casilla de salida y volver a pasar la entrada:
                    System.out.println("El coche " + (id+1) + " tendrá que esperar a que algun surtidor este libre " + (id+1) + " tendrá que esperar a que los coches de los surtidores terminen de repostar.");
                    // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gráfica:
                    if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El coche " + (id+1) + " tendrá que esperar a que algun surtidor este libre " + (id+1) + " tendrá que esperar a que los coches de los surtidores terminen de repostar.");
                    // Como no ha conseguido el Tenedor izquierdo suelta el comensal
                    surtidor .soltarSurtidor(id, log);
                    vehiculo.soltarVehiculo(id, log);
                    // Y ahora el Filósofo piensa *********************
                    System.out.println("El coche " + (id+1) + " está esperando su turno.");
                    if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El coche " + (id+1) + " está esperando su turno.");
                    try {
                        // El tiempo que tarda el filósofo en pensar, entre 100 y 1000 milisegundos:
                        Coches.sleep(random.nextInt(1000) + 100);
                    } catch (InterruptedException ex) {
                        System.out.println("Error. Descripción: " + ex.toString());
                        if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog("\n\n Error. Descripción: " + ex.toString() + "\n\n");
                    }
                    // Fin de espera del coche *********************
                    continue; // Se vuelve a poner en la casilla de salida y volver a pedir la entrada en la gasolinera.
                    // -----------------------------------------------------------------------------------------------------------------
                }
                // Si ha conseguido repostar en el surtidor. El coche tiene que ir a la cola para pagar:

                // Y ahora el reposta  =========================================================================================

                System.out.println("El coche " + (id+1) + " está repostando.");
                if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El coche " + (id+1) + " está repostando.");
                // Simular el tiempo que tarda el coche en repostar, entre 5 y 10 minutos que los pasamos a  nanosegundos:
                try {
                    sleep(random.nextInt(5) + 10);
                } catch (InterruptedException ex) {
                    System.out.println("Error. Descripción: " + ex.toString());
                    if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog("\n\n Error. Descripción: " + ex.toString() + "\n\n");
                } // Fin de Simular el tiempo que tarda el coche en repostar
                // Fin de Ahora el Coche paga ====================================================================================
                // Suelta el surtidor:
                surtidor.soltarSurtidor(id, log);


                //HAY QUE PONER LA COLA

                // Suelta el vehiculo:
                vehiculo.soltarVehiculo(id, log);
                // Ahora el Coche espera *********************************************************************************************
                System.out.println("El coche " + (id+1) + " está esperando.");
                if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El coche " + (id+1) + " está esperando.");
                // El tiempo que tarda el filósofo en pensar, entre 100 y 1000 milisegundos:
                try {
                    Filosofo.sleep(random.nextInt(1000) + 100);
                } catch (InterruptedException ex) {
                    System.out.println("Error. Descripción: " + ex.toString());
                    if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog("\n\n Error. Descripción: " + ex.toString() + "\n\n");
                }
                // Fin de Ahora el Filósofo piensa **************************************************************************************
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                System.err.println("Se ha producido un error. Descripción: " + ex.toString());
                try {
                    if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog("\n\n Se ha producido un error. Descripción: " + ex.toString() + "\n\n");
                } catch (InterruptedException ex1) {
                    Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } // Fin del try / catch

            if(finalizado){ // Si se ha pulsado el botón en la interface de 'Pausar' (public static boolean finalizado = true):
                break; // Se sale
            }

        }  // Fin de Se repite infinitamente While

        // Se ha pulsado el botón de la interface 'Pausar' (public static boolean finalizado = true):
        System.out.println("La cena ha terminado para todos: El Filósofo " + (id+1) +" se ha puesto a pensar.\n\nPulsar Iniciar para continuar.\n\n");
        try {
            if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" La cena ha terminado para todos: El Filósofo " + (id+1) +" se ha puesto a pensar.\n\n Pulsar Iniciar para continuar.\n\n");
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


}
