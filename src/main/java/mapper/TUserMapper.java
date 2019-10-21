package mapper;


import entity.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserMapper {
	
    public TUser selectAll(Integer id);


    int insert1(TUser user);

    int insert2(TUser user);

    TUser selectById(int i);

    int insertIfOper(TUser user);

    int insertSelective(TUser user);

    int insertForeach4Batch(@Param("userList") List<TUser> userList);

    int updateIfOper(TUser user2);

    List<TUser> selectUserJobs1();

    List<TUser> selectUserJobs2();
}