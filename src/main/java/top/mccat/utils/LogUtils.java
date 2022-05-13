package top.mccat.utils;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import top.mccat.enums.Color;

/**
 * @author Raven
 * @date 2022/05/05 16:57
 */
public class LogUtils {
    private Object object;
    private ConsoleCommandSender commandSender;
    public static final Integer INFO_LEVEL = 0;
    public static final Integer DEBUG_LEVEL = 1;
    public static final Integer ERROR_LEVEL = 2;

    public LogUtils() {
    }

    public LogUtils(Object object, ConsoleCommandSender commandSender) {
        this.object = object;
        this.commandSender = commandSender;
    }

    /**
     * 打印info日志，默认颜色为白色
     * @param msg 信息
     */
    public void info(String msg){
        logToConsole(msg, Color.White.getColorStr());
    }

    /**
     * 向玩家打印info日志，默认颜色为白色
     * @param msg 信息
     * @param player 玩家对象
     */
    public void info(String msg, Player player){
        logToUser(msg,Color.White.getColorStr(),player);
    }

    /**
     * 打印debug日志，默认颜色为绿色
     * @param msg 信息
     */
    public void debug(String msg){
        logToConsole(msg,Color.Green.getColorStr());
    }

    /**
     * 向玩家打印debug日志，默认颜色为绿色
     * @param msg 信息
     * @param player 玩家对象
     */
    public void debug(String msg, Player player){
        logToUser(msg,Color.Green.getColorStr(),player);
    }

    /**
     * 打印错误日志，默认颜色为红色
     * @param msg 信息
     */
    public void error(String msg){
        logToConsole(msg,Color.Red.getColorStr());
    }

    /**
     * 向玩家打印error日志，默认颜色为红色
     * @param msg 信息
     * @param player 玩家对象
     */
    public void error(String msg, Player player){
        logToUser(msg,Color.Red.getColorStr(),player);
    }

    /**
     * log日志打印方法
     * @param msgExtra 字符数据
     * @param color Color枚举参数
     */
    private void logToConsole(String msgExtra, String color){
        String msg = ColorUtils.getColorStr("&6[&b"+object.getClass().getName()+"&6]==>"+color+msgExtra);
        commandSender.sendMessage(msg);
    }

    /**
     * 向玩家发送log日志
     * @param msgExtra 信息字符串
     * @param color Color枚举参数
     * @param player 玩家对象
     */
    private void logToUser(String msgExtra, String color, Player player){
        String msg = ColorUtils.getColorStr("&6[&b"+object.getClass().getName()+"&6]==>"+color+msgExtra);
        player.sendMessage(msg);
    }

    public void setCommandSender(ConsoleCommandSender commandSender) {
        this.commandSender = commandSender;
    }
}
