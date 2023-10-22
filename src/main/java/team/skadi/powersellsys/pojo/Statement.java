package team.skadi.powersellsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statement {
    private Integer powerId;
    private String powerName;
    private Integer sum;
    private Float totalPrice;
}
