package top.mccat.enums;

/**
 * @author Distance
 * @date 2022/5/24 14:38
 */
public enum StrengthLoreLabel {
    /**
     * 基础 Damage Label参数
     */
    EssentialsSharpLabel("&b[&4锋利&b]", 1),
    EssentialsPowerLabel("&b[&4矢锋&b]", 1),
    /**
     * 基础 Defence Label 参数
     */
    EssentialsDefenceLabel("&b[&1防御&b]", 1),
    /**
     * 特殊 damage label参数
     */
    HeartStealingLabel("&b[&c生命偷取]",3),
    AbsoluteDamageLabel("&b[&e穿透伤害&b]", 3),
    KillPowerLabel("&b[&d嗜血屠夫&b]",3)
    ;
    private final String loreLabel;
    private final Integer status;

    StrengthLoreLabel(String loreLabel, Integer status) {
        this.loreLabel = loreLabel;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public String getLoreLabel() {
        return loreLabel;
    }

    @Override
    public String toString() {
        return "StrengthLoreLabel{" +
                "loreLabel='" + loreLabel + '\'' +
                '}';
    }
}
