package com.cz.lookportnews;

import android.util.Log;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 14221 on 2018/2/8.
 */

public class RxTest {
    private static final String TAG = "RxTest";

    @Test
    public void testRx(){
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                Log.d(TAG, "ObservableEmitter : 1 " );
                System.out.println("ObservableEmitter 1");
                e.onNext(1);
                System.out.println("ObservableEmitter 2");
//                Log.d(TAG, "ObservableEmitter : 2 " );
               e.onNext(2);
                System.out.println("ObservableEmitter 3");
//                Log.d(TAG, "ObservableEmitter : 3 " );
                e.onNext(3);
                e.onComplete();
                System.out.println("ObservableEmitter 4");
 //               Log.d(TAG, "ObservableEmitter : 4 " );
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {

            private Integer i;

            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "onSubscribe: ");
                System.out.println("onSubscribe");
                disposable=d;
            }

            @Override
            public void onNext(Integer integer) {

                if(integer==2)
                {
                    disposable.dispose();
                }
                System.out.println("onNext "+ integer);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("SubScribe onComplete");
            }
        });
    }

}
