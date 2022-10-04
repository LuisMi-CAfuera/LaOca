import java.util.ArrayList;
import java.util.Random;

public class LaOca {
    private static class jugador implements Runnable {
        final int OBJETIVO = 10;

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
                        System.out.println("Locura el jugador " + Thread.currentThread().getName().toUpperCase() +" saco: " + dados);
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

    public static void main(String[] args) throws InterruptedException{
        final int TAM = 10;
        Thread[] t = new Thread[TAM];
        int i =0;

        for(i =0;i<TAM;i++){
            t[i]= new Thread(new jugador());
            t[i].start();
        }
        boolean flag = false;
        int ganador =0;

        while(flag){
            Thread.sleep(20);
            for (i=0;i < TAM && !flag; i++){
                if(!t[i].isAlive()){
                    flag = true;
                    ganador = i;
                }
            }

        }
        for (i=0;i<TAM;i++){
            if(i!=ganador){
                t[i].interrupt();
            }
            t[i].join();
        }

    }
}
