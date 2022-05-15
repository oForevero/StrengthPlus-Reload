package top.mccat.utils;

import top.mccat.exception.ExtraParseException;

/**
 * @author Raven
 * @date 2022/05/10 16:11
 */
public class ObjectParseUtils {
    /**
     * 将object转换为 integer
     * @param object object 对象
     * @return Integer 对象
     * @throws ExtraParseException int参数转换失败
     */
    public static Integer integerParse(Object object) throws ExtraParseException {
        int parseInt;
        try {
            parseInt = Integer.parseInt(object.toString());
        } catch (NumberFormatException e) {
            throw new ExtraParseException();
        }
        return parseInt;
    }

    /**
     * 将object转换为 double
     * @param object object对象
     * @return double数据
     * @throws ExtraParseException double参数转换失败
     */
    public static Double doubleParse(Object object) throws ExtraParseException{
        double parseDouble;
        try {
            parseDouble = Double.parseDouble(object.toString());
        } catch (NumberFormatException e) {
            throw new ExtraParseException();
        }
        return parseDouble;
    }

    /**
     * 将object转换为 boolean
     * @param object object对象
     * @return Boolean 对象
     */
    public static Boolean booleanParse(Object object){
        return Boolean.parseBoolean(object.toString());
    }
}
