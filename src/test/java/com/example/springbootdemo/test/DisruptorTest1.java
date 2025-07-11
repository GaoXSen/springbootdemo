package com.example.springbootdemo.test;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DisruptorTest1 {
    // 定义事件类
    public static class LongEvent {
        private long value;

        public void set(long value) {
            this.value = value;
        }

        public long get() {
            return value;
        }
    }

    // 定义事件工厂
    public static class LongEventFactory implements EventFactory<LongEvent> {
        @Override
        public LongEvent newInstance() {
            return new LongEvent();
        }
    }

    // 定义事件处理器
    public static class LongEventHandler implements EventHandler<LongEvent> {
        @Override
        public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
            System.out.println("Event: " + event.get());
        }
    }

    public static void main(String[] args) {
        // 创建线程池
        Executor executor = Executors.newCachedThreadPool();

        // 创建事件工厂
        LongEventFactory eventFactory = new LongEventFactory();

        // 指定 RingBuffer 大小，必须是 2 的整数次幂
        int bufferSize = 1024;

        // 创建 Disruptor 对象
        Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, bufferSize, executor);

        // 设置事件处理器
        disruptor.handleEventsWith(new LongEventHandler());

        // 启动 Disruptor
        disruptor.start();

        // 获取 RingBuffer
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // 生产事件
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; l < 100; l++) {
            bb.putLong(0, l);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
        }

        // 关闭 Disruptor
        disruptor.shutdown();
    }
}
