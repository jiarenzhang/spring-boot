package com.gfl.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Category Twitter的分布式自增ID算法Snowflake
 * @Author Mangal(729800096@qq.com)
 * @Create 16/4/21
 * @Modifier
 * @modificationDate
 * @Modify
 */
public class IdWorker {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final long workerId;
    private final static long twepoch      = 1361753741828L;
    private               long sequence     = 0L;
    private final static long workerIdBits = 4L;
    private final static long maxWorkerId  = -1L ^ -1L << workerIdBits;
    private final static long sequenceBits = 10L;

    private final static long workerIdShift      = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    private final static long sequenceMask       = -1L ^ -1L << sequenceBits;

    private long lastTimestamp = -1L;

    public IdWorker(final long workerId) {
        super();
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format(
                    "worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & sequenceMask;
            if (this.sequence == 0) {
                logger.info("###########" + sequenceMask);
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(String.format(
                        "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                        this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        long nextId = ((timestamp - twepoch << timestampLeftShift))
                      | (this.workerId << workerIdShift) | (this.sequence);
        //  System.out.println("timestamp:" + timestamp + ",timestampLeftShift:"
        //       + timestampLeftShift + ",nextId:" + nextId + ",workerId:"
        //       + workerId + ",sequence:" + sequence);
        return nextId;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) throws InterruptedException {
        IdWorker worker2 = new IdWorker(2);
//        for (int i = 0; i < 5000; i++) {
            System.out.println( ":" + worker2.nextId());
//        }
//        System.out.println(worker2.nextId());
//        Thread.sleep(1000L);
//        System.out.println(worker2.nextId());
    }
}
