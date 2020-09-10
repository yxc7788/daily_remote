package daily;

/**
 * @author yangxc27652
 * @date 2020/9/9
 * @description  JAVA中操作字符String常用的方法
 */
public class StringUtilsTest {

    public static void main(String[] args) {

        // 字符串替换 注意一定要
        String a = "aabb";
        String b = a.replace("a","2");
        System.out.println(b);

    }
}

/**
 * 测试类备用
 */
class StringUtil{

    private String a;
    private String b;

    StringUtil(String a, String b){
        this.a = a;
        this.b = b;
    }
}
