package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import team.skadi.powersellsys.pojo.Supplier;

import java.util.List;

public interface SupplierMapper {
	@Select("SELECT * FROM suppliers;")
	List<Supplier> selectAll();

	@Select("SELECT * FROM suppliers WHERE `account` = #{account} AND `password` = #{password}")
	Supplier getSupplierByAccountAndPassword(@Param("account") String account, @Param("password") String password);

	@Insert("INSERT INTO suppliers(`name`,`password`,`tel`,`address`,`zip_code`) VALUES(#{name},#{password},#{tel},#{address},#{zipCode})")
	void insertNewAccount(Supplier supplier);

	@Select("SELECT id FROM suppliers WHERE account IS NULL")
	Integer getEmptyAccountId();

	@Update("UPDATE suppliers SET account = #{account} WHERE id = #{id}")
	void updateAccount(Supplier supplier);

	@Select("SELECT * from suppliers where `tel`= #{tel}")
	Supplier getTelexists(@Param("tel") String tel);

	@Select("SELECT id,`account`,`name`,`password`,`tel`,`address`,`zip_code` FROM suppliers WHERE `account`= #{account}")
	Supplier findSupplierByAccount(@Param("account") String account);

	@Select("SELECT `name` FROM suppliers WHERE id = #{id}")
	String findSupplierNameById(@Param("id") Integer id);

	List<Supplier> page(Supplier supplier);

	void updateSupplier(Supplier supplier);
}
