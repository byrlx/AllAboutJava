package com.lx.rxjava;

import com.lx.Util.Common;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by xzc on 2/24/16.
 */
public class Learn {

    Subscriber<String> glbSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            System.out.println("hi: " + s);
        }
    };

    /**
     * 创建一个Observable
     */
    public void createObservable() {
        Observable newObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) return;

                try {
                    subscriber.onNext("jack");
                } catch (Exception e) {
                    subscriber.onError(e);
                }

                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("value is " + s);
            }
        };

        newObservable.subscribe(subscriber);
    }

    /**
     * 使用defer延迟创建Observable
     */
    public void deferObservable() {
        SomeType testData = new SomeType();
//        Observable<String> observable = testData.valueObservable(); //结果是null
        Observable<String> observable = testData.deferObservable();
        testData.setValue("lucy");
        observable.subscribe(glbSubscriber);
    }

    /**
     * 基于数组创建observable, 数组元素一个一个发射
     */
    public void testObserval(final String... names) {
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("hi:" + s);
            }
        });
    }

    /**
     * 使用range创建一组integer的observable
     */
    public void rangeObserval(){
        Observable.range(12, 22).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("it's: "+integer);
            }
        });
    }

    /**
     * 间隔性发射
     */
    public void repeatObserval(){
        Observable.just("repeat")
                .repeat(10)
                .subscribe(glbSubscriber);
    }

    /**
     * 延迟发射
     */
    public void timerObserval(){
        System.out.println("x");
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println(aLong.toString() + "");
                    }
                });

        Common.noTerminal();
    }

    /**
     * 使用buffer收集item再发射
     */
    public void bufferObserval(){
        Observable.interval(1, TimeUnit.SECONDS)
                .buffer(3, 5)
                .subscribe(new Subscriber<List<Long>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onNext(List<Long> longs) {
                        Common.printList(longs);
                    }
                });

        Common.noTerminal();
    }

    /**
     * flatmap, 把一个个项目都分别转化成observal, 然后
     * 合并成一个大的observal, 这个大observal的子项目
     * 就是小的observal
     */
    public void flatMapObserval(){
        Integer[] test = {2, 5 ,7, 9};
        Observable.from(test)
                .flatMap(new Func1<Integer, Observable<String>>() {
                    @Override
                    public Observable<String> call(Integer integer) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return Observable.just("it's "+integer);
                    }
                }).subscribe(glbSubscriber);
    }

    public static void start() {
        Learn leann = new Learn();
//        leann.createObservable();
//        leann.deferObservable();
//        leann.rangeObserval();
//        leann.repeatObserval();
//        leann.timerObserval();
//        leann.bufferObserval();
//        leann.flatMapObserval();
        Common.readConfig(null);
    }

    class SomeType {
        private String value;

        public void setValue(String value) {
            this.value = value;
        }

        public Observable<String> valueObservable() {
            return Observable.just(value);
        }

        public Observable<String> deferObservable() {
            return Observable.defer(new Func0<Observable<String>>() {
                @Override
                public Observable<String> call() {
                    return Observable.just(value);
                }
            });
        }
    }
}
