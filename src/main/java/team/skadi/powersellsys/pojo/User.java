package team.skadi.powersellsys.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
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
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	private LocalDateTime loginTime;
}
