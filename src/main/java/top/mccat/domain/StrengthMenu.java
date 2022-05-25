package top.mccat.domain;

/**
 * @author Distance
 * @date 2022/5/18 17:12
 */
public class StrengthMenu {
    private String menuTitle;
    private boolean menuEnable;
    private boolean chanceDisplay;

    public StrengthMenu(){}

    public StrengthMenu(String menuTitle, boolean menuEnable, boolean chanceDisplay) {
        this.menuTitle = menuTitle;
        this.menuEnable = menuEnable;
        this.chanceDisplay = chanceDisplay;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public boolean isMenuEnable() {
        return menuEnable;
    }

    public void setMenuEnable(boolean menuEnable) {
        this.menuEnable = menuEnable;
    }

    public boolean isChanceDisplay() {
        return chanceDisplay;
    }

    public void setChanceDisplay(boolean chanceDisplay) {
        this.chanceDisplay = chanceDisplay;
    }

    @Override
    public String toString() {
        return "StrengthMenu{" +
                "menuTitle='" + menuTitle + '\'' +
                ", menuEnable=" + menuEnable +
                ", chanceDisplay=" + chanceDisplay +
                '}';
    }
}
