package daily.steamapi;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangxc27652
 * @date 2020/9/15
 * @description steam测试
 */
public class SteamApiTest2 {

    public static void main(String[] args) {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<String> strList = new ArrayList<>();
        for (int num : numList) {
            strList.add(Integer.toString(num));
        }
        System.out.println(strList);

        List<String> strList1 = numList.stream()
                .map(it -> Integer.toString(it))
                .collect(Collectors.toList());

        System.out.println(strList1);
    }
}
