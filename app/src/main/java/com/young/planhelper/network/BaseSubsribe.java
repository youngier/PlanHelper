package com.young.planhelper.network;

import android.util.Log;

import rx.Subscriber;

/**
 * 观察者
 *
 * @author finddreams
 * @address http://blog.csdn.net/finddreams
 */
public abstract class BaseSubsribe<T> extends Subscriber<T> {
    private static final String TAG = "BaseSubsribe";


    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

    }

    @Override
    public void onNext(T t) {
        Log.i(TAG, "response" + t.toString());

        onSuccess(t);
    }

    @Override
    public void onCompleted() {
        Log.i(TAG, "onCompleted");

    }

    public abstract void onSuccess(T result);

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Log.i(TAG, "onError" + e.getMessage());

    }

}