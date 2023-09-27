package team.skadi.powersellsys.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
	private Integer id;
	private String name;
	private String tel;
	private String address;
	private String zipCode;
}
