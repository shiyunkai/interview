package com.interview.thread;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther: shiyunkai
 * @Date: 2019/06/17 09:39
 * @Description: ExecutorService的使用
 *  execute(Runnable)
 *  submit(Runnable)
 *  submit(Callable)
 *  invokeAny(...)
 *  invokeAll(...)
 */
public class ExecutorServiceDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();

        // submit(Runnable)
        // Future future = service.submit(() -> System.out.println("Asynchronous task"));

        // submit(Callable)
        // Callable 的结果可以通过 submit(Callable) 方法返回的 Future 对象进行获取。
        // submitCallable(service);

        // invokeAny(...)
        // invokeAny(service);

        // invokeAll(...)
        invokeAll(service);
    }

    private static void invokeAny(ExecutorService service) throws InterruptedException, ExecutionException {
        HashSet<Callable<String>> callables = new HashSet<>();
        callables.add(()->"Task 1");
        callables.add(()->"Task 2");
        callables.add(()->"Task 3");
        callables.add(()->"Task 4");
        String result = service.invokeAny(callables);
        System.out.println("result = "+result);
    }

    private static void invokeAll(ExecutorService service) throws InterruptedException, ExecutionException {
        HashSet<Callable<String>> callables = new HashSet<>();
        callables.add(()->"Task 1");
        callables.add(()->"Task 2");
        callables.add(()->"Task 3");
        callables.add(()->"Task 4");
        List<Future<String>> futures = service.invokeAll(callables);
        for(Future<String> f: futures){
            System.out.println("futuer.get = "+f.get());
        }
    }

    private static void submitCallable(ExecutorService service) throws InterruptedException, ExecutionException {
        Future future = service.submit(()->{
           System.out.println("Asynchronous Callable");
           return "Callable Result";
        });
        System.out.println("future.get() = "+future.get());
    }
}
