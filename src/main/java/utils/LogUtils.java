package utils;

import lombok.AllArgsConstructor;
import org.bukkit.command.ConsoleCommandSender;
import org.junit.jupiter.api.Test;
import utils.enums.Color;

/**
 * @author Raven
 * @date 2022/05/05 16:57
 */
@AllArgsConstructor
public class LogUtils {
    private ConsoleCommandSender commandSender;
    private Class object;

    /**
     * 打印info日志，默认颜色为白色
     * @param msg
     */
    public void info(String msg){
        log(msg,Color.White.getColorStr());
    }

    /**
     * 打印debug日志，默认颜色为绿色
     * @param msg
     */
    public void debug(String msg){
        log(msg,Color.Green.getColorStr());
    }

    /**
     * 打印错误日志，默认颜色为红色
     * @param msg
     */
    public void error(String msg){
        log(msg,Color.Red.getColorStr());
    }

    /**
     * log日志打印方法
     * @param msgExtra 字符数据
     * @param color Color枚举参数
     */
    private void log(String msgExtra,String color){
        String msg = ColorUtils.getColorStr("&6[&b"+object.getName()+"&6]==>"+color+msgExtra);
        commandSender.sendMessage(msg);
    }
}
