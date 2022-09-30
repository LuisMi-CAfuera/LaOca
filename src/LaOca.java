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
        ArrayList<Thread> jugadores = new ArrayList<>();
        jugadores.add(new Thread(new jugador()));
        jugadores.add(new Thread(new jugador()));
        jugadores.add(new Thread(new jugador()));
        jugadores.add(new Thread(new jugador()));

        boolean flag = true;

        for (Thread a : jugadores) {
            a.start();
        }

        while (flag) {
            for (Thread i : jugadores) {
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
