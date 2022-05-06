package top.mccat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Raven
 * @date 2022/05/06 17:59
 * 强化石对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StrengthStone {
    private String stoneName;
    private List<String> lore;
    private boolean isSafe;
    private boolean isSuccess;
    private boolean isAdmin;
}
