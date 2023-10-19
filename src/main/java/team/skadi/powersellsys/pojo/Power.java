package team.skadi.powersellsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Power {
    private Integer id;
    private String name;
    private Integer type;
    private String model;
    private Integer capacity;
    private Integer stock;
    private Double price;
    private Byte discount;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String describe;
    private Integer sum;
}
