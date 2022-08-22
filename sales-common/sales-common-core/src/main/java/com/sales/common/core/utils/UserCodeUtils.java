package com.sales.common.core.utils;

/**
 * @author wuyingqiang
 * @desc: TODO 用户编号ID生产工具，每秒可产生8个不重复，年产2.5亿个。初始长度为7,10年后长度为10位
 *
 * @date 2022/7/13 21:12
 */
public class UserCodeUtils {
    /** 开始时间截 (2021-12-14) 1639411200*/
    private final static long twepoch = 1639411200L;
    /** 平台ID所占的位数 */
    private final static long platformIdBits = 3;

    /** 支持的最多的平台数为31 */
    private final static long maxplatformIdBits = -1L ^ (-1L << platformIdBits);

    /** 序列在id中占的位数 */
    private final static long sequenceBits = 4;


    /** 平台标识id向左移8位(8) */
    private final long platformIdShift = sequenceBits ;

    /** 时间截向左移14位(6+8) */
    private final long timestampLeftShift = sequenceBits + platformIdBits;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final static long sequenceMask = -1 ^ (-1 << sequenceBits);


    /** 平台ID(0~31) */
    private long platformId=1L;

    /** 毫秒内序列(0~15) */
    private static long  sequence = 0;

    /** 上次生成ID的时间截 */
    private  static long lastTimestamp = -1L;
//    public UserCodeUtils(long platformId) {
//        if (platformId > maxplatformIdBits || platformId < 0) {
//            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxplatformIdBits));
//        }
//        this.platformId = platformId;
//    }

    /**
     * 生成一个用户Code
     * @return 获取
     */
    public static synchronized long generateCode() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0;
        }
        //上次生成ID的时间截
        lastTimestamp = timestamp;
//        return ((timestamp - twepoch) << timestampLeftShift) //
//                | (platformId << platformIdShift) //
//                | sequence;
        return ((timestamp - twepoch)<< sequenceBits)| sequence;
    }
    /**
     * 阻塞到下一秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以秒为单位的当前时间
     * @return 当前时间(秒)
     */
    protected  static long timeGen() {
        return System.currentTimeMillis()/1000L;
    }

    /** 测试 10000个id的生成效率 */
    public static void main(String[] args) {
        System.out.println("cur:"+ DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS,"2021-12-14 00:00:00").getTime());
        long start=System.currentTimeMillis();
        System.out.println("start:"+start);
//        UserCodeUtils idWorker = new UserCodeUtils( 1);
        for (long i = 0; i < 20; i++) {
            long id = UserCodeUtils.generateCode();
            System.out.println("i="+i);
            System.out.println("十进制："+id);
        }
        System.out.println("耗时:"+((System.currentTimeMillis())-start));
    }



}
