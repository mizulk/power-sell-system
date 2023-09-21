package team.skadi.powersellsys.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
	Integer id;
	String account;
	String password;
	Byte sex;
	Short age;
	String zipCode;
	String tel;
	String address;
	LocalDateTime createTime;
	LocalDateTime updateTime;
	LocalDateTime loginTime;
}
