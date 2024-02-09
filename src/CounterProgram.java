public class CounterProgram {
    static Counter counter = new Counter();
    synchronized void countUp()
    {
        // synchronized method
        for (int i = 1; i <= 20; i++) {
            System.out.println(i);
            counter.increment();
            try {
                Thread.sleep(500);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    synchronized void countDown()
    {
        // synchronized method
        for (int i = 20; i >= 0; --i) {
            System.out.println(i);
            counter.decrement();
            try {
                Thread.sleep(500);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {

        final CounterProgram obj = new CounterProgram();
        Thread a = new Thread() {
            public void run() { obj.countUp(); }
        };

        Thread b = new Thread() {
            public void run() { obj.countDown(); }
        };

        a.start();
        b.start();

        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println( "Counter Value: " + counter.getCount());


    }
    // Counter class to hold the count
    static class Counter {
        private int count = 0;

        public void increment() {
            count++;
        }

        public void decrement() {
            count--;
        }

        public int getCount() {
            return count;
        }
    }
}
