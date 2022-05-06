package factory;

import domain.config.EssentialsConfig;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @author Raven
 * @date 2022/05/05 16:54
 * 配置文件读取仓库
 */
public class ConfigFactory {
    private final JavaPlugin plugin;
    private final EssentialsConfig essentialsConfig;
    private final FileConfiguration fileConfiguration = new YamlConfiguration();
    public ConfigFactory(JavaPlugin plugin, EssentialsConfig essentialsConfig){
        this.plugin = plugin;
        this.essentialsConfig = essentialsConfig;
    }

    public void initFile(){
        try {
            fileConfiguration.load(new File(plugin.getDataFolder(),"config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
