package daily;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxc27652
 * @date 2020/9/27
 * @description  文件以及输入输出流
 */
public class FileTest {
    public static void main(String[] args) {

        File file = new File("D:\\index.txt");
        String lineText;
        if (file.exists()) {
            System.out.println("index文件存在");
        }
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


        File file1 = new File("C:\\Users\\19141\\Downloads\\56");
        System.out.println(file1.isFile());
        System.out.println(file1.exists());
        System.out.println(file1.isDirectory());

    }

}
