package daily;

import java.util.Arrays;

/**
 * The type Enum test.
 *
 * @author yangxc27652
 * @date 2020 /9/15
 * @description 遍历枚举测试
 */
public class EnumTest {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // 遍历一个枚举时候注意用  枚举对象.values
        for (SearchCategoryEnum s : SearchCategoryEnum.values()) {
            System.out.println(s.getValue());
        }


        // 采用steam方式去遍历
        System.out.println("------------");
        SearchCategoryEnum paramValue = Arrays.stream(SearchCategoryEnum.values())
                .filter(e -> e.getValue().contains("图片"))
                .findFirst()
               //.map(ISystemParam::getValue)
                .orElse(null);
        System.out.println(paramValue.getValue());

        System.out.println("------------");
        System.out.println(SearchCategoryEnum.ALL);
        System.out.println(SearchCategoryEnum.ALL.getValue());
    }
}

/**
 * The enum Search category.
 */
enum SearchCategoryEnum {

    /**
     * All search category.
     */
    ALL("综合查询"),
    /**
     * News search category.
     */
    NEWS("新闻"),
    /**
     * Taxpayer search category.
     */
    TAXPAYER("纳税人"),
    /**
     * Quiz search category.
     */
    QUIZ("知识问答"),
    /**
     * Policy law search category.
     */
    POLICY_LAW("政策法规"),
    /**
     * Doc search category.
     */
    DOC("涉税文档"),
    /**
     * Pic search category.
     */
    PIC("图片"),
    /**
     * Pic 1 search category enum.
     */
    PIC1("图片1"),
    /**
     * Baike search category.
     */
    BAIKE("税务百科");

    private String value;

    SearchCategoryEnum(String value) {
        this.value = value;
    }

    /**
     * Get value string.
     *
     * @return the string
     */
    public String getValue(){
        return value;
    }
}
