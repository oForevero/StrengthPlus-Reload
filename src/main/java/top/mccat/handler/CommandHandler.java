package top.mccat.handler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mccat.StrengthPlus;

/**
 * @author Raven
 * @date 2022/05/05 21:22
 */
public class CommandHandler implements CommandExecutor {
    private StrengthPlus strengthPlus;

    public CommandHandler(StrengthPlus strengthPlus) {
        this.strengthPlus = strengthPlus;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender!=null){
            if(commandSender instanceof Player){

            }else {

            }
        }
        return true;
    }
}
