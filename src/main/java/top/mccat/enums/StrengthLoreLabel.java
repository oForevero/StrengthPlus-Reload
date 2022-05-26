package top.mccat.enums;

/**
 * @author Distance
 * @date 2022/5/24 14:38
 */
public enum StrengthLoreLabel {
    /**
     * 基础 Damage Label参数
     */
    EssentialsSharpLabel("&b[&4伤害&b]", 1),
    EssentialsPowerLabel("&b[&4弓力&b]", 2),
    /**
     * 基础 Defence Label 参数
     */
    EssentialsDefenceLabel("&b[&1防御&b]", 3),

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
