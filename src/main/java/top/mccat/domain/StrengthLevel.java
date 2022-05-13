package top.mccat.domain;

/**
 * @author Raven
 * @date 2022/05/06 19:00
 */
public class StrengthLevel {
    private Integer normalStoneCost;
    private boolean loseLevel;
    private boolean breakItem;
    private Integer strengthChance;

    public StrengthLevel() {
    }

    public StrengthLevel(Integer normalStoneCost, boolean loseLevel, boolean breakItem, Integer strengthChance) {
        this.normalStoneCost = normalStoneCost;
        this.loseLevel = loseLevel;
        this.breakItem = breakItem;
        this.strengthChance = strengthChance;
    }

    public Integer getNormalStoneCost() {
        return normalStoneCost;
    }

    public void setNormalStoneCost(Integer normalStoneCost) {
        this.normalStoneCost = normalStoneCost;
    }

    public boolean isLoseLevel() {
        return loseLevel;
    }

    public void setLoseLevel(boolean loseLevel) {
        this.loseLevel = loseLevel;
    }

    public boolean isBreakItem() {
        return breakItem;
    }

    public void setBreakItem(boolean breakItem) {
        this.breakItem = breakItem;
    }

    public Integer getStrengthChance() {
        return strengthChance;
    }

    public void setStrengthChance(Integer strengthChance) {
        this.strengthChance = strengthChance;
    }
}
