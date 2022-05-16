package top.mccat.dao.impl;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mccat.dao.NotifyDao;

/**
 * @author Distance
 * @date 2022/5/16 9:01
 */
public class NotifyDaoImpl implements NotifyDao {

    @Override
    public void notifyPlayer(String msg, Player player) {
        player.sendMessage(msg);
    }

    @Override
    public void notifyBroadcast(String msg, CommandSender commandSender) {
        commandSender.sendMessage(msg);
    }
}
