package top.mccat.factory;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import top.mccat.StrengthPlus;
import top.mccat.domain.StrengthLevel;
import top.mccat.domain.StrengthStone;
import top.mccat.domain.config.EssentialsConfig;
import top.mccat.enums.YamlConfigMessage;
import top.mccat.exception.ConfigFileNotFoundException;
import top.mccat.utils.ObjectParseUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private final File strengthLevelFile;
    private final File strengthItemFile;
    private final File strengthStoneFile;
    /**
     * 实体参数对象
     */
    private final List<StrengthLevel> strengthLevels = new ArrayList<>();
    private final StrengthStone strengthStone = new StrengthStone();
    private final EssentialsConfig essentialsConfig = new EssentialsConfig();
    private boolean debugStatus = false;
    public static final String PLUGIN_VERSION = "2.1-Alpha";
    public ConfigFactory(StrengthPlus plugin){
        this.plugin = plugin;
        File dataFolder = plugin.getDataFolder();
        config = new File(dataFolder,"config.yml");
        strengthLevelFile = new File(dataFolder, "strength-level.yml");
        strengthItemFile = new File(dataFolder,"strength-item.yml");
        strengthStoneFile = new File(dataFolder,"strength-stone.yml");
    }

    /**
     * 将yml写入plugin文件夹的io方法
     */
    public void readConfigFile(){
        try {
            fileConfiguration.load(config);
            reloadEssentialsConfig();
            fileConfiguration.load(strengthLevelFile);
            reloadStrengthLevel();
            fileConfiguration.load(strengthItemFile);
            fileConfiguration.load(strengthStoneFile);
        } catch (IOException | InvalidConfigurationException e) {
            plugin.consoleLog(2,"配置文件IO读取错误，正在重新生成配置文件！");
            plugin.consoleLog(1,"正在写入默认配置文件......");
            writeConfigFile();
            plugin.consoleLog(1,"正在读取默认配置文件......");
            readConfigFile();
            plugin.consoleLog(1,"读取默认配置文件成功！");
        } catch (ConfigFileNotFoundException e){

        }
    }

    /**
     * 从插件中写入yml文件到插件文件夹中
     */
    public void writeConfigFile(){
        try {
            plugin.saveResource("config.yml",false);
            plugin.saveResource("strength-level.yml",false);
            plugin.saveResource("strength-item.yml",false);
            plugin.saveResource("strength-stone.yml",false);
        } catch (Exception e) {
            plugin.consoleLog(2,"写入默认配置文件失败！请反馈给开发者，帖子地址如下：");
            plugin.consoleLog(2,"https://www.mcbbs.net/thread-1217337-1-1.html");
        }
    }

    /**
     * 加载基础配置文件
     */
    private void reloadEssentialsConfig() throws ConfigFileNotFoundException {
        ConfigurationSection strengthPlus = fileConfiguration.getConfigurationSection("strengthPlus");
        if(strengthPlus==null){
            plugin.consoleLog(YamlConfigMessage.ConfigStrengthPlusLoadError.getLevelCode(), YamlConfigMessage.ConfigStrengthPlusLoadError.getMessage());
            throw new ConfigFileNotFoundException(YamlConfigMessage.ConfigStrengthPlusLoadError.getMessage());
        }
        debugStatus = strengthPlus.getBoolean("debug");
        essentialsConfig.setTitle(strengthPlus.getString("title"));
        essentialsConfig.setDivider("divider");
        essentialsConfig.setLevelIcon("levelIcon");
        List<?> notify = strengthPlus.getList("notify");
        if(notify==null){
            plugin.consoleLog(YamlConfigMessage.ConfigNotifyLoadError.getLevelCode(), YamlConfigMessage.ConfigNotifyLoadError.getMessage());
            throw new ConfigFileNotFoundException(YamlConfigMessage.ConfigNotifyLoadError.getMessage());
        }
        essentialsConfig.setSuccessNotify(notify.get(0).toString());
        essentialsConfig.setFailNotify(notify.get(1).toString());
        List<?> broadcast = strengthPlus.getList("broadcast");
        if(broadcast==null){
            plugin.consoleLog(YamlConfigMessage.ConfigBroadcastLoadError.getLevelCode(), YamlConfigMessage.ConfigBroadcastLoadError.getMessage());
            throw new ConfigFileNotFoundException(YamlConfigMessage.ConfigBroadcastLoadError.getMessage());
        }
        essentialsConfig.setSuccessBroadcast(broadcast.get(0).toString());
        essentialsConfig.setSafeBroadcast(broadcast.get(1).toString());
        essentialsConfig.setFailBroadcast(broadcast.get(2).toString());
        List<?> damage = strengthPlus.getList("damage");
        if(damage==null){
            plugin.consoleLog(YamlConfigMessage.ConfigDamageLoadError.getLevelCode(), YamlConfigMessage.ConfigDamageLoadError.getMessage());
            throw new ConfigFileNotFoundException(YamlConfigMessage.ConfigDamageLoadError.getMessage());
        }
        List<?> defence = strengthPlus.getList("defence");
        if(defence==null){
            plugin.consoleLog(YamlConfigMessage.ConfigDefenceLoadError.getLevelCode(), YamlConfigMessage.ConfigDefenceLoadError.getMessage());
            throw new ConfigFileNotFoundException(YamlConfigMessage.ConfigDefenceLoadError.getMessage());
        }
        try {
            essentialsConfig.setSwordDamage(ObjectParseUtils.doubleParse(damage.get(0)));
            essentialsConfig.setBowDamage(ObjectParseUtils.doubleParse(damage.get(1)));
            essentialsConfig.setCrossBowDamage(ObjectParseUtils.doubleParse(damage.get(2)));
            essentialsConfig.setArmorDefence(ObjectParseUtils.doubleParse(defence.get(0)));
            essentialsConfig.setMinDamage(ObjectParseUtils.doubleParse(defence.get(1)));
        } catch (Exception e) {
            plugin.consoleLog(2,"错误！数据转换失败，请确认数据格式正确！");
        }
        plugin.consoleLog(1,essentialsConfig);
    }

    /**
     * 读取强化等级配置文件
     */
    private void reloadStrengthLevel(){
        ConfigurationSection levelConfig = fileConfiguration.getConfigurationSection("strength-level");
        if (levelConfig == null){
            plugin.consoleLog(2, "错误，strength-level.yml下的strength_level数据不存在或文件不存在！");
            return;
        }
        List<Map<?, ?>> mapList = levelConfig.getMapList("strength-level");
        for (Map<?, ?> levelMap : mapList) {
            try {
                StrengthLevel strengthLevel = new StrengthLevel();
                strengthLevel.setNormalStoneCost(ObjectParseUtils.integerParse(levelMap.get("normalStone")));
                strengthLevel.setStrengthChance(ObjectParseUtils.integerParse(levelMap.get("chance")));
                strengthLevel.setLoseLevel(ObjectParseUtils.booleanParse(levelMap.get("loseLevel")));
                strengthLevel.setBreakItem(ObjectParseUtils.booleanParse(levelMap.get("break")));
                strengthLevels.add(strengthLevel);
            } catch (Exception e) {
                plugin.consoleLog(2,"错误！strength-level配置文件读取错误！");
            }
        }
        plugin.consoleLog(1,strengthLevels);
    }

    private void reloadStrengthStone(){
        ConfigurationSection strengthStone = fileConfiguration.getConfigurationSection("strength-stone");
        if(strengthStone==null){
            plugin.consoleLog(2,"");
            return;
        }
    }

    public List<StrengthLevel> getStrengthLevel() {
        return strengthLevels;
    }

    public StrengthStone getStrengthStone() {
        return strengthStone;
    }

    public EssentialsConfig getEssentialsConfig() {
        return essentialsConfig;
    }

    public boolean isDebugStatus() {
        return debugStatus;
    }
}
