package daily;

/**
 * @author yangxc27652
 * @date 2020/9/9
 * @description  JAVA中操作字符String常用的方法
 */
public class StringUtilsTest {

    public static void main(String[] args) {

        // 字符串替换 注意如果是String类型的话，一定要用新引用指向新对象
        String a = "aabb";
        String b = a.replace("a","2");
        System.out.println(b);

        // 第一个字符换成3
        StringBuilder a1= new StringBuilder("abc");
        a1.replace(0,1,"3");
        System.out.println(a1);



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
