package top.mccat;

import com.sun.istack.internal.NotNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import top.mccat.enums.Permission;
import top.mccat.enums.YamlConfigMessage;
import top.mccat.factory.ConfigFactory;
import top.mccat.utils.ColorUtils;
import top.mccat.utils.LogUtils;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Raven
 * @date 2022/05/05 16:05
 * 主插件启动类
 */
public class StrengthPlus extends JavaPlugin {
    private final LogUtils utils = new LogUtils(StrengthPlus.class,null);
    private final ConfigFactory configFactory = new ConfigFactory(this);
    private ConsoleCommandSender consoleSender;

    /**
     * 被spigot读取的方法
     */
    @Override
    public void onLoad() {
        consoleSender = this.getServer().getConsoleSender();
        //获取console的打印日志实体
        utils.setCommandSender(consoleSender);
        authorMenu(this);
        configFactory.readConfigFile();
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
        configFactory.readConfigFile();
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
     * @param alias 子联想
     * @param args 字指令联想
     * @return 联想列表
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
     * @param level 代表执行参数
     * @param msg 消息参数
     */
    public void consoleLog(Integer level, String msg){
        if(!configFactory.isDebugStatus()){
            return;
        }
        if(level.equals(LogUtils.INFO_LEVEL)){
            utils.info(msg);
        }else if (level.equals(LogUtils.DEBUG_LEVEL)){
            utils.debug(msg);
        }else if(level.equals(LogUtils.ERROR_LEVEL)){
            utils.error(msg);
        }
    }

    /**
     * 直接通过枚举打印日志
     * @param yamlConfigMessage 枚举对象
     */
    public void consoleLog(YamlConfigMessage yamlConfigMessage){
        if(!configFactory.isDebugStatus()){
            return;
        }
        Integer level = yamlConfigMessage.getLevelCode();
        String msg = yamlConfigMessage.getMessage();
        consoleLog(level,msg);
    }

    /**
     * 执行log方法，默认封装到主类中，对象会打印对应的对象名log，范例如下[object]==>
     * @param level 代表执行参数
     * @param o 对象参数
     */
    public void consoleLog(Integer level, @NotNull Object o){
        if(!configFactory.isDebugStatus()){
            return;
        }
        String msg = "["+o.getClass().getName()+ "]==>"+ o;
        if(level.equals(LogUtils.INFO_LEVEL)){
            utils.info(msg);
        }else if (level.equals(LogUtils.DEBUG_LEVEL)){
            utils.debug(msg);
        }else if(level.equals(LogUtils.ERROR_LEVEL)){
            utils.error(msg);
        }
    }

    @Override
    public void saveDefaultConfig() {
        configFactory.writeConfigFile();
    }

    public void consoleMsg(String msg){
        consoleSender.sendMessage(ColorUtils.getColorStr(msg));
    }

    /**
     * 作者信息menu
     * @param plugin javaPlugin.
     */
    private void authorMenu(StrengthPlus plugin){
        consoleMsg("&4&l===----------&6&l[StrengthPlus]&4&l-----------===");
        consoleMsg("&a          插件版本：&b"+ConfigFactory.PLUGIN_VERSION);
        consoleMsg("&b             制作者： Raven       ");
        consoleMsg("&b             QQ ： 740585947     ");
        consoleMsg("&b  如有bug可以在bbs论坛下留言反馈，蟹蟹！");
        consoleMsg("&4&l===----------&6&l[StrengthPlus]&4&l-----------===");
    }
}
