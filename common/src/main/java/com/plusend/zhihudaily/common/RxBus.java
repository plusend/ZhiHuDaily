package com.plusend.zhihudaily.common;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by plusend on 16/5/29.
 */
public class RxBus {

    private static final RxBus INSTANCE = new RxBus();

    public static RxBus getInstance() {
        return INSTANCE;
    }

    private final Subject<Object, Object> rxBus = new SerializedSubject<>(PublishSubject.create());

    public void send(Object o) {
        rxBus.onNext(o);
    }

    public Observable<Object> toObserverable() {
        return rxBus;
    }

    public boolean hasObservers() {
        return rxBus.hasObservers();
    }
}
