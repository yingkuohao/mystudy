package com.mylearn.cache;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-5
 * Time: ????3:05
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class CacheUtil4<K, V> implements Computable<K, V> {

    private final ConcurrentHashMap<K, Future<V>> map = new ConcurrentHashMap<K, Future<V>>();  //value???future

    private final Computable computable;

    public Computable getComputable() {
        return computable;
    }

    public CacheUtil4(Computable computable) {
        this.computable = computable;
    }

    /**
     *
     * ???  map.putIfAbsent 4???}???????put???????????????????????????????????????
     * ??????????
     *
     * @param arg
     * @return
     * @throws InterruptedException
     */
    public V compute(final K arg) throws InterruptedException {
        while (true) {
            Future<V> future = map.get(arg);
            if (future == null) {
                //???future???????????????new???????????
                Callable<V> callable = new Callable<V>() {
                    public V call() throws InterruptedException {
                        Object newValue = computable.compute(arg);
                        return (V) newValue;
                    }
                };

                FutureTask<V> futureTask = new FutureTask<V>(callable); //?????????????FutureTask

                future = map.putIfAbsent(arg, futureTask);       //?????? ????????put
                if (future == null) {
                    //????��???????????????put????????????????????future.get????
                    future = futureTask;    //??future?????????????????? if (future != null) ??
                    futureTask.run();    //????????????call?????????compute
                }
            }
            //????????????????????????
            try {
                return future.get();  //????????????b??????????????????????????????
            } catch (ExecutionException e) {
                throw new InterruptedException(e.getMessage());
            }
        }
    }
}
