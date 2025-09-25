package pro.java;

import java.util.LinkedList;

/**
 * Собственная реализация пула потоков
 */
public class CustomThreadPool {

    private final int poolSize;
    private final LinkedList<Runnable> taskQueue;
    private final WorkerThread[] workerThreads;
    //private final AtomicBoolean isShutdown;
    private volatile boolean acceptNewTasks;

    /**
     * Конструктор пула потоков
     * @param poolSize количество рабочих потоков
     */
    public CustomThreadPool(int poolSize) {
        if (poolSize <= 0) {
            throw new IllegalArgumentException("Pool size must be positive");
        }

        this.poolSize = poolSize;
        this.taskQueue = new LinkedList<>();
        this.workerThreads = new WorkerThread[poolSize];
        //  this.isShutdown = new AtomicBoolean(false);
        this.acceptNewTasks = true;

        // Инициализация и запуск рабочих потоков
        initializeThreads();
    }

    /**
     * Инициализация и запуск всех рабочих потоков
     */
    private void initializeThreads() {
        for (int i = 0; i < poolSize; i++) {
            workerThreads[i] = new WorkerThread("CustomThreadPool-Worker-" + (i + 1));
            workerThreads[i].start();
        }
    }

    /**
     * Добавление задачи в очередь на выполнение
     * @param task задача для выполнения
     * @throws IllegalStateException если пул был остановлен
     */
    public void execute(Runnable task) {
        if (task == null) {
            throw new NullPointerException("Task cannot be null");
        }

        if (!acceptNewTasks) {
            throw new IllegalStateException("ThreadPool has been shut down");
        }

        synchronized (taskQueue) {
            taskQueue.addLast(task);
            taskQueue.notify(); // Уведомляем один ожидающий поток
        }
    }

    /**
     * Остановка пула потоков
     * После вызова новые задачи не принимаются
     * Все потоки завершат работу после выполнения оставшихся задач
     */
    public void shutdown() {
        acceptNewTasks = false;

        synchronized (taskQueue) {
            taskQueue.notifyAll(); // Уведомляем все ожидающие потоки
        }
    }

    /**
     * Ожидание завершения всех потоков
     * Блокирует текущий поток до завершения всех рабочих потоков
     */
    public void awaitTermination() throws InterruptedException {
        for (WorkerThread worker : workerThreads) {
            if (worker != null && worker.isAlive()) {
                worker.join();
            }
        }
    }


    /**
     * Рабочий поток для выполнения задач
     */
    private class WorkerThread extends Thread {

        private volatile boolean isWorking = false;

        public WorkerThread(String name) {
            super(name);
            setDaemon(false); // Не daemon-поток
        }

        @Override
        public void run() {
            Runnable task;

            while (true) {
                task = null;

                synchronized (taskQueue) {
                    // Ожидаем задачу или сигнал к завершению
                    while (taskQueue.isEmpty() && acceptNewTasks) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    // Если есть задача, берем её
                    if (!taskQueue.isEmpty()) {
                        task = taskQueue.removeFirst();
                    } else if (!acceptNewTasks) {
                        // Нет задач и shutdown инициирован
                        break;
                    }
                }

                // Выполняем задачу
                if (task != null) {
                    try {
                        isWorking = true;
                        task.run();
                    } catch (Exception e) {
                        // Логируем ошибку, но не останавливаем поток
                        System.err.println("Task execution failed in " + getName() + ": " + e.getMessage());
                        e.printStackTrace();
                    } finally {
                        isWorking = false;
                    }
                }
            }
        }

    }
}
