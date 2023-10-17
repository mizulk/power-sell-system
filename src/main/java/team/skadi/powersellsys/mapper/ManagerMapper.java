package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.skadi.powersellsys.pojo.Manager;

@Mapper
public interface ManagerMapper {

	@Select("SELECT job_number,`name` FROM manager WHERE job_number = #{jobNumber} AND `password` = #{password}")
	Manager findManagerByJobNumberAndPassword(@Param("jobNumber") Short jobNumber, @Param("password") String password);


}
