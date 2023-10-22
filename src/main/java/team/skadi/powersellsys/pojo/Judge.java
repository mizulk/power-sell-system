package team.skadi.powersellsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Judge {
    private String powerName;
    private String userAccount;
    private Byte star;
    private String content;
    private LocalDateTime createTime;
}
