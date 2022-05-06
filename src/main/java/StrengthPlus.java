import com.sun.corba.se.impl.activation.CommandHandler;
import com.sun.istack.internal.NotNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import utils.LogUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Raven
 * @date 2022/05/05 16:05
 */
public class StrengthPlus extends JavaPlugin {
    private final LogUtils utils = new LogUtils(StrengthPlus.class,null);
    /**
     * 被spigot读取的方法
     */
    @Override
    public void onLoad() {
        //获取console的打印日志实体
        utils.setCommandSender(this.getServer().getConsoleSender());
    }

    /**
     * 插件被允许时的方法
     */
    @Override
    public void onEnable() {
        
    }

    /**
     * 禁用时的方法
     */
    @Override
    public void onDisable() {

    }

    /**
     * 重载配置方法
     */
    @Override
    public void reloadConfig() {

    }

    /**
     * 子命令联想
     */
    private final String[] subUserCommands = {"normal", "safe", "success"};
    private final String[] subCommands = {"normal", "safe", "success", "admin", "reload", "normalstone", "safestone", "successstone"};
    @Override
    public @Nullable
    List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length > 1) {
            return new ArrayList<>();
        }
        if(sender.hasPermission(CommandHandler.ADMIN_PERMISSION)){
            if (args.length == 0) {
                return Arrays.asList(subCommands);
            }
            return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
        }
        if (args.length == 0) {
            return Arrays.asList(subUserCommands);
        }
        return Arrays.stream(subUserCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    }

    public void consoleLog(Integer i){

    }
}
