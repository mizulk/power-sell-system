package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.skadi.powersellsys.pojo.Supplier;

import java.util.List;

public interface SupplierMapper {
    @Select("SELECT * FROM suppliers;")
    List<Supplier> selectAll();

    @Select( "SELECT * FROM suppliers WHERE `name` = #{name} AND `password` = #{password}")
    Supplier getSupplierByAccountAndPassword(@Param("name") String name, @Param("password") String password);

    @Insert("INSERT INTO suppliers(`name`,`password`,`tel`,`address`,`zip_code`) VALUES(#{name},#{password},#{tel},#{address},#{zipCode})")
    void insertAccount(Supplier supplier);

    @Select("SELECT * from suppliers where `tel`= #{tel}")
    Supplier getTelexists(@Param("tel") String tel);

}
