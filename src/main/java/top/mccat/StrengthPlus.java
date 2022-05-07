package top.mccat;

import com.sun.istack.internal.NotNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import top.mccat.enums.Permission;
import top.mccat.factory.ConfigFactory;
import top.mccat.utils.LogUtils;

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
    private final ConfigFactory configFactory = new ConfigFactory(this);
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

    /**
     * tab联想指令
     * @param sender 指令发送者
     * @param command 指令
     * @param alias
     * @param args 字指令联想
     * @return
     */
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length > 1) {
            return new ArrayList<>();
        }
        if(sender.hasPermission(Permission.Admin.getPermission()) || sender.isOp()){
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

    /**
     * 执行log方法，默认封装到主类中
     * @param i 代表执行参数
     */
    public void consoleLog(Integer i, String msg){
        if(i.equals(LogUtils.INFO_LEVEL)){
            utils.info(msg);
        }else if (i.equals(LogUtils.DEBUG_LEVEL)){
            utils.debug(msg);
        }else if(i.equals(LogUtils.ERROR_LEVEL)){
            utils.error(msg);
        }
    }

    @Override
    public void saveDefaultConfig() {
        super.saveDefaultConfig();
        if (!this.configFile.exists()) {
            this.saveResource("config.yml", false);
        }
    }
}
