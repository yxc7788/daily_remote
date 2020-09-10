package daily;

/**
 * @author yangxc27652
 * @date 2020/9/10
 * @description 类加载器测试
 */
public class ClassLoaderTest {
    public static void main(String[] args) {

        Classloader classloader = this.getClass().getClassLoader();
    }
}
