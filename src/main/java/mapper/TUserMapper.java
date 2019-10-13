package mapper;


import entity.TUser;

public interface TUserMapper {
	
    public TUser selectAll(Integer id);


    int insert1(TUser user);

    int insert2(TUser user);

    TUser selectById(int i);

    int insertIfOper(TUser user);

    int insertSelective(TUser user);
}