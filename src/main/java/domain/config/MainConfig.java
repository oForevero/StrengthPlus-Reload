package domain.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raven
 * @date 2022/05/05 21:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainConfig {
    private boolean debug;
    private String title;
}
