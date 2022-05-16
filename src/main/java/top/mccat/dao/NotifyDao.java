package top.mccat.dao;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Distance
 * @date 2022/5/16 8:59
 */
public interface NotifyDao {
    /**
     * 通知玩家方法
     * @param msg 消息
     * @param player 玩家实体
     */
    void notifyPlayer(String msg, Player player);

    /**
     * 通知服务器广播方法
     * @param msg 消息
     * @param commandSender console广播对象
     */
    void notifyBroadcast(String msg, CommandSender commandSender);
}
