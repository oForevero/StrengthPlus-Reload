package top.mccat.utils;

import com.sun.istack.internal.NotNull;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mccat.domain.config.EssentialsConfig;
import top.mccat.enums.Color;
import top.mccat.enums.EnumMsg;

/**
 * @author Distance
 * @date 2022/5/26 10:07
 */
public class MsgUtils {
    private EssentialsConfig essentialsConfig;
    private CommandSender commandSender;
    public MsgUtils(){}

    public MsgUtils(EssentialsConfig essentialsConfig, CommandSender commandSender) {
        this.essentialsConfig = essentialsConfig;
        this.commandSender = commandSender;
    }

    /**
     * 将消息送到玩家对象上
     * @param color 颜色枚举参数
     * @param msg 消息字符串
     * @param player 玩家对象
     */
    public void sendToPlayer(@NotNull String msg, @NotNull Player player, Color color){
        String colorStr = ColorUtils.getColorStr(essentialsConfig.getPluginName()+color.getColorStr()+msg);
        player.sendMessage(colorStr);
    }

    /**
     * 将消息发送到控制台上
     * @param msg 消息参数
     * @param color 颜色枚举参数
     */
    public void sendToConsole(@NotNull String msg, Color color){
        String colorStr = ColorUtils.getColorStr(LogUtils.DEBUG_TITLE+color.getColorStr()+msg);
        commandSender.sendMessage(colorStr);
    }

    /**
     * 将美剧消息发送到控制台上
     * @param msg 消息参数
     * @param color 颜色枚举参数
     */
    public void sendToConsole(@NotNull EnumMsg msg, Color color){
        String colorStr = ColorUtils.getColorStr(LogUtils.DEBUG_TITLE+color.getColorStr()+msg.getMsg());
        commandSender.sendMessage(colorStr);
    }

    public void setEssentialsConfig(EssentialsConfig essentialsConfig) {
        this.essentialsConfig = essentialsConfig;
    }

    public void setCommandSender(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public String toString() {
        return "MsgUtils{" +
                "essentialsConfig=" + essentialsConfig +
                '}';
    }
}
