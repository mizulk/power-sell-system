package team.skadi.powersellsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritePower {
	private Integer id;
	private Integer userId;
	private Integer powerId;
	private String name;
	private Integer stock;
	private Double price;
	private Byte discount;
	private String describe;
	private Integer capacity;
	private String value;
}
