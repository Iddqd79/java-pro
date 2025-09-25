package pro.java;

/**
 * Home task-3
 *
 */
public class App {
    public static void main(String[] args) {
        pro.java.CustomThreadPool customThreadPool = new CustomThreadPool(3);

        for (int i = 0; i < 12; i++) {

            int finalI = i;
            customThreadPool.execute(() -> {
                boolean isLongTask = finalI % 3 == 0;
                try {

                    Thread.sleep(isLongTask ? 1000 : 100);
                    System.out.println((isLongTask ? "long" : "regular") + "task" + finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            if (i > 6) {
                customThreadPool.shutdown();
            }
        }
        customThreadPool.shutdown();
    }
}

