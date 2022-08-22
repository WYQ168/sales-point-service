package com.sales.common.core.utils;

/**
 * @author wuyingqiang
 * @desc: TODO,用户编号与邀请码转换工具类
 * @date 2022/7/14 12:18
 */
public class ShareCodeUtils {

    private static final String tempStr="sales168";
    /** 邀请码字符数组 */
    private static final char[] r=new char[]{
            '6', 'K', 'U', 'D', 'F', 'R',
            'Q', 'W', 'E', '8', 'S', '2',
            'G', 'H', 'Z', 'X', 'Y', 'T',
            '4', 'V', 'N', 'B', '9', 'C',
            '7', 'P', '5', '3', 'M', 'J',
            };

    /** 补全邀请码长度 */
    private static final char b='A';

    /** 进制长度 */
    private static final int binLen=r.length;

    /** 邀请码长度 */
    private static final int codeLen=6;

    /**
     * 根据ID生成随机码
     * @param id ID
     * @return 随机码
     */
    public static String idToShareCode(long id) {
        char[] buf=new char[32];
        int charPos=32;

        while((id / binLen) > 0) {
            int ind=(int)(id % binLen);
            buf[--charPos]=r[ind];
            id /= binLen;
        }
        buf[--charPos]=r[(int)(id % binLen)];
        String str=new String(buf, charPos, (32 - charPos));
        // 不够长度的自动补全
        if(str.length() < codeLen) {
            StringBuilder sb=new StringBuilder();
            sb.append(tempStr.subSequence(0, codeLen-str.length()));
            str+=sb.toString();
        }
        return str;
    }

    /**
     * 根据随机码生成ID
     * @param code 随机码
     * @return ID
     */
    public static long shareCodeToId(String code) {
        char chs[]=code.toCharArray();
        long res=0L;
        for(int i=0; i < chs.length; i++) {
            int ind=0;
            for(int j=0; j < binLen; j++) {
                if(chs[i] == r[j]) {
                    ind=j;
                    break;
                }
            }
            if(chs[i] == b) {
                break;
            }
            if(i > 0) {
                res=res * binLen + ind;
            } else {
                res=ind;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        for (long i = 0; i < 20; i++) {
            long id = UserCodeUtils.generateCode();
//            System.out.println("二进制："+Long.toBinaryString(id));
            System.out.print("i="+i+",");
            System.out.print("userCode："+id+",");
            String code= idToShareCode(id);
            System.out.print("用户："+id+"的邀请码为："+code+",");  ;
            System.out.println("邀请码："+code+"反推的用户ID为："+shareCodeToId(code)+",");  ;
//            System.out.println("cur-start:"+(System.currentTimeMillis()-1577808000000L));
        }
    }
}