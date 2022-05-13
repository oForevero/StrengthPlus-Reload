package top.mccat.factory;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.meta.ItemMeta;
import top.mccat.StrengthPlus;
import top.mccat.domain.StrengthLevel;
import top.mccat.domain.StrengthStone;
import top.mccat.domain.config.EssentialsConfig;
import top.mccat.enums.YamlConfigMessage;
import top.mccat.exception.ConfigValueNotFoundException;
import top.mccat.exception.ExtraParseException;
import top.mccat.utils.ObjectParseUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    private final StrengthStone strengthStone = new StrengthStone();
    private final EssentialsConfig essentialsConfig = new EssentialsConfig();
    private List<StrengthLevel> strengthLevels = new ArrayList<>();
    private List<String> strengthItems = new ArrayList<>();
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
            reloadStrengthItem();
            fileConfiguration.load(strengthStoneFile);
            reloadStrengthStone();
        } catch (IOException | InvalidConfigurationException e) {
            plugin.consoleLog(2,"配置文件IO读取错误，正在重新生成配置文件！");
            plugin.consoleLog(1,"正在写入默认配置文件......");
            writeConfigFile();
            plugin.consoleLog(1,"正在读取默认配置文件......");
            readConfigFile();
            plugin.consoleLog(1,"读取默认配置文件成功！");
        } catch (ConfigValueNotFoundException e){
            //当Config文件中读取值失败执行，个人思路是直接读取本地默认参数
        }
    }

    /**
     * 从插件中写入yml文件到插件文件夹中
     */
    public void writeConfigFile(){
        plugin.saveResource("config.yml",false);
        plugin.saveResource("strength-level.yml",false);
        plugin.saveResource("strength-item.yml",false);
        plugin.saveResource("strength-stone.yml",false);
    }

    /**
     * 加载基础配置文件
     */
    private void reloadEssentialsConfig() throws ConfigValueNotFoundException {
        ConfigurationSection strengthPlus = fileConfiguration.getConfigurationSection("strengthPlus");
        if(strengthPlus==null){
            plugin.consoleLog(YamlConfigMessage.ConfigStrengthPlusLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigStrengthPlusLoadError.getMessage());
        }
        debugStatus = strengthPlus.getBoolean("debug");
        essentialsConfig.setTitle(strengthPlus.getString("title"));
        essentialsConfig.setDivider("divider");
        essentialsConfig.setLevelIcon("levelIcon");
        List<String> notify= strengthPlus.getStringList("notify");
            if(notify==null){

                plugin.consoleLog(YamlConfigMessage.ConfigNotifyLoadError);
                throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigNotifyLoadError.getMessage());
        }
        essentialsConfig.setSuccessNotify(notify.get(0).toString());
        essentialsConfig.setFailNotify(notify.get(1).toString());
        List<?> broadcast = strengthPlus.getList("broadcast");
        if(broadcast==null){
            plugin.consoleLog(YamlConfigMessage.ConfigBroadcastLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigBroadcastLoadError.getMessage());
        }
        essentialsConfig.setSuccessBroadcast(broadcast.get(0).toString());
        essentialsConfig.setSafeBroadcast(broadcast.get(1).toString());
        essentialsConfig.setFailBroadcast(broadcast.get(2).toString());
        List<?> damage = strengthPlus.getList("damage");
        if(damage==null){
            plugin.consoleLog(YamlConfigMessage.ConfigDamageLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigDamageLoadError.getMessage());
        }
        List<?> defence = strengthPlus.getList("defence");
        if(defence==null){
            plugin.consoleLog(YamlConfigMessage.ConfigDefenceLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigDefenceLoadError.getMessage());
        }
        try {
            essentialsConfig.setSwordDamage(ObjectParseUtils.doubleParse(damage.get(0)));
            essentialsConfig.setBowDamage(ObjectParseUtils.doubleParse(damage.get(1)));
            essentialsConfig.setCrossBowDamage(ObjectParseUtils.doubleParse(damage.get(2)));
            essentialsConfig.setArmorDefence(ObjectParseUtils.doubleParse(defence.get(0)));
            essentialsConfig.setMinDamage(ObjectParseUtils.doubleParse(defence.get(1)));
        } catch (ExtraParseException e) {
            plugin.consoleLog(2,"config.yml下的damage"+e.getMessage());
        }
        plugin.consoleLog(1,essentialsConfig);
    }

    /**
     * 读取强化等级配置文件
     */
    private void reloadStrengthLevel() throws ConfigValueNotFoundException {
        strengthLevels = null;
        ConfigurationSection levelConfig = fileConfiguration.getConfigurationSection("strength-level");
        if (levelConfig == null){
            plugin.consoleLog(YamlConfigMessage.ConfigStrengthLevelLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigStrengthLevelLoadError.getMessage());
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
            } catch (ExtraParseException e) {
                plugin.consoleLog(2,"strength-level.yml下的strength-level"+e.getMessage());
            }
        }
        plugin.consoleLog(1,strengthLevels);
    }

    private void reloadStrengthStone() throws ConfigValueNotFoundException {
        ConfigurationSection strengthStone = fileConfiguration.getConfigurationSection("strength-stone");
        if(strengthStone==null){
            plugin.consoleLog(YamlConfigMessage.ConfigStrengthStoneLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigStrengthStoneLoadError.getMessage());
        }
        //暂未知道是否成功，故搁置
        //StrengthStone normalStone = strengthStone.getObject("normal-stone", StrengthStone.class);
        List<Map<?, ?>> normalStone = strengthStone.getMapList("normal-stone");
        List<Map<?, ?>> safeStone = strengthStone.getMapList("safe-stone");
        List<Map<?, ?>> successStone = strengthStone.getMapList("success-stone");
        List<Map<?, ?>> adminStone = strengthStone.getMapList("admin-stone");
    }

    private void reloadStrengthItem() throws ConfigValueNotFoundException {
        strengthItems = null;
        ConfigurationSection strengthItem = fileConfiguration.getConfigurationSection("strength-item");
        if(strengthItem==null){
            plugin.consoleLog(YamlConfigMessage.ConfigStrengthItemLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigStrengthItemLoadError.getMessage());
        }
        List<?> list = strengthItem.getList("strength-item");
        for(Object o : list){
            strengthItems.add(o.toString());
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
