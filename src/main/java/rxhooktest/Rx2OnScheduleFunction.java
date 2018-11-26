package rxhooktest;

import io.reactivex.functions.Function;

public final class Rx2OnScheduleFunction implements Function<Runnable, Runnable> {
    @Override
    public Runnable apply(Runnable runnable) {
        return () -> {
            System.out.println("In Rx2OnScheduleFunction");
                runnable.run();
            };
    }
}