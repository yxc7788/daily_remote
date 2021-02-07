package daily;

import java.util.Scanner;

/**
 * @author yangxc27652
 * @date 2021/1/23
 * @description  控制台输入
 */
public class ScannerTest {
    public static void main(String[] args) {
//        while (true) {
//            //从控制台输入，转化成字符串
//            System.out.println("请输入字符串");
//            Scanner s = new Scanner(System.in);
//            String str = s.nextLine();
//            System.out.println("输出字符串" + str);
//
//            // 从控制台输入一个整数
//            System.out.println("请输入数字");
//            int a = s.nextInt();
//            System.out.println("输出数字" + a);
//
//
//        }

        // 从控制台输入一个数组，输入格式  1 2 3 4
        Scanner s = new Scanner(System.in);
        System.out.println("请输入一个数组");
        String b = s.nextLine();
        String [] strs = b.split(" ");
        int[] nums = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            nums[i] = Integer.valueOf(strs[i]);
            System.out.println("输出数组元素" + i + "值为: " + nums[i]);
        }




    }
}
