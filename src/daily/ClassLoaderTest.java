package daily;

/**
 * @author yangxc27652
 * @date 2020/9/10
 * @description 类加载器测试
 */
public class ClassLoaderTest {
    public static void main(String[] args) {

        ClassLoaderTest test = new ClassLoaderTest();
        test.test();
    }

    /**
     * static方法中不能用this，所以加了一层test方法
     */
    void test () {
        ClassLoader classloader = this.getClass().getClassLoader();
        System.out.println("本类的类加载器是"+ classloader);
        ClassLoader fatherLoader = classloader.getParent();
        System.out.println("本类加载器的父加载器是"+ fatherLoader);
    }
}
