package com.xml.frame.report.interpreter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleThreadPool implements Runnable{
    BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();
    boolean stop = false;
    int poolSize = 0;
    public SimpleThreadPool(int poolSize){
        this.poolSize = poolSize;
        for (int i = 0; i < poolSize; ++i){
            new Thread(this).start();
        }
    }

    public void runTask(Runnable task){
        tasks.offer(task);
    }

    public void stop(){
        stop = true;
        for (int i = 0; i < poolSize; ++i){
            tasks.offer(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    }

    @Override
    public void run() {
        while(!stop){
            try {
                Runnable runnable = tasks.take();
                runnable.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
