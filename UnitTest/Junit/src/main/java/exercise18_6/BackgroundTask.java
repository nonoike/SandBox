package exercise18_6;

import java.util.concurrent.Executors;

public class BackgroundTask {
    // 何を実行するのかは実装クラスに委譲
    private Runnable task;

    public BackgroundTask(Runnable task) {
        this.task = task;
    }

    // どのように実行されるのかを担当
    public void invoke() {
        Executors.newSingleThreadExecutor().execute(task);
    }
}
