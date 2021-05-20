package daily;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxc27652
 * @date 2020/9/24
 * @description
 */
public class Test {

    public static void main(String[] args) {

        int a = Integer.parseInt("1000") + 1;

        System.out.println(a);
        String b = String.valueOf(a);
        System.out.println(b);


        // 这是一条测试记录
        System.out.println("-----------------");


        String filename = "C:\\OFD_25_225_20200326_04.TXT";
        String fileNamePrefix = "OFD_25_225_20200326_";
        int index = filename.indexOf(fileNamePrefix);
        String p = filename.substring(index + fileNamePrefix.length(), index+2+fileNamePrefix.length());
        System.out.println(p);

        System.out.println("-----------------");

        String lineText;
        File file = new File("indexFileName");
        List<String> fileNameList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((lineText = bufferedReader.readLine()) != null) {
                // 判断是否为确认文件名
                if (lineText.contains("OFD_")) {
                    fileNameList.add(lineText);
                }
            }
            bufferedReader.close();
        } catch (Exception exception) {
            System.out.println("dd");
        }
        System.out.println(fileNameList);

    }
}
