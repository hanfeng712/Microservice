package com.gaoxi.gaoxicommonservicefacade.dao;

import com.gaoxi.gaoxicommonservicefacade.model.UserInfoData;
import org.apache.ibatis.annotations.*;

import java.util.List;
/**
 * @author sifu
 * @version 1.0
 * @date 2017/12/14
 */
public interface UserInfo {
    public String login(String username);
    /**
     *
     * Description: 后台登录<br/>
     *
     * @author 丁鹏
     * @param username
     * @param password
     * @return
     */
    @Select("SELECT COUNT(*) AS num FROM users WHERE username=#{arg0} AND PASSWORD=#{arg1}")
    int isLogin(String username, String password);
    @Select("SELECT *FROM UserInfo WHERE useraccount=#{useraccount}")
    UserInfo selectByAccount(@Param("useraccount") String useraccount);
    @Select("SELECT *FROM UserInfo WHERE userid=#{arg0}")
    UserInfoData selectByPrimaryKey(Integer userid);
    @Select("SELECT *FROM UserInfo WHERE username=#{arg0}")
    UserInfoData selectUserByAmount(String username);
    /**
     * 功能描述：更新对象
     * @param UserInfo
     */
    @Options(useGeneratedKeys=true,keyProperty = "id",keyColumn = "id")
    @Update("<script> update UserInfo "
            + "<set> "
            + "<if test='userid!=null'> userid = #{userid}, </if> "
            + "<if test='useraccount!=null'> useraccount = #{useraccount}, </if> "
            + "<if test='password!=null'> password = #{password}, </if>"
            + "<if test='username!=null'> username = #{username}, </if>"
            + "<if test='power!=null'> photourl = #{power}, </if>"
            + "<if test='idnumber!=null'> idnumber = #{idnumber}, </if>"
            + "<if test='phonenumber!=null'> phonenumber = #{phonenumber}, </if>"
            + "<if test='age!=null'> age = #{age}, </if>"
            + "<if test='photourl!=null'> photourl = #{photourl}, </if>"
            + "<if test='money!=null'> money = #{money}, </if>"
            + "</set> "
            + "where userid = #{userid} </script>")
    int updateByPrimaryKeySelective(UserInfoData record);
    /**
     * 功能描述：根据id删除用户
     * @param userId
     */
    @Delete("DELETE FROM UserInfo WHERE userid =#{userid}")
    int deleteByPrimaryKey(Integer userId);
    /**
     *
     * **/
    //推荐使用#{}取值，不要用${},因为存在注入的风险
    @Insert("INSERT INTO UserInfo(useraccount,password) VALUES(#{useraccount}, #{password} )")
    @Options(useGeneratedKeys=true, keyProperty="userid", keyColumn="userid")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    int insertSelective(UserInfoData user);
    /**
     * 获取相应权限的用户
     * @param power
     * @return
     */
    @Select("SELECT * FROM UserInfo WHERE power=#{arg0}")
    List<UserInfoData> selectByPower(Integer power);
    /**
     *
     * Description: 查询后台导航栏信息<br/>
     *
     * @author 丁鹏
     * @return
     */
    @Select("SELECT * FROM UserInfo")
    List<UserInfoData> getAllUser();
}
