package top.mccat.utils;

import net.md_5.bungee.api.ChatColor;

/**
 * @author Raven
 * @date 2022/05/05 20:36
 * 颜色转换类
 */
public class ColorUtils {

    /**
     * 获取Color字符方法
     * @param str 字符串
     * @return 经过解析的字符串
     */
    public static String getColorStr(String str){
        return ChatColor.translateAlternateColorCodes('&',str);
    }

    public static String parseColorStr(String str){
        return str.replace("&","§");
    }
}
