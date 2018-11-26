package rxhooktest;

import io.micronaut.runtime.Micronaut;
import io.reactivex.plugins.RxJavaPlugins;
import rx.plugins.RxJavaHooks;

import java.util.concurrent.atomic.AtomicInteger;

public class Application {
    static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) {

        RxJavaHooks.setOnScheduleAction(new Rx1OnScheduleAction());
        RxJavaPlugins.setScheduleHandler(new Rx2OnScheduleFunction());
        Micronaut.run();
    }
}