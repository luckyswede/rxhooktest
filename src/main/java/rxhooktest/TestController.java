package rxhooktest;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

@Controller("/")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Get("/rx1")
    Flowable<? extends HttpResponse<?>> rx1() {
        Observable<String> observable = Observable
                .just("a")
                .subscribeOn(Schedulers.newThread())
                .map(s -> s + "b")
                .map(s -> s + "c");
        return Flowable
                .fromPublisher(subscriber -> observable
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onComplete();
                            }

                            @Override
                            public void onError(Throwable e) {
                                subscriber.onError(e);
                            }

                            @Override
                            public void onNext(String s) {
                                subscriber.onNext(s);
                            }
                        })
                ).map(s -> HttpResponse.ok(s));
    }

    @Get("/rx2")
    Flowable<? extends HttpResponse<?>> rx2() {
        return Flowable
                .just("a")
                .subscribeOn(io.reactivex.schedulers.Schedulers.newThread())
                .map(s -> s + "b")
                .map(s -> s + "c")
                .map(s -> HttpResponse.ok(s));
    }
}
