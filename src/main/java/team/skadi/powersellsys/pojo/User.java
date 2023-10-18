package team.skadi.powersellsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Integer id;
	private String account;
	private String password;
	private String name;
	private Byte sex;
	private Short age;
	private String zipCode;
	private String tel;
	private String address;
	private Double balance;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	private LocalDateTime loginTime;
}
