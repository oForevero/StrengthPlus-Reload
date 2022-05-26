package top.mccat.factory;

import com.sun.istack.internal.NotNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import top.mccat.StrengthPlus;
import top.mccat.domain.StrengthExtra;
import top.mccat.domain.StrengthLevel;
import top.mccat.domain.StrengthMenu;
import top.mccat.domain.StrengthStone;
import top.mccat.domain.config.EssentialsConfig;
import top.mccat.enums.Color;
import top.mccat.enums.msg.YamlConfigMsg;
import top.mccat.exception.ConfigValueNotFoundException;
import top.mccat.exception.ExtraParseException;
import top.mccat.utils.LogUtils;
import top.mccat.utils.MsgUtils;
import top.mccat.utils.ObjectParseUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private final File strengthExtraFile;
    private final File strengthMenuFile;
    private final LogUtils logUtils;
    private MsgUtils msgUtils;
    /**
     * 实体参数对象
     */
    private final StrengthStone strengthStone = new StrengthStone();
    private final EssentialsConfig essentialsConfig = new EssentialsConfig();
    private final StrengthExtra strengthExtra = new StrengthExtra();
    private final StrengthMenu strengthMenu = new StrengthMenu();
    private List<StrengthLevel> strengthLevels = new ArrayList<>();
    private List<StrengthStone> strengthStones = new ArrayList<>();
    private List<String> strengthItems = new ArrayList<>();
    private boolean debugStatus = false;
    public static final String PLUGIN_VERSION = "2.1-Alpha";

    public ConfigFactory(StrengthPlus plugin, LogUtils logUtils, MsgUtils msgUtils){
        this.plugin = plugin;
        this.logUtils = logUtils;
        this.msgUtils = msgUtils;
        File dataFolder = plugin.getDataFolder();
        config = new File(dataFolder,"config.yml");
        strengthLevelFile = new File(dataFolder, "strength-level.yml");
        strengthStoneFile = new File(dataFolder,"strength-stone.yml");
        strengthItemFile = new File(dataFolder,"strength-item.yml");
        strengthExtraFile = new File(dataFolder, "strength-extra.yml");
        strengthMenuFile = new File(dataFolder, "strength-menu.yml");
    }

    /**
     * 将yml写入plugin文件夹的io方法
     */
    public void readConfigFile(){
        try {
            msgUtils.sendToConsole("正在读取默认配置文件......",Color.LightGreen);
            fileConfiguration.load(config);
            reloadEssentialsConfig();
            fileConfiguration.load(strengthLevelFile);
            reloadStrengthLevel();
            fileConfiguration.load(strengthStoneFile);
            reloadStrengthStone();
            fileConfiguration.load(strengthItemFile);
            reloadStrengthItem();
            fileConfiguration.load(strengthExtraFile);
            reloadStrengthExtra();
            fileConfiguration.load(strengthMenuFile);
            reloadStrengthMenu();
            msgUtils.sendToConsole("配置文件读取成功！",Color.LightRed);
        } catch (IOException | InvalidConfigurationException e) {
            msgUtils.sendToConsole("配置文件IO读取错误，正在重新生成配置文件！", Color.LightRed);
            msgUtils.sendToConsole("正在写入默认配置文件......",Color.LightGreen);
            writeConfigFile();
            readConfigFile();
        } catch (ConfigValueNotFoundException e){
            //当Config文件中读取值失败执行，个人思路是直接读取本地默认参数
            msgUtils.sendToConsole(e.getMessage(),Color.Red);
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
        plugin.saveResource("strength-extra.yml",false);
        plugin.saveResource("strength-menu.yml",false);
    }

    /**
     * 加载基础配置文件
     * @throws ConfigValueNotFoundException 配置文件未找到异常
     */
    private void reloadEssentialsConfig() throws ConfigValueNotFoundException {
        ConfigurationSection strengthPlus = fileConfiguration.getConfigurationSection("strengthPlus");
        if(strengthPlus==null){
            logUtils.consoleLog(YamlConfigMsg.ConfigStrengthPlusLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMsg.ConfigStrengthPlusLoadError.getMsg());
        }
        debugStatus = strengthPlus.getBoolean("debug");
        //设置log状态
        logUtils.setDebugStatus(debugStatus);
        essentialsConfig.setPluginName(strengthPlus.getString("pluginName"));
        essentialsConfig.setTitle(strengthPlus.getString("title"));
        essentialsConfig.setDivider("divider");
        essentialsConfig.setLevelIcon("levelIcon");
        ConfigurationSection notify = strengthPlus.getConfigurationSection("notify");
        if(notify==null){
            logUtils.consoleLog(YamlConfigMsg.ConfigNotifyLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMsg.ConfigNotifyLoadError.getMsg());
        }
        essentialsConfig.setSuccessNotify(notify.getString("success"));
        essentialsConfig.setFailNotify(notify.getString("fail"));
        ConfigurationSection broadcast = strengthPlus.getConfigurationSection("broadcast");
        if(broadcast==null){
            logUtils.consoleLog(YamlConfigMsg.ConfigBroadcastLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMsg.ConfigBroadcastLoadError.getMsg());
        }
        essentialsConfig.setSuccessBroadcast(broadcast.getString("success"));
        essentialsConfig.setSafeBroadcast(broadcast.getString("safe"));
        essentialsConfig.setFailBroadcast(broadcast.getString("fail"));
        logUtils.consoleLog(1,essentialsConfig);
    }

    /**
     * 读取强化等级配置文件
     * @throws ConfigValueNotFoundException 配置文件未找到异常
     */
    private void reloadStrengthLevel() throws ConfigValueNotFoundException {
        strengthLevels.clear();
        ConfigurationSection levelConfig = fileConfiguration.getConfigurationSection("strength-level");
        if (levelConfig == null){
            logUtils.consoleLog(YamlConfigMsg.ConfigStrengthLevelLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMsg.ConfigStrengthLevelLoadError.getMsg());
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
                strengthLevel.setLoseLevel(getBooleanDefaultConfigExtra(levelExtra,key+".loseLevel"));
                strengthLevel.setBreakItem(getBooleanDefaultConfigExtra(levelExtra,key+".break"));
                strengthLevels.add(strengthLevel);
            } catch (ExtraParseException e) {
                logUtils.consoleLog(2,"strength-level.yml下的等级"+e.getMessage());
            }
        }
        logUtils.consoleLog(1,strengthLevels);
    }

    /**
     * 读取强化石配置文件
     * @throws ConfigValueNotFoundException 配置文件未找到异常
     */
    private void reloadStrengthStone() throws ConfigValueNotFoundException {
        strengthStones.clear();
        ConfigurationSection stoneConfig = fileConfiguration.getConfigurationSection("strength-stone");
        if(stoneConfig==null){
            logUtils.consoleLog(YamlConfigMsg.ConfigStrengthStoneLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMsg.ConfigStrengthStoneLoadError.getMsg());
        }
        //获取强化石key和参数
        Map<String, Object> values = stoneConfig.getValues(false);
        Set<String> keySet = values.keySet();
        for(String key : keySet){
            StrengthStone strengthStone = new StrengthStone();
            Map<String, Object> stoneExtra = stoneConfig.getValues(true);
            strengthStone.setStoneName(stoneExtra.get(key+".name").toString());
            strengthStone.setLore(stoneConfig.getStringList(key+".lore"));
            strengthStone.setSafe(getBooleanDefaultConfigExtra(stoneExtra,key+".isSafe"));
            strengthStone.setSuccess(getBooleanDefaultConfigExtra(stoneExtra,key+".isSuccess"));
            strengthStone.setAdmin(getBooleanDefaultConfigExtra(stoneExtra,key+".isAdmin"));
            try {
                strengthStone.setStrengthExtra(getIntegerDefaultConfigExtra(stoneExtra,key+".chanceExtra"));
            } catch (ExtraParseException e) {
                logUtils.consoleLog(2,"strength-stone.yml下的额外几率"+e.getMessage());
            }
            strengthStones.add(strengthStone);
        }
        logUtils.consoleLog(1,strengthStones);
    }

    /**
     * 重载强化物品
     * @throws ConfigValueNotFoundException
     */
    private void reloadStrengthItem() throws ConfigValueNotFoundException {
        strengthItems.clear();
        ConfigurationSection strengthConfig = fileConfiguration.getConfigurationSection("strength-item");
        if(strengthConfig==null) {
            logUtils.consoleLog(YamlConfigMsg.ConfigStrengthItemLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMsg.ConfigStrengthItemLoadError.getMsg());
        }
        List<?> nameList = strengthConfig.getList("name");
        if(nameList == null){
            logUtils.consoleLog(YamlConfigMsg.ConfigStrengthItemLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMsg.ConfigStrengthItemLoadError.getMsg());
        }
        for(Object o : nameList){
            strengthItems.add(o.toString());
        }
        logUtils.consoleLog(1,nameList);
    }

    /**
     * 重载强化伤害参数
     * @throws ConfigValueNotFoundException
     */
    private void reloadStrengthExtra() throws ConfigValueNotFoundException{
        ConfigurationSection extraConfig = fileConfiguration.getConfigurationSection("strength-extra");
        if(extraConfig==null){
            logUtils.consoleLog(YamlConfigMsg.ConfigDamageLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMsg.ConfigStrengthExtraLoadError.getMsg());
        }
        ConfigurationSection damage = extraConfig.getConfigurationSection("damage");
        if(damage==null){
            logUtils.consoleLog(YamlConfigMsg.ConfigDamageLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMsg.ConfigDamageLoadError.getMsg());
        }
        ConfigurationSection defence = extraConfig.getConfigurationSection("defence");
        if(defence==null){
            logUtils.consoleLog(YamlConfigMsg.ConfigDefenceLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMsg.ConfigDefenceLoadError.getMsg());
        }
        strengthExtra.setSwordDamage(damage.getDouble("sword"));
        strengthExtra.setBowDamage(damage.getDouble("bow"));
        strengthExtra.setCrossBowDamage(damage.getDouble("crossbow"));
        strengthExtra.setArmorDefence(defence.getDouble("armorDefence"));
        strengthExtra.setMinDamage(defence.getDouble("minDamage"));
        logUtils.consoleLog(1,strengthExtra);
    }

    public void reloadStrengthMenu() throws ConfigValueNotFoundException{
        ConfigurationSection menuConfig = fileConfiguration.getConfigurationSection("strength-menu");
        if(menuConfig==null){
            logUtils.consoleLog(YamlConfigMsg.ConfigStrengthMenuLoadError);
            throw new ConfigValueNotFoundException(YamlConfigMsg.ConfigStrengthExtraLoadError.getMsg());
        }
        strengthMenu.setMenuTitle(menuConfig.getString("menuTitle"));
        strengthMenu.setMenuEnable(menuConfig.getBoolean("enable"));
        strengthMenu.setChanceDisplay(menuConfig.getBoolean("menuDisplay"));
        logUtils.consoleLog(1,strengthMenu);
    }

    /**
     * 读取config默认参数，如不存在则设置默认boolean值为false
     * @param mapData map参数
     * @param key 键值
     * @return 布尔值
     */
    private boolean getBooleanDefaultConfigExtra(@NotNull Map mapData, @NotNull String key){
        if(!mapData.containsKey(key)){
            return false;
        }else {
            return ObjectParseUtils.booleanParse(mapData.get(key));
        }
    }

    private Integer getIntegerDefaultConfigExtra(@NotNull Map mapData, @NotNull String key) throws ExtraParseException {
        if(!mapData.containsKey(key)){
            return null;
        }else {
            return ObjectParseUtils.integerParse(mapData.get(key));
        }
    }

    public StrengthStone getStrengthStone() {
        return strengthStone;
    }

    public EssentialsConfig getEssentialsConfig() {
        return essentialsConfig;
    }

    public StrengthExtra getStrengthExtra() {
        return strengthExtra;
    }

    public StrengthMenu getStrengthMenu() {
        return strengthMenu;
    }

    public List<StrengthLevel> getStrengthLevels() {
        return strengthLevels;
    }

    public List<StrengthStone> getStrengthStones() {
        return strengthStones;
    }

    public List<String> getStrengthItems() {
        return strengthItems;
    }

    public boolean isDebugStatus() {
        return debugStatus;
    }

    public void setMsgUtils(MsgUtils msgUtils) {
        this.msgUtils = msgUtils;
    }
}
