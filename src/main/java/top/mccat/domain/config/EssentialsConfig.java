package top.mccat.domain.config;

/**
 * @author Raven
 * @date 2022/05/05 21:24
 * config.yml配置文件
 */
public class EssentialsConfig {
    private String pluginName;
    private String title;
    private String divider;
    private String levelIcon;
    private String successNotify;
    private String failNotify;
    private String successBroadcast;
    private String safeBroadcast;
    private String failBroadcast;

    public EssentialsConfig() {
    }

    public EssentialsConfig(String pluginName, String title, String divider, String levelIcon, String successNotify, String failNotify, String successBroadcast, String safeBroadcast, String failBroadcast) {
        this.pluginName = pluginName;
        this.title = title;
        this.divider = divider;
        this.levelIcon = levelIcon;
        this.successNotify = successNotify;
        this.failNotify = failNotify;
        this.successBroadcast = successBroadcast;
        this.safeBroadcast = safeBroadcast;
        this.failBroadcast = failBroadcast;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDivider() {
        return divider;
    }

    public void setDivider(String divider) {
        this.divider = divider;
    }

    public String getLevelIcon() {
        return levelIcon;
    }

    public void setLevelIcon(String levelIcon) {
        this.levelIcon = levelIcon;
    }

    public String getSuccessNotify() {
        return successNotify;
    }

    public void setSuccessNotify(String successNotify) {
        this.successNotify = successNotify;
    }

    public String getFailNotify() {
        return failNotify;
    }

    public void setFailNotify(String failNotify) {
        this.failNotify = failNotify;
    }

    public String getSuccessBroadcast() {
        return successBroadcast;
    }

    public void setSuccessBroadcast(String successBroadcast) {
        this.successBroadcast = successBroadcast;
    }

    public String getSafeBroadcast() {
        return safeBroadcast;
    }

    public void setSafeBroadcast(String safeBroadcast) {
        this.safeBroadcast = safeBroadcast;
    }

    public String getFailBroadcast() {
        return failBroadcast;
    }

    public void setFailBroadcast(String failBroadcast) {
        this.failBroadcast = failBroadcast;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    @Override
    public String toString() {
        return "EssentialsConfig{" +
                "pluginName='" + pluginName + '\'' +
                ", title='" + title + '\'' +
                ", divider='" + divider + '\'' +
                ", levelIcon='" + levelIcon + '\'' +
                ", successNotify='" + successNotify + '\'' +
                ", failNotify='" + failNotify + '\'' +
                ", successBroadcast='" + successBroadcast + '\'' +
                ", safeBroadcast='" + safeBroadcast + '\'' +
                ", failBroadcast='" + failBroadcast + '\'' +
                '}';
    }
}
