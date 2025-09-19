package pro.java;

import java.util.LinkedList;
import java.util.concurrent.Executor;

public class CustomThreadPool implements Executor {
    private final LinkedList<Runnable> workQueue = new LinkedList<>();
    private volatile boolean isRunning = true;

    public CustomThreadPool(int nThreads) {
        for (int i = 0; i < nThreads; i++) {
            new Thread(new TaskWorker()).start();
        }
    }

    @Override
    public void execute(Runnable command) {
        if (!isRunning) {
            throw new IllegalStateException("Executor is shutdown");
        }
        synchronized (workQueue) {
            workQueue.offer(command);
        }
    }

    public void shutdown() {
        isRunning = false;
    }

    private final class TaskWorker implements Runnable {

        @Override
        public void run() {
            while (isRunning) {
                synchronized (workQueue) {
                    Runnable nextTask = workQueue.poll();
                    if (nextTask != null) {
                        nextTask.run();
                    }
                }
            }
        }
    }
}