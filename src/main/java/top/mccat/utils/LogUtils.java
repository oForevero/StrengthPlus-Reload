package top.mccat.utils;

import com.sun.istack.internal.NotNull;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import top.mccat.enums.Color;
import top.mccat.enums.msg.YamlConfigMsg;

/**
 * @author Raven
 * @date 2022/05/05 16:57
 * plugin日志打印类
 */
public class LogUtils {
    private ConsoleCommandSender commandSender;
    public static final Integer INFO_LEVEL = 0;
    public static final Integer DEBUG_LEVEL = 1;
    public static final Integer ERROR_LEVEL = 2;
    public static final String DEBUG_TITLE = "&e[&bStrengthPlus&e]";
    private boolean debugStatus;
    public LogUtils() {
    }

    public LogUtils(ConsoleCommandSender commandSender) {
        this.commandSender = commandSender;
    }

    /**
     * 执行log方法，默认封装到主类中
     * @param level 代表执行参数
     * @param msg 消息参数
     */
    public void consoleLog(Integer level, String msg){
        if(!debugStatus){
            return;
        }
        if(level.equals(INFO_LEVEL)){
            info(msg);
        }else if (level.equals(DEBUG_LEVEL)){
            debug(msg);
        }else if(level.equals(ERROR_LEVEL)){
            error(msg);
        }
    }

    /**
     * 直接通过枚举打印日志
     * @param yamlConfigMsg 枚举对象
     */
    public void consoleLog(YamlConfigMsg yamlConfigMsg){
        if(!debugStatus){
            return;
        }
        Integer level = yamlConfigMsg.getLevelCode();
        String msg = yamlConfigMsg.getMsg();
        consoleLog(level,msg);
    }

    /**
     * 执行log方法，默认封装到主类中，对象会打印对应的对象名log，范例如下[object]==>
     * @param level 代表执行参数
     * @param o 对象参数
     */
    public void consoleLog(Integer level, @NotNull Object o){
        if(!debugStatus){
            return;
        }
        String msg = o.toString();
        if(level.equals(INFO_LEVEL)){
            info(msg);
        }else if (level.equals(DEBUG_LEVEL)){
            debug(msg);
        }else if(level.equals(ERROR_LEVEL)){
            error(msg);
        }
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

    public String getCurrentFileName(){
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[5];
        return stackTraceElement.getFileName()+"."+stackTraceElement.getMethodName();
    }
    
    public int getCurrentLineNum(){
        return Thread.currentThread().getStackTrace()[5].getLineNumber();
    }

    /**
     * log日志打印方法
     * @param msgData 字符数据
     * @param color Color枚举参数
     */
    private void logToConsole(String msgData, String color){
        String msg = ColorUtils.getColorStr(DEBUG_TITLE+"&6[&b"+getCurrentFileName()+"&6]"+"&a[&c"+getCurrentLineNum()+"&a]==>"+color+msgData);
        commandSender.sendMessage(msg);
    }

    /**
     * 向玩家发送log日志
     * @param msgData 信息字符串
     * @param color Color枚举参数
     * @param player 玩家对象
     */
    private void logToUser(String msgData, String color, Player player){
        String msg = ColorUtils.getColorStr(DEBUG_TITLE+"&6[&b"+getCurrentFileName()+"&6]"+"&a[&c"+getCurrentLineNum()+"&a]==>"+color+msgData);
        player.sendMessage(msg);
    }

    public void reloadLogUtils(boolean debugStatus){
        this.debugStatus = debugStatus;
    }

    public void setDebugStatus(boolean debugStatus) {
        this.debugStatus = debugStatus;
    }

    public void setCommandSender(ConsoleCommandSender commandSender) {
        this.commandSender = commandSender;
    }
}
