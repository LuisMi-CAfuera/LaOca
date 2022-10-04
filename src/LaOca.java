import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

public class LaOca {
    private static class jugador implements Runnable {
        //Esta Constante la utilizo para que cuando un jugador llegue a cien o se pase ponerla en la condicion y si tengo que cambiarle no tener que cambiar todo
        final int OBJETIVO = 100;
        private static Logger logging = PSPLogger.getLogger(LaOca.class.getName());


        @Override
        public void run() {
            Random r = new Random();
            int pos = 0;



            while (pos != OBJETIVO) {
                try {
                    Thread.sleep(r.nextLong(2000) + 1);
                    //Aqui se lanzan los dados
                    int dados = r.nextInt(6) + 1;
                    pos = dados + pos;

                        logging.info("Locura el jugador " + Thread.currentThread().getName().toUpperCase() +" saco: " + dados);
                        logging.info("Le faltan:" + (OBJETIVO - pos));
                     if (pos > OBJETIVO) {
                        pos = OBJETIVO - (pos - OBJETIVO);
                         logging.info("Tiene que volver atras posicon "+ pos);
                    }

                } catch (InterruptedException e) {
                    logging.info("Tarea Interrumpida ");
                    break;
                }

            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Logger logging = PSPLogger.getLogger(LaOca.class.getName());
        final int TAM = 10;
        Thread[] t = new Thread[TAM];
        int i = 0;


        //Con este for lo que hacemos es inicializar todos los Threads a la vez que los metemos en el array
        for (i = 0; i < TAM; i++) {
            t[i] = new Thread(new jugador());
            t[i].start();
        }
        boolean flag = false;
        int ganador = 0;

            do {
            Thread.sleep(20);
            for (i = 0; i < TAM && !flag; i++) {
                if (!t[i].isAlive()) {
                    flag = true;
                    ganador = i;
                    //aqui si no esta vivo directamente interrumpimos todos los jugadores

                    for (i = 0; i < TAM; i++) {
                        if (i != ganador) {
                            t[i].interrupt();
                        }
                        t[i].join();
                    }
                    break;
                }
            }

        } while(!flag);

            //Aqui ponemos que es el Final y ponemos que se muestre el ganador
        logging.info("Final del programa");
        logging.info("Ha ganado el jugador" + t[ganador].getName());

    }
}
