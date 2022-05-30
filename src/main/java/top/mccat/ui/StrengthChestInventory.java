package top.mccat.ui;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import top.mccat.domain.StrengthMenu;
import top.mccat.domain.StrengthStone;
import top.mccat.enums.Color;
import top.mccat.enums.msg.StrengthPlusMsg;
import top.mccat.factory.ThreadPoolFactory;
import top.mccat.utils.ColorUtils;
import top.mccat.utils.LogUtils;
import top.mccat.utils.MsgUtils;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Distance
 * @date 2022/5/25 16:14
 */
public class StrengthChestInventory implements Listener{
    private StrengthMenu strengthMenu;
    private final ItemStack displayBar = new ItemStack(Material.PAINTING);
    private final ItemStack air = new ItemStack(Material.AIR);
    private final ItemStack progressBar = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
    private final ItemStack runningBar = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
    private final ItemStack successProgressBar = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
    private final ItemStack failProgressBar = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
    private final ItemStack strengthDividerGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    private final ItemStack enchantingTable = new ItemStack(Material.ENCHANTING_TABLE);
    private final ItemStack ironBars = new ItemStack(Material.IRON_BARS);
    private final ItemStack fire = new ItemStack(Material.SOUL_CAMPFIRE);
    private final ItemStack startButton = new ItemStack(Material.END_CRYSTAL);
    private final ItemStack extraTable = new ItemStack(Material.END_PORTAL_FRAME);
    private final ThreadPoolExecutor threadPool = ThreadPoolFactory.getThreadPool();
    private Thread uiSonThread;
    private LogUtils logUtils;
    private MsgUtils msgUtils;
    private List<StrengthStone> stoneList;
    private final int inventorySize = 54;
    Map<Player,Boolean> playerInMenuMap = new HashMap();
    /**
     * 整体ui数组，特殊按钮等用air itemstack填充，强化物品放置栏用
     */
    private final ItemStack[] STRENGTH_UI = new ItemStack[]
            {strengthDividerGlass,strengthDividerGlass,strengthDividerGlass,ironBars,ironBars,ironBars,ironBars,strengthDividerGlass,displayBar,
            strengthDividerGlass,strengthDividerGlass,strengthDividerGlass,ironBars,air,air,ironBars,strengthDividerGlass,strengthDividerGlass,
            strengthDividerGlass,air,strengthDividerGlass,ironBars,ironBars,ironBars,ironBars,strengthDividerGlass,air,
            strengthDividerGlass,enchantingTable,strengthDividerGlass,ironBars,fire,fire,ironBars,strengthDividerGlass,extraTable,
            strengthDividerGlass,strengthDividerGlass,strengthDividerGlass,strengthDividerGlass,strengthDividerGlass,strengthDividerGlass,strengthDividerGlass,strengthDividerGlass,strengthDividerGlass,
            progressBar,progressBar,progressBar,progressBar,progressBar,progressBar,progressBar,strengthDividerGlass,startButton};

    public StrengthChestInventory() {
        setItemName(enchantingTable,"&b强化物品台");
        setItemName(fire,"&5灵火锻造");
        setItemName(progressBar,"&a进度条");
        setItemName(ironBars,"&8分割线");
        setItemName(strengthDividerGlass, "&8分隔板");
        setItemName(startButton,"&c开始强化");
        setItemName(extraTable,"&b附加物品台");
        setItemName(displayBar, "&a强化信息");
    }

    public StrengthChestInventory(StrengthMenu strengthMenu, LogUtils logUtils, MsgUtils msgUtils) {
        this();
        this.strengthMenu = strengthMenu;
        this.logUtils = logUtils;
        this.msgUtils = msgUtils;
    }

