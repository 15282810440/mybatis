import entity.TJobHistory;
import entity.TUser;
import mapper.TJobHistoryAnnoMapper;
import mapper.TUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MybatisTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init(){
        try {
            String resource = "mybatis-config.xml";
            InputStream in = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test1(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper userMapper = sqlSession.getMapper(TUserMapper.class);
        TUser user = userMapper.selectAll(1);
        System.out.println(user);
    }

//    @Test
    //测试插入数据自动生成
    /* 前提
    *   当前数据库主键必须为自增长
    *   必须在xml insert标签中配置  useGeneratedKeys = true  keyProperty = "id"
    *   那么在 当前新增的数据对象中 当前的新增列的id就会注入到当前对象中来
    * */
//    public void testInsertGenerateId1(){
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        TUserMapper userMapper = sqlSession.getMapper(TUserMapper.class);
//        TUser user = new TUser();
//        user.setEmail("1059@qq.com");
//        user.setMobile("15282810440");
//        user.setRealName("账号");
//        userMapper.insert1(user);
//        sqlSession.commit();
//        System.out.println(user.getId());
//    }
//
//    @Test
//    //测试插入数据自动生成
//    //设置
//    //      <selectKey keyProperty="id" order="AFTER" resultType="int">
//    //			select LAST_INSERT_ID()
//    //		</selectKey>
//    //      after 会在执行insert语句后 进行 selectKey标签里的语句 然后将查询出来的数据装填到 keyProperty 字段中去  resultType 表示查询返回的数据类型
//    public void testInsertGenerateId2(){
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        TUserMapper userMapper = sqlSession.getMapper(TUserMapper.class);
//        TUser user = new TUser();
//        user.setEmail("1059@qq.com");
//        user.setMobile("15282810440");
//        user.setRealName("账号");
//        userMapper.insert2(user);
//        sqlSession.commit();
//        System.out.println(user.getId());
//    }
//
//    @Test
//    // 多参数查询
//    // 多参数传递时  如果在5个以下的参数传递可以使用 多参数传递
//    // 如果是5个以上的参数传递 那么尽量使用对象传递
//    public void testManyParamQuery(){
//        //map查询
//        //传递的map的话 需要标注传递的是map类型  使用传入的map的key来获取参数
//        //多参数查询
//        //多参数传递的话 使用 @Param 注解指定参数的key是多少  然后根据key来获取对应的参数
//        //对象传递查询
//        //对象传递的话 需要标注传递的bean类型，  使用字段名称来获取参数
//    }
//    @Test
//    //参数#和参数$区别测试(动态sql 入门)
//    public void testSymbol(){
//        // #{key,jdbcType=jdbcType}
//        // # 获取的值会根据  jdbcType 来自动处理 如果是非数字类型 那么会自动在当前值的前后加上 '' 单引号  防止sql注入
//
//        // ${key}
//        // $ 获取的值不会做任何改动 传入的是什么值 那么就会追加到当前的sql中去 非常的灵活 但是非常容易出问题 只能内部系统使用
//    }
//
//    @Test
//    //注解测试
//    //<!-- 映射文件，mapper的配置文件 -->
//    //	<mappers>
//    //		<!--直接映射到相应的mapper文件 -->
//    //		<mapper resource="sqlmapper/TUserMapper.xml" />
//    //		<mapper class="mapper.TJobHistoryAnnoMapper"></mapper>
//    //	</mappers>
//    // 注解mapper类 必须被 mybatis mappers 扫描到
//    //
//    public void testAnnotation(){
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        TJobHistoryAnnoMapper tJobHistoryAnnoMapper = sqlSession.getMapper(TJobHistoryAnnoMapper.class);
//        List<TJobHistory> tJobHistories = tJobHistoryAnnoMapper.selectByUserId(1);
//        for(TJobHistory tJobHistory : tJobHistories){
//            System.out.println(tJobHistory);
//        }
//    }
//
//    @Test
//    //测试自动映射
//    //自动映射  默认是全名称映射  select t_name from table  那么返回的对象列也必须有 t_name字段 否则没法映射
//    //自动映射规则可以在
//    //  <settings>
//    //		<setting name="mapUnderscoreToCamelCase" value="true" />
//    //	</settings>
//    //
//    public void testAutoMapping(){
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        TUserMapper userMapper = sqlSession.getMapper(TUserMapper.class);
//        TUser user = userMapper.selectById(1);
//        System.out.println(user);
//    }
//
//    // if 用于select并于where配合
//    // where 标签里面 如果没有if符合 那么当前 where 关键字就不会存在 实际实现是 trim标签
//    //where标签解决了如果没条件符合  那么就会抛出异常的问题 也解决了 where 1=1的尴尬写法问题
//    @Test
//    public void testSelectIfOper(){
//
//    }
//
//    @Test
//    //if用于update 与set配合
//    //set标签会将最后一个参数的 最后一个 逗号 , 给去除
//    // set标签解决了 内部if标签的尾部逗号导致sql格式错误的问题
//    public void testUpdateIfOper(){
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        TUserMapper userMapper = sqlSession.getMapper(TUserMapper.class);
//        TUser user2 = new TUser();
//        user2.setEmail("10591@qq.com2");
//        user2.setMobile("1528281044012");
//        user2.setRealName("账号12");
//        user2.setId(21);
//        userMapper.updateIfOper(user2);
//        sqlSession.commit();
//    }
//
//    @Test
//    //测试 insert标签 if标签 和trim标签的使用
//    //对比加trim的写法和不加trim的写法的优雅实现
//    public void testInsertIfOper(){
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        TUserMapper userMapper = sqlSession.getMapper(TUserMapper.class);
//        TUser user = new TUser();
//        user.setEmail("1059@qq.com");
//        user.setMobile("15282810440");
//        user.setRealName("账号");
////        user.setPositionId(1);
////        userMapper.insertIfOper(user);
//        userMapper.insertSelective(user);
//        sqlSession.commit();
//        System.out.println(user);
//    }
//    @Test
//    //foreach用于In查询
//    //  foreach 标签 解决数组集合的参数传入 需要群体匹配的问题
//    public void testForeach4In(){
//
//    }
//
//    @Test
//    //foreach用于批量插入
//    public void testForeach4Insert(){
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        TUserMapper userMapper = sqlSession.getMapper(TUserMapper.class);
//        TUser user = new TUser();
//        user.setEmail("1059@qq.com");
//        user.setMobile("15282810440");
//        user.setRealName("账号");
//        TUser user1 = new TUser();
//        user1.setEmail("10591@qq.com");
//        user1.setMobile("152828104401");
//        user1.setRealName("账号1");
//        userMapper.insertForeach4Batch(Arrays.asList(user, user1));
//        sqlSession.commit();
//    }
//
//    @Test
//    //批量更新
//    //ExecutorType.REUSE 在自动提交为true的时候 这个执行方式是即刻执行即刻提交的，  在执行完sql后，事务就已经提交了， 在自动提交为false的时候  提交是延迟的，直到显示调用commit方法才会将数据提交到数据库
//    //ExecutorType.REUSE 会重用statement 执行sql操作  相同的sql  不用重新创建statement对象  多个请求 多次发送 但是相同的
//    //ExecutorType.BATCH 在自动提交为true的时候 这个执行方式是批量提交的，只有在调用commit方法才会提交到数据库，如果是非自动提交 那么当前也是只有在调用commit方法才会提交到数据库
//    //ExecutorType.BATCH 会批量提交sql  多个sql 一次发送
//    //ExecutorType.SIMPLE 默认值  每次执行sql  都会自动提交 , 自动提交为false  会进入事务模式
//    public void testBatchExcutor() {
////        设置自动提交
////        SqlSession sqlSession = sqlSessionFactory.openSession(true);
//        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE, true);
//        TUserMapper userMapper = sqlSession.getMapper(TUserMapper.class);
//        TUser user1 = new TUser();
//        user1.setEmail("10591@qq.com");
//        user1.setMobile("152828104401");
//        user1.setRealName("账号");
//        userMapper.insertSelective(user1);
//        System.out.println(user1);
//        TUser user2 = new TUser();
//        user2.setEmail("10591@qq.com21");
//        user2.setMobile("152828104401");
//        user2.setRealName("z账号211");
//        user2.setId(21);
//        userMapper.updateIfOper(user2);
//        System.out.println(user2);
//        sqlSession.commit();
//    }


    @Test
    //测试两种关联方式
    public void testAssociation(){
        // 2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获取对应mapper
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        // 4.执行查询语句并返回结果
        // ----------------------
        List<TUser> selectUserJobs1 = mapper.selectUserJobs1();
        List<TUser> selectUserJobs2 = mapper.selectUserJobs2();
        for (TUser tUser : selectUserJobs1) {
            System.out.println(tUser);
        }
        for (TUser tUser : selectUserJobs2) {
            System.out.println(tUser.getJobs().size());
        }
    }


    @Test
    //测试自动映射
    public void testAutoMapping(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper userMapper = sqlSession.getMapper(TUserMapper.class);
        TUser user = userMapper.selectById(1);
        System.out.println(user);
    }

    public static void main(String[] args) {
        int count = 0;

        int pron, two , five ;
        for(pron = 0; pron<=50; pron ++){
            for(two = 0; two<=50; two ++){
                for(five = 0; five<=20; five ++){

                    if(((1*pron) + (2*two) + (5 * (50-pron-two))) == 100 && (pron + two + (50 - pron - two)) == 50 && (pron + two + five) == 50){
                        count++;
                        System.out.println("1分的有:" + pron + "; 2分的有 :" + two+"; 五分的有:" + five);
                    }
                }
            }
        }
        System.out.println("一共有 "+ count + "可能");
    }



}
