package top.mccat.ui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.mccat.domain.StrengthMenu;
import top.mccat.utils.ColorUtils;

/**
 * @author Distance
 * @date 2022/5/25 16:14
 */
public class StrengthChestInventory {
    private StrengthMenu strengthMenu;
    private final ItemStack air = new ItemStack(Material.AIR);
    private final ItemStack progressBar = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
    private final ItemStack strengthDividerGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    private final ItemStack enchantingTable = new ItemStack(Material.ENCHANTING_TABLE);
    private final ItemStack ironBars = new ItemStack(Material.IRON_BARS);
    private final ItemStack fire = new ItemStack(Material.CAMPFIRE);
    /**
     * 整体ui数组，特殊按钮等用air itemstack填充，强化物品放置栏用
     */
    private final ItemStack[] STRENGTH_UI = new ItemStack[]
            {strengthDividerGlass,strengthDividerGlass,strengthDividerGlass,ironBars,ironBars,ironBars,ironBars,strengthDividerGlass,air,
            strengthDividerGlass,strengthDividerGlass,strengthDividerGlass,ironBars,air,air,ironBars,strengthDividerGlass,air,
            strengthDividerGlass,air,strengthDividerGlass,ironBars,air,air,ironBars,strengthDividerGlass,air,
            strengthDividerGlass,enchantingTable,strengthDividerGlass,ironBars,ironBars,ironBars,ironBars,strengthDividerGlass,air,
            strengthDividerGlass,strengthDividerGlass,strengthDividerGlass,ironBars,fire,fire,ironBars,strengthDividerGlass,air,
            progressBar,progressBar,progressBar,progressBar,progressBar,progressBar,progressBar,progressBar,progressBar};

    public StrengthChestInventory() {

    }

    public StrengthChestInventory(StrengthMenu strengthMenu) {
        this.strengthMenu = strengthMenu;
        setItemName(enchantingTable,"&b强化物品台");
        setItemName(fire,"&5强化石放置处");
        setItemName(progressBar,"&a进度条");
        setItemName(ironBars,"&8分割线");
        setItemName(strengthDividerGlass, "&8分隔板");
    }

    public Inventory getStrengthInventory(){
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, strengthMenu.getMenuTitle());
        inventory.setContents(STRENGTH_UI);
        return inventory;
    }

    public void setStrengthMenu(StrengthMenu strengthMenu) {
        this.strengthMenu = strengthMenu;
    }

    /**
     * 设置物品名
     * @param stack stack参数
     * @param name 物品名
     */
    private void setItemName(ItemStack stack, String name){
        ItemMeta itemMeta = stack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(ColorUtils.getColorStr(name));
        stack.setItemMeta(itemMeta);
    }
}
