package top.mccat.handler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mccat.domain.StrengthMenu;
import top.mccat.enums.Permission;
import top.mccat.ui.StrengthChestInventory;
import top.mccat.utils.ColorUtils;
import top.mccat.utils.LogUtils;
import top.mccat.utils.MsgUtils;

/**
 * @author Raven
 * @date 2022/05/05 21:22
 */
public class CommandHandler implements CommandExecutor {
    private StrengthMenu strengthMenu;
    private final MsgUtils msgUtils;
    private final LogUtils logUtils;
    private StrengthChestInventory strengthChestInventory;
    public CommandHandler(StrengthMenu strengthMenu, MsgUtils msgUtils, LogUtils logUtils) {
        this.strengthMenu = strengthMenu;
        this.msgUtils = msgUtils;
        this.logUtils = logUtils;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String mainCommand, String[] commandArray) {
        if(!"console".equalsIgnoreCase(commandSender.getName())){
            Player player = (Player) commandSender;
            if(commandArray.length == 0){
                notifyMenu(player);
                return true;
            }else {
                if (player.hasPermission(Permission.Admin.getPermission()) || player.isOp()){
                    switch (commandArray[0]){
                        case "menu":
                            //先关闭视图
                            player.closeInventory();
                            //开启强化菜单
                            player.openInventory(strengthChestInventory.getStrengthInventory());
                            break;
                        case "":
                        default:
                            break;
                    }
                }
            }
        }else {

        }
        return true;
    }

    /**
     * 发送指令 menu
     * @param player player.
     */
    public void notifyMenu(Player player){
        sendMsg(player,"&4&l===---------&6&l[strengthPlus]&4&l----------===");
        sendMsg(player,"&c/sp 或 qh 打开此帮助菜单");
        sendMsg(player,"&a/sp 或 qh normal 进行一次强化");
        sendMsg(player,"&b/sp 或 qh safe 保护强化 (强化失败不降级)");
        sendMsg(player,"&b/sp 或 qh success 必定成功强化 ");
        if(player.hasPermission(Permission.Admin.getPermission())){
            sendMsg(player,"&4&l===-------&6&l[strengthPlusAdmin]&4&l--------===");
            sendMsg(player,"&c/sp &b admin &c管理员强化，&a直接满级");
            sendMsg(player,"&c/sp &b reload &c管理员专用，&a重载配置");
            sendMsg(player,"&c/sp &b normalStone &c管理员&a权限可用，给与&b普通强化石");
            sendMsg(player,"&c/sp &b safeStone &c管理员&a权限可用，给与&c保护强化石");
            sendMsg(player,"&c/sp &b successStone &c管理员&a权限可用，给与&e必定成功强化石");
        }
        sendMsg(player,"&4&l===&6&l--------------------------------&4&l===");
    }

    public void sendMsg(Player player, String str){
        player.sendMessage(ColorUtils.getColorStr(str));
    }

    public void setStrengthMenu(StrengthMenu strengthMenu) {
        this.strengthMenu = strengthMenu;
    }

    public void setStrengthChestInventory(StrengthChestInventory strengthChestInventory) {
        this.strengthChestInventory = strengthChestInventory;
    }
}
