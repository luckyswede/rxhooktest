package rxhooktest;

import rx.functions.Action0;
import rx.functions.Func1;

public final class Rx1OnScheduleAction implements Func1<Action0, Action0> {
    @Override
    public Action0 call(final Action0 action0) {
        return () -> {
            System.out.println("In Rx1OnScheduleAction");
            action0.call();
        };
    }
}