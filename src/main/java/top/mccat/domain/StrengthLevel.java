package top.mccat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raven
 * @date 2022/05/06 19:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StrengthLevel {
    private Integer normalStoneCost;
    private boolean loseLevel;
    private boolean breakItem;
    private Integer strengthChance;
}
