package top.mccat.domain;

/**
 * @author Distance
 * @date 2022/5/17 10:05
 */
public class StrengthExtra {
    private double swordDamage;
    private double bowDamage;
    private double crossBowDamage;
    private double minDamage;
    private double armorDefence;

    public StrengthExtra() {
    }

    public StrengthExtra(double swordDamage, double bowDamage, double crossBowDamage, double minDamage, double armorDefence) {
        this.swordDamage = swordDamage;
        this.bowDamage = bowDamage;
        this.crossBowDamage = crossBowDamage;
        this.minDamage = minDamage;
        this.armorDefence = armorDefence;
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
}
