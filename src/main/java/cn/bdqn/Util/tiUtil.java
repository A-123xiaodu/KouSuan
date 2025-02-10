package cn.bdqn.Util;

import java.util.Arrays;
import java.util.Random;

public class tiUtil {
    public static int[]wei={2,3,4};
    public static int[]zhi={0,1,2,3,4,5,6,7,8,9};
    public static String shizi(){
        StringBuffer diyi=new StringBuffer();
        StringBuffer dier=new StringBuffer();
        Random random=new Random();
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
        for (int i : first) {
            diyi.append(i);
        }
        for (int j : second) {
            dier.append(j);
        }
        System.out.println(diyi+"-"+dier);
        return diyi+"-"+dier;
    }
}
