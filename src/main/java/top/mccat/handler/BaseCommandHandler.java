package top.mccat.handler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Raven
 * @date 2022/05/05 21:22
 */
public abstract class BaseCommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){

        }else {

        }
        return true;
    }
}
