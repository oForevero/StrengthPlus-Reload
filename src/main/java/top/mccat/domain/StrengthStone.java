package top.mccat.domain;

import java.util.List;

/**
 * @author Raven
 * @date 2022/05/06 17:59
 * 强化石对象
 */
public class StrengthStone {
    private String stoneName;
    private List<String> lore;
    private boolean isSafe;
    private boolean isSuccess;
    private boolean isAdmin;
    private Integer strengthExtra;
    public StrengthStone() {
    }

    public StrengthStone(String stoneName, List<String> lore, boolean isSafe, boolean isSuccess, boolean isAdmin, Integer strengthExtra) {
        this.stoneName = stoneName;
        this.lore = lore;
        this.isSafe = isSafe;
        this.isSuccess = isSuccess;
        this.isAdmin = isAdmin;
        this.strengthExtra = strengthExtra;
    }

    public String getStoneName() {
        return stoneName;
    }

    public void setStoneName(String stoneName) {
        this.stoneName = stoneName;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Integer getStrengthExtra() {
        return strengthExtra;
    }

    public void setStrengthExtra(Integer strengthExtra) {
        this.strengthExtra = strengthExtra;
    }

    @Override
    public String toString() {
        return "StrengthStone{" +
                "stoneName='" + stoneName + '\'' +
                ", lore=" + lore +
                ", isSafe=" + isSafe +
                ", isSuccess=" + isSuccess +
                ", isAdmin=" + isAdmin +
                ", strengthExtra=" + strengthExtra +
                '}';
    }
}
