package utils.enums;

/**
 * @author Raven
 * @date 2022/05/05 21:07
 */
public enum Color {
    /**
     * 对应枚举颜色类
     */
    Black("&0"),
    DarkBlue("&1"),
    Green("&2"),
    DarkCyan("&3"),
    Red("&4"),
    Purple("&5"),
    Gold("&6"),
    Gray("&7"),
    DarkGray("&8"),
    Blue("&9"),
    LightGreen("&a"),
    Cyan("&b"),
    LightRed("&c"),
    LightPurple("&d"),
    LightGold("&e"),
    White("&f");
    private final String colorStr;

    Color(String colorStr) {
        this.colorStr = colorStr;
    }

    public String getColorStr() {
        return colorStr;
    }

    @Override
    public String toString() {
        return getColorStr();
    }
}
