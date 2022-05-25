package top.mccat.ui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import top.mccat.domain.StrengthMenu;

/**
 * @author Distance
 * @date 2022/5/25 16:14
 */
public class StrengthChestInventory {
    private StrengthMenu strengthMenu;
    private final ItemStack air = new ItemStack(Material.AIR);
    private final ItemStack strengthProgressBarGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    private final ItemStack strengthDividerGlass = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
    private final ItemStack enchantingTable = new ItemStack(Material.ENCHANTING_TABLE);
    private final ItemStack ironBars = new ItemStack(Material.IRON_BARS);
    private final ItemStack fire = new ItemStack(Material.FIRE);
    /**
     * 整体ui数组，特殊按钮等用air itemstack填充，强化物品放置栏用
     */
    private final ItemStack[] STRENGTH_UI = new ItemStack[]
            {strengthDividerGlass,strengthDividerGlass,strengthDividerGlass,ironBars,ironBars,ironBars,ironBars,strengthDividerGlass,air,
            strengthDividerGlass,strengthDividerGlass,strengthDividerGlass,ironBars,air,air,ironBars,strengthDividerGlass,};
    public StrengthChestInventory() {
    }

    public StrengthChestInventory(StrengthMenu strengthMenu) {
        this.strengthMenu = strengthMenu;
    }

    public Inventory getStrengthInventory(){
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, strengthMenu.getMenuTitle());
        inventory.setContents(STRENGTH_UI);
        return inventory;
    }

    public void setStrengthMenu(StrengthMenu strengthMenu) {
        this.strengthMenu = strengthMenu;
    }
}
