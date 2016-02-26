package com.lx.rxjava;

import com.lx.Util.Common;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
     * many
     */
    public void many() {
        Observable<Integer> ob1 = Observable.just(1, 2, 3);
        Observable<String> ob2 = Observable.just("hi", "yha", "helo");

        Observable<String> omap1 = ob1.flatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(Integer integer) {
                return Observable.just("a+" + integer);
            }
        });

        Observable.concat(ob2, omap1)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Common.printThread(s);
                    }
                });
        Common.noTerminal();
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
    public void rangeObserval() {
        Observable.range(12, 22).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("it's: " + integer);
            }
        });
    }

    /**
     * 间隔性发射
     */
    public void repeatObserval() {
        Observable.just("repeat")
                .repeat(10)
                .subscribe(glbSubscriber);
    }

    /**
     * 延迟发射
     */
    public void timerObserval() {
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
    public void bufferObserval() {
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
    public void flatMapObserval() {
        Integer[] test = {2, 5, 7, 9};
        Observable.from(test)
                .flatMap(new Func1<Integer, Observable<String>>() {
                    @Override
                    public Observable<String> call(Integer integer) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return Observable.just("it's " + integer);
                    }
                }).subscribe(glbSubscriber);
    }

    /**
     * orders
     */
    public void firstObserval() {
        String[] test = new String[]{"1", "a", "卡"};
        Observable many = Observable.from(test);
//        many.first().subscribe(glbSubscriber);
        many.last().subscribe(glbSubscriber);
        many.first().subscribe(glbSubscriber);
    }

    /**
     * merge
     */
    public void mergeObserval() {
        Observable<Integer> ob2 = Observable.just(2, 4, 6).subscribeOn(Schedulers.computation());
        Observable<Integer> ob1 = Observable.just(1, 3, 5).subscribeOn(Schedulers.io());

        List<Integer> l1 = Arrays.asList(new Integer[]{1, 5, 9});
        Observable[] observables = new Observable[]{Observable.just(1), Observable.just(5), Observable.just(9)};
        List<Integer> l2 = Arrays.asList(new Integer[]{2, 6, 10});
//        Observable.merge(ob1, ob2)
        Observable.merge(observables)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Common.printThread(integer + "");

                        /**
                         * 如果不加这段代码, ob1和ob2在自己的scheduler中执行, 先执行ob1, 后执行ob2
                         * 加了这段代码后,不确定在哪个scheduler中执行及执行顺序
                         */
                        try {
                            Common.printThread("sleep ");
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

        Common.noTerminal();
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
//        leann.firstObserval();
//        Common.readConfig(null);
//        leann.mergeObserval();
        leann.many();
    }

    static class SomeType {
        private String value;

        public SomeType() {
        }

        public String getValue() {
            return value;
        }

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
