package daily;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yangxc27652
 * @date 2020/9/10
 * @description 时间相关工具类
 */
public class TimeUtilsTest {

    public static void main(String[] args) {

        // 新建一个date对象
        Date date = new Date();
        System.out.println(date);   //  Tue Sep 15 13:54:27 CST 2020

        // 取当前机器日期
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String machineDate = df.format(date);
        System.out.println("当前机器日期为:" + machineDate);

        // 取当前机器时间
        SimpleDateFormat df1 = new SimpleDateFormat("HHmmss");
        String machineTime = df1.format(date);
        System.out.println("当前机器时间为:" + machineTime);

        Long startTs = System.currentTimeMillis(); // 当前时间戳
        System.out.println("当前时间戳为:"+startTs);

    }
}
