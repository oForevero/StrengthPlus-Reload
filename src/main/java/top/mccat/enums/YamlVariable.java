package top.mccat.enums;

/**
 * @author Distance
 * @date 2022/5/17 15:17
 */
public enum YamlVariable {
    /**
     * 对应玩家变量和武器等级变量
     */
    PlayerVariable("%player%"),
    WeaponLevelVariable("%level%");
    private String variable;

    YamlVariable(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }
}
