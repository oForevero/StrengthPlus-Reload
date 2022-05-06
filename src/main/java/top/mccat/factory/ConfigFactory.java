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
    private final EssentialsConfig essentialsConfig;
    private final FileConfiguration fileConfiguration = new YamlConfiguration();
    public ConfigFactory(StrengthPlus plugin, EssentialsConfig essentialsConfig){
        this.plugin = plugin;
        this.essentialsConfig = essentialsConfig;
    }

    public void initFile(){
        File dataFolder = plugin.getDataFolder();
        try {
            fileConfiguration.load(new File(dataFolder,"config.yml"));
            fileConfiguration.load(new File(dataFolder,"strength-extra.yml"));
            fileConfiguration.load(new File(dataFolder,"strength-item.yml"));
            fileConfiguration.load(new File(dataFolder,"strength-stone.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
            plugin.consoleLog(0,"");
        }
    }
}
