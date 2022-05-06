package domain.config;

import domain.StrengthLevel;
import domain.StrengthStone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Raven
 * @date 2022/05/05 21:24
 * config.yml配置文件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EssentialsConfig {
    private boolean debug;
    private String title;
    private String successNotify;
    private String failNotify;
    private String successBroadcast;
    private String safeBroadcast;
    private String failBroadcast;
    private double swordDamage;
    private double bowDamage;
    private double crossBowDamage;
    private double minDamage;
    private double armorDefence;
    private List<StrengthLevel> strengthLevels;
    private List<StrengthStone> strengthStones;
    private List<Integer> strengthLevelChance;
    private List<String> strengthItem;
}