    public Inventory getStrengthInventory(){
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, ColorUtils.getColorStr(strengthMenu.getMenuTitle()));
        inventory.setContents(STRENGTH_UI);
        return inventory;
    }

    /**
     * 进行强化ui操作监听
     * @param clickEvent 点击强化事件
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClickEvent(InventoryClickEvent clickEvent){
//        如果被取消则直接return
        if(clickEvent.isCancelled()){
            return;
        }
//        如果不为玩家直接return
        if(!(clickEvent.getWhoClicked() instanceof Player)){
            return;
        }
        InventoryView inventoryView = clickEvent.getWhoClicked().getOpenInventory();
        Inventory inventory = clickEvent.getInventory();
//        如果其强化菜单名不为玩家设定的参数，且箱子大小不为54直接return
        String parseTitle = ColorUtils.parseColorStr(strengthMenu.getMenuTitle());
        if(!inventoryView.getTitle().equals(parseTitle) || inventory.getSize() != inventorySize){
            return;
        }
//        如果存在玩家正在强化则取消强化事件
        Player player = (Player) clickEvent.getWhoClicked();
        if(playerInMenuMap.containsKey(player) && playerInMenuMap.get(player)){
            clickEvent.setCancelled(true);
            msgUtils.sendToPlayer(StrengthPlusMsg.StrengthNotFinish.getMsg(),player, Color.LightGreen);
            return;
        }
//        0-53为上层物品栏
        int location = clickEvent.getRawSlot();
        logUtils.consoleLog(1,"on click event");
        logUtils.consoleLog(1,location);
        if (location >= 0 && location < inventorySize){
            switch (clickEvent.getRawSlot()){
                //强化石左槽位
                case 13:
                    break;
                //强化石右槽位
                case 14:
                    break;
                //强化武器放置槽位
                case 19:
                    break;
                //强化点击槽位
                case 53:
                    clickEvent.setCancelled(true);
                    playerInMenuMap.put(player,true);
                    strengthAnimation(inventory,1, player);
                    logUtils.consoleLog(1, PlaceholderAPI.setPlaceholders(player,"%strengthplus_item%"));
                    break;
                default:
                    clickEvent.setCancelled(true);
            }
        }
    }

    /**
     * 当强化ui进行关闭的事件
     * @param closeEvent 关闭事件
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryCloseEvent(InventoryCloseEvent closeEvent){
        Inventory inventory = closeEvent.getInventory();
//        如果不为54格或者不为箱子则直接return
        if(inventory.getSize() != 54 || !inventory.getType().equals(InventoryType.CHEST)){
            return;
        }
        InventoryView view = closeEvent.getView();
        if(!view.getTitle().equals(strengthMenu.getMenuTitle())){
            return;
        }
        Player player = (Player)closeEvent.getPlayer();
        logUtils.consoleLog(1,"on close event");
//        如果存在玩家正在强化则取消强化事件
        playerInMenuMap.replace(player,false);
        //获取左右强化石槽位
        ItemStack leftStrengthStoneLocation = inventory.getItem(13);
        ItemStack rightStrengthStoneLocation = inventory.getItem(14);
        //获取强化物品和附加强化石
        ItemStack strengthItem = inventory.getItem(19);
        ItemStack stoneExtraLocation = inventory.getItem(29);
        PlayerInventory playerInventory = player.getInventory();
        BlockingQueue<Runnable> queue = threadPool.getQueue();

    }

    public void setStrengthMenu(StrengthMenu strengthMenu) {
        this.strengthMenu = strengthMenu;
    }

    /**
     * 强化动画及其使用，使用线程池方法
     * @param inventory 背包参数
     * @param level 武器等级
     */
    private void strengthAnimation(Inventory inventory, int level, Player player){
        threadPool.execute(()->{
//            循环开始和闪烁次数
            int i = 45;
            int time = 0;
            while(i < 52){
                try {
                    inventory.setItem(i,runningBar);
                    i++;
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    logUtils.consoleLog(2,"警告，线程阻塞，可能是并发导致的问题！");
                    break;
                }
            }
            while(time < 6){
                try {
                    strengthFinishAnimation(inventory,time,true);
                    time++;
                } catch (InterruptedException e) {
                    logUtils.consoleLog(2,"警告，线程阻塞，可能是并发导致的问题！");
                    break;
                }
            }
            playerInMenuMap.put(player,false);
        });
    }

    /**
     * 进行强化结束动画展示
     * @param inventory 强化ui
     * @param time 闪烁次数
     * @param success 是否强化成功
     */
    private void strengthFinishAnimation(Inventory inventory,int time, boolean success) throws InterruptedException {
        int start = 0;
        if(success){
            while(start < time){
                for(int i = 45; i < 52; i++){
                    inventory.setItem(i,modifyProgressBarStatus(time));
                }
                Thread.sleep(200);
                start++;
            }
        }else {
            for (int i = 45; i < 52; i++){
                inventory.setItem(i,modifyProgressBarStatus(time));
            }
        }
    }

    /**
     * 切换动画状态
     * @param time 次数
     * @return ItemStack 对象
     */
    private ItemStack modifyProgressBarStatus(int time){
        if(time%2==0){
            return successProgressBar;
        }
        return progressBar;
    }

    /**
     * 设置物品名
     * @param stack itemStack对象
     * @param name 物品名
     */
    private void setItemName(ItemStack stack, String name){
        ItemMeta itemMeta = stack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(ColorUtils.getColorStr(name));
        stack.setItemMeta(itemMeta);
    }

    /**
     * 设置物品lore参数
     * @param stack itemStack对象
     * @param lore 物品lore
     */
    private void setItemLore(ItemStack stack, List<String> lore){
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setLore(lore);
        stack.setItemMeta(meta);
    }
}
