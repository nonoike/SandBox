package exercise18_6;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import exercise18_6.BackgroundTask;

public class BackgroundTaskTest {
    private BackgroundTask sut;

    @Rule
    public Timeout timeout = new Timeout(3000, TimeUnit.MILLISECONDS);

    @Test
    public void invokeで別スレッドで実行される() throws Exception {
        final AtomicReference<String> backgroundThreadName = new AtomicReference<>();
        final CountDownLatch latch = new CountDownLatch(1);
        Runnable task = () -> {
            backgroundThreadName.set(Thread.currentThread().getName());
            latch.countDown();
        };
        sut = new BackgroundTask(task);

        sut.invoke();
        latch.await();

        assertThat(backgroundThreadName.get(), is(not(Thread.currentThread().getName())));
    }
}
