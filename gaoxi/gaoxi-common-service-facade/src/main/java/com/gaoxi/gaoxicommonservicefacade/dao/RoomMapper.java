package com.gaoxi.gaoxicommonservicefacade.dao;

import com.gaoxi.gaoxicommonservicefacade.model.Room;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomMapper {
	/**
     * 功能描述：根据id删除用户
     * @param userId
     */
    @Delete("DELETE FROM RoomInfo WHERE roomid =#{roomid}")
    int deleteByPrimaryKey(Integer roomid);
  //推荐使用#{}取值，不要用${},因为存在注入的风险
 	 @Insert("INSERT INTO RoomInfo(local,money,state,type) VALUES(#{local}, #{money}, #{state}, #{type})")
 	 @Options(useGeneratedKeys=true, keyProperty="roomid", keyColumn="roomid")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    int insert(Room record);
    
    @Select("SELECT *FROM RoomInfo WHERE userid=#{arg0}")
    Room selectByPrimaryKey(Integer roomid);
    
    @Options(useGeneratedKeys=true,keyProperty = "id",keyColumn = "id")
    @Update("<script> update RoomInfo "
    		+ "<set> "
    		+ "<if test='local!=null'> local = #{local}, </if> "
    		+ "<if test='money!=null'> money = #{money}, </if> "
    		+ "<if test='state!=null'> state = #{state}, </if>"
    		+ "<if test='type!=null'> type = #{type}, </if>"
    		+ "</set> "
    		+ "where roomid = #{roomid} </script>")
    int updateByPrimaryKeySelective(Room record);
    
    @Options(useGeneratedKeys=true,keyProperty = "id",keyColumn = "id")
    @Update("<script> update RoomInfo "
    		+ "<set> "
    		+ "<if test='local!=null'> local = #{local}, </if> "
    		+ "<if test='money!=null'> money = #{money}, </if> "
    		+ "<if test='state!=null'> state = #{state}, </if>"
    		+ "<if test='type!=null'> type = #{type}, </if>"
    		+ "</set> "
    		+ "where roomid = #{roomid} </script>")
    int updateByPrimaryKey(Room record);
    
    @Select("SELECT * FROM RoomInfo WHERE state=#{state}")
    List<Room> selectRoomByStateType(Room stateType);

    @Insert("INSERT INTO RoomInfo(local,money,state,type) VALUES(#{local}, #{money}, #{state}, #{type})")
	@Options(useGeneratedKeys=true, keyProperty="roomid", keyColumn="roomid")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    int insertSelective(Room record);
}