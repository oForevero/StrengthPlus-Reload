package top.mccat;

import com.sun.istack.internal.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import top.mccat.enums.Color;
import top.mccat.enums.Permission;
import top.mccat.factory.ConfigFactory;
import top.mccat.factory.OtherPluginFactory;
import top.mccat.handler.CommandHandler;
import top.mccat.ui.StrengthChestInventory;
import top.mccat.utils.ColorUtils;
import top.mccat.utils.LogUtils;
import top.mccat.utils.MsgUtils;

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
    private final LogUtils logutils = new LogUtils();
    private final MsgUtils msgUtils = new MsgUtils();
    private ConfigFactory configFactory;
    private ConsoleCommandSender consoleSender;
    private CommandHandler commandHandler;
    private StrengthChestInventory strengthChestInventory;
    private final String DefaultCommand = "sp";
    /**
     * 被spigot读取的方法
     */
    @Override
    public void onLoad() {
        consoleSender = this.getServer().getConsoleSender();
        //进行日志工具和消息工具的console对象传递
        logutils.setCommandSender(consoleSender);
        msgUtils.setCommandSender(consoleSender);
        authorMenu(this);
        //对配置文件进行读取
        configFactory = new ConfigFactory(this,logutils,msgUtils);
        configFactory.readConfigFile();
        msgUtils.setEssentialsConfig(configFactory.getEssentialsConfig());
        initEssentialsModel();

    }

    /**
     * 插件被允许时的方法
     */
    @Override
    public void onEnable() {
        enableListener();
        setupOtherPlugin();
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
     * 初始化基础模块参数
     */
    private void initEssentialsModel(){
        logutils.setDebugStatus(configFactory.isDebugStatus());
        //初始化强化界面ui
        strengthChestInventory = new StrengthChestInventory(configFactory.getStrengthMenu(),logutils,msgUtils);
        //初始化命令响应
        commandHandler = new CommandHandler(configFactory.getStrengthMenu(),msgUtils,logutils);
        commandHandler.setStrengthMenu(configFactory.getStrengthMenu());
        commandHandler.setStrengthChestInventory(strengthChestInventory);
    }


    /**
     * 逐步允许监听器
     */
    private void enableListener(){
        //注册插件指令监听器
        Bukkit.getPluginCommand(DefaultCommand).setExecutor(commandHandler);
        //注册ui事件监听器
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(strengthChestInventory,this);
    }

    /**
     * 对经济api进行挂载
     */
    private void setupOtherPlugin(){
        if(getServer().getPluginManager().getPlugin("Vault") != null){

        }
        if(getServer().getPluginManager().getPlugin("PlaceHolderApi") != null){
            if(OtherPluginFactory.BuildPlaceHolderExpansion()){
                msgUtils.sendToConsole("PlaceHolderApi挂载成功！", Color.LightRed);
            }
        }
    }

    /**
     * 子命令联想
     */
    private final String[] subUserCommands = {"normal", "safe", "success"};
    private final String[] subCommands = {"menu", "normal", "safe", "success", "admin", "reload", "normalstone", "safestone", "successstone"};

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

    @Override
    public void saveDefaultConfig() {
        configFactory.readConfigFile();
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
