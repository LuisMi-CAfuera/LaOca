import java.util.ArrayList;
import java.util.Random;

public class LaOca {
    private static class jugador implements Runnable {
        final int OBJETIVO = 50;

        @Override
        public void run() {
            Random r = new Random();
            int pos = 0;
            while (pos != OBJETIVO) {
                try {
                    Thread.sleep(r.nextLong(2000) + 1);
                    int dados = r.nextInt(6) + 1;
                    pos = dados + pos;
                    if (pos < OBJETIVO) {
                        System.out.println("Locura el jugador saco: " + dados);
                        System.out.println("Le faltan:" + (OBJETIVO - pos));
                    } else if (pos > OBJETIVO) {
                        System.out.println("Tiene que volver atras");
                        pos = OBJETIVO - (pos - OBJETIVO);
                    } else {
                        System.out.println(Thread.currentThread().getName().toUpperCase() + " Llego al final");
                    }

                } catch (InterruptedException e) {
                    System.out.println("Tarea Interrumpida ");
                    break;
                }

            }

        }
    }

    public static void main(String[] args) {
        final int TAM = 4;
        Thread[] t = new Thread[TAM];
        for(int i =0;i==TAM;i++){
            t[i]= new Thread(new jugador());
            t[i].start();
        }

        boolean flag = true;

        while (flag) {
            for (Thread i : jugadores) {
                Thread.sleep(20);
                if (!i.isAlive()) {
                    for (Thread e : jugadores) {
                        e.interrupt();
                    }
                }

                flag=false;
            }

        }
    }
}
