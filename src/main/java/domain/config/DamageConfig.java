package domain.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raven
 * @date 2022/05/05 21:27
 * 伤害数据配置实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DamageConfig {
    private double swordDamage;
    private double bowDamage;
    private double crossBowDamage;
    private double minDamage;
    private double maxDamage;
}
