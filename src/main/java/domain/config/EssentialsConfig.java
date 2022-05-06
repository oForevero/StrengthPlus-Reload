package domain.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private double swordDamage;
    private double bowDamage;
    private double crossBowDamage;
    private double minDamage;
    private double armorDefence;
}
