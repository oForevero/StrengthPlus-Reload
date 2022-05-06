package top.mccat.enums;

/**
 * @author Raven
 * @date 2022/05/06 19:29
 */
public enum Permission {
    /**
     * 对应强化权限，分层单个给予
     */
    StrengthMenu("strengthPlus.menu",false),
    StrengthItem("strengthPlus.use",false),
    GiveStrengthStone("strengthPlus.admin.give",true),
    AdminStrength("strengthPlus.admin.strength",true),
    StrengthPlusReload("strengthPlus.admin.reload",true),
    //以下为角色权限，user对应非admin权限，admin对应所有权限
    User("top.mccat.StrengthPlus.User",false),
    Admin("top.mccat.StrengthPlus.Admin",true)
    ;

    private final String permission;
    private final boolean isAdmin;

    Permission(String permission, boolean isAdmin) {
        this.permission = permission;
        this.isAdmin = isAdmin;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
