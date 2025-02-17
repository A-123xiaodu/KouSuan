package cn.bdqn.util;

import java.util.Random;

public class tiUtil {
    public static int[]wei={2,3,4,5};//当前要生成的位数
    public static int[]zhi={0,1,2,3,4,5,6,7,8,9};//随机这几个数
    static Random random=new Random();
    public static String shizi(){//生成两位随机位数的随机数
        StringBuffer diyi=new StringBuffer();
        StringBuffer dier=new StringBuffer();
        int weizhu = random.nextInt(wei.length);
        int weishu=wei[weizhu];
        int[]first=new int[weishu];
        int[]second=new int[weishu];
        for (int i = 1; i < 3; i++) {
            int dangqian=1;
            while(dangqian<=weishu){
                int suishu = random.nextInt(zhi.length);
                if(dangqian==1 && suishu==0){
                    continue;
                }
                if(i==1){
                    first[dangqian-1]=zhi[suishu];
                }else{
                    second[dangqian-1]=zhi[suishu];
                }
                dangqian++;
            }
        }
        for (int i : first) {//获取完之后，存储和拼接
            diyi.append(i);
        }
        for (int j : second) {
            dier.append(j);
        }
        System.out.println(diyi+"-"+dier);
        return diyi+"-"+dier;
    }
    public static String getYan(){
        StringBuffer stringBuffer=new StringBuffer();
        for (int i = 0; i < 6; i++) {
            stringBuffer.append(zhi[random.nextInt(zhi.length)]);
        }
        return stringBuffer.toString();
    }
}
