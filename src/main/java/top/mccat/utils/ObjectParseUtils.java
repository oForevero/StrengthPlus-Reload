package top.mccat.utils;

/**
 * @author Raven
 * @date 2022/05/10 16:11
 */
public class ObjectParseUtils {

    /**
     * 将object转换为 integer
     * @param object object 对象
     * @return Integer 对象
     */
    public static Integer integerParse(Object object) throws Exception{
        return Integer.parseInt(object.toString());
    }

    /**
     * 将object转换为 boolean
     * @param object object对象
     * @return Boolean 对象
     */
    public static Boolean booleanParse(Object object) throws Exception{
        return Boolean.parseBoolean(object.toString());
    }
}
