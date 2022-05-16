package top.mccat.domain.config;

import top.mccat.domain.StrengthLevel;
import top.mccat.domain.StrengthStone;

import java.util.List;

/**
 * @author Raven
 * @date 2022/05/05 21:24
 * config.yml配置文件
 */
public class EssentialsConfig {
    private String title;
    private String divider;
    private String levelIcon;
    private String successNotify;
    private String failNotify;
    private String successBroadcast;
    private String safeBroadcast;
    private String failBroadcast;
    private double swordDamage;
    private double bowDamage;
    private double crossBowDamage;
    private double minDamage;
    private double armorDefence;

    public EssentialsConfig() {
    }

    public EssentialsConfig(String title, String divider, String levelIcon, String successNotify, String failNotify, String successBroadcast, String safeBroadcast, String failBroadcast, double swordDamage, double bowDamage, double crossBowDamage, double minDamage, double armorDefence) {
        this.title = title;
        this.divider = divider;
        this.successNotify = successNotify;
        this.failNotify = failNotify;
        this.levelIcon = levelIcon;
        this.successBroadcast = successBroadcast;
        this.safeBroadcast = safeBroadcast;
        this.failBroadcast = failBroadcast;
        this.swordDamage = swordDamage;
        this.bowDamage = bowDamage;
        this.crossBowDamage = crossBowDamage;
        this.minDamage = minDamage;
        this.armorDefence = armorDefence;
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

    public double getSwordDamage() {
        return swordDamage;
    }

    public void setSwordDamage(double swordDamage) {
        this.swordDamage = swordDamage;
    }

    public double getBowDamage() {
        return bowDamage;
    }

    public void setBowDamage(double bowDamage) {
        this.bowDamage = bowDamage;
    }

    public double getCrossBowDamage() {
        return crossBowDamage;
    }

    public void setCrossBowDamage(double crossBowDamage) {
        this.crossBowDamage = crossBowDamage;
    }

    public double getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(double minDamage) {
        this.minDamage = minDamage;
    }

    public double getArmorDefence() {
        return armorDefence;
    }

    public void setArmorDefence(double armorDefence) {
        this.armorDefence = armorDefence;
    }

    @Override
    public String toString() {
        return "EssentialsConfig{" +
                "title='" + title + '\'' +
                ", divider='" + divider + '\'' +
                ", levelIcon='" + levelIcon + '\'' +
                ", successNotify='" + successNotify + '\'' +
                ", failNotify='" + failNotify + '\'' +
                ", successBroadcast='" + successBroadcast + '\'' +
                ", safeBroadcast='" + safeBroadcast + '\'' +
                ", failBroadcast='" + failBroadcast + '\'' +
                ", swordDamage=" + swordDamage +
                ", bowDamage=" + bowDamage +
                ", crossBowDamage=" + crossBowDamage +
                ", minDamage=" + minDamage +
                ", armorDefence=" + armorDefence +
                '}';
    }
}
