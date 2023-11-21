import java.util.concurrent.Semaphore;

class ReaderWriter {
    static Semaphore mutex = new Semaphore(1);
    static Semaphore writeBlock = new Semaphore(1);
    static int readCount = 0;

    static class Reader implements Runnable {
        public void run() {
            try {
                mutex.acquire();
                readCount++;
                if (readCount == 1) {
                    writeBlock.acquire();
                }
                mutex.release();

                // Reading the resource
                System.out.println("Reader is reading...");

                mutex.acquire();
                readCount--;
                if (readCount == 0) {
                    writeBlock.release();
                }
                mutex.release();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    static class Writer implements Runnable {
        public void run() {
            try {
                writeBlock.acquire();

                // Writing to the resource
                System.out.println("Writer is writing...");

                writeBlock.release();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        Thread reader1 = new Thread(new Reader());
        Thread reader2 = new Thread(new Reader());
        Thread writer1 = new Thread(new Writer());
        Thread reader3 = new Thread(new Reader());
        Thread writer2 = new Thread(new Writer());

         writer1.start() ;
        reader1.start();
                writer2.start();

        reader2.start();
        reader3.start();
    }
}
