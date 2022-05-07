package top.mccat.factory;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import top.mccat.domain.config.EssentialsConfig;
import top.mccat.StrengthPlus;
import java.io.File;
import java.io.IOException;

/**
 * @author Raven
 * @date 2022/05/05 16:54
 * 配置文件读取仓库
 */
public class ConfigFactory {
    private final StrengthPlus plugin;
    private final FileConfiguration fileConfiguration = new YamlConfiguration();
    /**
     * 对应配置文件
     */
    private final File config;
    private final File strengthExtra;
    private final File strengthItem;
    private final File strengthStone;
    public static final String PLUGIN_VERSION = "2.1-Alpha";
    public ConfigFactory(StrengthPlus plugin){
        this.plugin = plugin;
        File dataFolder = plugin.getDataFolder();
        config = new File(dataFolder,"config.yml");
        strengthExtra = new File(dataFolder,"strength-extra.yml");
        strengthItem = new File(dataFolder,"strength-item.yml");
        strengthStone = new File(dataFolder,"strength-stone.yml");
    }

    /**
     * 将yml写入plugin文件夹的io方法
     */
    public void readConfigFile(){
        try {
            fileConfiguration.load(config);
            fileConfiguration.load(strengthExtra);
            fileConfiguration.load(strengthItem);
            fileConfiguration.load(strengthStone);
        } catch (IOException e) {
            plugin.consoleLog(2,"插件io读取错误，插件加载失败！");
            writeConfigFile();
        } catch (InvalidConfigurationException e) {
            plugin.consoleLog(1,"配置文件不存在，正在生成配置文件.....");
            plugin.saveDefaultConfig();
            writeConfigFile();
        }
    }

    /**
     * 从插件中写入yml文件到插件文件夹中
     */
    public void writeConfigFile(){
        plugin.saveResource("config.yml",false);
        plugin.saveResource("strength-extra.yml",false);
        plugin.saveResource("strength-item.yml",false);
        plugin.saveResource("strength-stone.yml",false);
    }
}
