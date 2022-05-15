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
import java.util.*;

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
    private List<StrengthStone> strengthStones = new ArrayList<>();
    private List<String> strengthItems = new ArrayList<>();
    private boolean debugStatus = false;
    public static final String PLUGIN_VERSION = "2.1-Alpha";
    public ConfigFactory(StrengthPlus plugin){
        this.plugin = plugin;
        File dataFolder = plugin.getDataFolder();
        config = new File(dataFolder,"config.yml");
        strengthLevelFile = new File(dataFolder, "strength-level.yml");
        strengthStoneFile = new File(dataFolder,"strength-stone.yml");
        strengthItemFile = new File(dataFolder,"strength-item.yml");
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
            fileConfiguration.load(strengthStoneFile);
            reloadStrengthStone();
            fileConfiguration.load(strengthItemFile);
            reloadStrengthItem();
            plugin.consoleLog(1,"配置文件读取成功！");
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
     * @throws ConfigValueNotFoundException 配置文件未找到异常
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
        ConfigurationSection notify = strengthPlus.getConfigurationSection("notify");
        if(notify==null){
            plugin.consoleLog(YamlConfigMessage.ConfigNotifyLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigNotifyLoadError.getMessage());
        }
        essentialsConfig.setSuccessNotify(notify.getString("success"));
        essentialsConfig.setFailNotify(notify.getString("fail"));
        ConfigurationSection broadcast = strengthPlus.getConfigurationSection("broadcast");
        if(broadcast==null){
            plugin.consoleLog(YamlConfigMessage.ConfigBroadcastLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigBroadcastLoadError.getMessage());
        }
        essentialsConfig.setSuccessBroadcast(broadcast.getString("success"));
        essentialsConfig.setSafeBroadcast(broadcast.getString("safe"));
        essentialsConfig.setFailBroadcast(broadcast.getString("fail"));
        ConfigurationSection damage = strengthPlus.getConfigurationSection("damage");
        if(damage==null){
            plugin.consoleLog(YamlConfigMessage.ConfigDamageLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigDamageLoadError.getMessage());
        }
        ConfigurationSection defence = strengthPlus.getConfigurationSection("defence");
        if(defence==null){
            plugin.consoleLog(YamlConfigMessage.ConfigDefenceLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigDefenceLoadError.getMessage());
        }
        essentialsConfig.setSwordDamage(damage.getDouble("sword"));
        essentialsConfig.setBowDamage(damage.getDouble("bow"));
        essentialsConfig.setCrossBowDamage(damage.getDouble("crossbow"));
        essentialsConfig.setArmorDefence(defence.getDouble("armorDefence"));
        essentialsConfig.setMinDamage(defence.getDouble("minDamage"));
        plugin.consoleLog(1,essentialsConfig);
    }

    /**
     * 读取强化等级配置文件
     * @throws ConfigValueNotFoundException 配置文件未找到异常
     */
    private void reloadStrengthLevel() throws ConfigValueNotFoundException {
        strengthLevels.clear();
        ConfigurationSection levelConfig = fileConfiguration.getConfigurationSection("strength-level");
        if (levelConfig == null){
            plugin.consoleLog(YamlConfigMessage.ConfigStrengthLevelLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigStrengthLevelLoadError.getMessage());
        }
        //获取强化等级key和参数
        Map<String, Object> values = levelConfig.getValues(false);
        Set<String> keySet = values.keySet();
        for (String key : keySet) {
            Map<String, Object> levelExtra = levelConfig.getValues(true);
            try {
                StrengthLevel strengthLevel = new StrengthLevel();
                strengthLevel.setNormalStoneCost(ObjectParseUtils.integerParse(levelExtra.get(key+".normalStone")));
                strengthLevel.setStrengthChance(ObjectParseUtils.integerParse(levelExtra.get(key+".chance")));
                strengthLevel.setLoseLevel(ObjectParseUtils.booleanParse(levelExtra.get(key+".loseLevel")));
                strengthLevel.setBreakItem(ObjectParseUtils.booleanParse(levelExtra.get(key+".break")));
                strengthLevels.add(strengthLevel);
            } catch (ExtraParseException e) {
                plugin.consoleLog(2,"strength-level.yml下的等级"+e.getMessage());
            }
        }
        plugin.consoleLog(1,strengthLevels);
    }

    /**
     * 读取强化石配置文件
     * @throws ConfigValueNotFoundException 配置文件未找到异常
     */
    private void reloadStrengthStone() throws ConfigValueNotFoundException {
        strengthStones.clear();
        ConfigurationSection stoneConfig = fileConfiguration.getConfigurationSection("strength-stone");
        if(stoneConfig==null){
            plugin.consoleLog(YamlConfigMessage.ConfigStrengthStoneLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigStrengthStoneLoadError.getMessage());
        }
        //获取强化石key和参数
        Map<String, Object> values = stoneConfig.getValues(false);
        Set<String> keySet = values.keySet();
        for(String key : keySet){
            StrengthStone strengthStone = new StrengthStone();
            Map<String, Object> stoneExtra = stoneConfig.getValues(true);
            strengthStone.setStoneName(stoneExtra.get(key+".name").toString());
            strengthStone.setLore(stoneConfig.getStringList(key+".lore"));
            strengthStone.setSafe(ObjectParseUtils.booleanParse(stoneExtra.get(key+".isSafe")));
            strengthStone.setSuccess(ObjectParseUtils.booleanParse(stoneExtra.get(key+".isSuccess")));
            strengthStone.setAdmin(ObjectParseUtils.booleanParse(stoneExtra.get(key+".isAdmin")));
            strengthStones.add(strengthStone);
        }
        plugin.consoleLog(1,strengthStones);
    }

    private void reloadStrengthItem() throws ConfigValueNotFoundException {
        strengthItems.clear();
        ConfigurationSection strengthConfig = fileConfiguration.getConfigurationSection("strength-item");
        if(strengthConfig==null) {
            plugin.consoleLog(YamlConfigMessage.ConfigStrengthItemLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigStrengthItemLoadError.getMessage());
        }
        List<?> nameList = strengthConfig.getList("name");
        if(nameList == null){
            plugin.consoleLog(YamlConfigMessage.ConfigStrengthItemLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMessage.ConfigStrengthItemLoadError.getMessage());
        }
        for(Object o : nameList){
            strengthItems.add(o.toString());
        }
        plugin.consoleLog(1,nameList);
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
