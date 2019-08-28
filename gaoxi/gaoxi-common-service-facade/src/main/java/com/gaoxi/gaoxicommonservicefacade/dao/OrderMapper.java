package com.gaoxi.gaoxicommonservicefacade.dao;

import com.gaoxi.gaoxicommonservicefacade.model.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
	@Delete("DELETE FROM OrderInfo WHERE orderid =#{orderid}")
    int deleteByPrimaryKey(Integer orderid);
	
	@Insert("INSERT INTO OrderInfo(orderid,householdname,id,starttime,endtime,money,state,roomid,userid) VALUES(#{orderid}, #{householdname}, #{id}, #{starttime}, #{starttime}, #{endtime}, #{money}, #{state}, #{roomid}, #{userid})")
 	@Options(useGeneratedKeys=true, keyProperty="orderid", keyColumn="orderid")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    int insert(Order record);
	
	@Insert("INSERT INTO OrderInfo(orderid,householdname,id,starttime,endtime,money,state,roomid,userid) VALUES(#{orderid}, #{householdname}, #{id}, #{starttime}, #{starttime}, #{endtime}, #{money}, #{state}, #{roomid}, #{userid})")
 	@Options(useGeneratedKeys=true, keyProperty="orderid", keyColumn="orderid")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    int insertSelective(Order record);
	
	@Select("SELECT *FROM OrderInfo WHERE userid=#{arg0}")
    Order selectByPrimaryKey(Integer orderid);
	@Options(useGeneratedKeys=true,keyProperty = "id",keyColumn = "id")
    @Update("<script> update OrderInfo "
    		+ "<set> "
    		+ "<if test='orderid!=null'> orderid = #{orderid}, </if> "
    		+ "<if test='householdname!=null'> householdname = #{householdname}, </if> "
    		+ "<if test='id!=null'> id = #{id}, </if>"
    		+ "<if test='starttime!=null'> starttime = #{starttime}, </if>"
    		+ "<if test='endtime!=null'> endtime = #{endtime}, </if>"
    		+ "<if test='money!=null'> money = #{money}, </if>"
    		+ "<if test='state!=null'> state = #{state}, </if>"
    		+ "<if test='roomid!=null'> roomid = #{roomid}, </if>"
    		+ "<if test='userid!=null'> userid = #{userid}, </if>"
    		+ "</set> "
    		+ "where orderid = #{orderid} </script>")
    int updateByPrimaryKeySelective(Order record);
	
	@Options(useGeneratedKeys=true,keyProperty = "id",keyColumn = "id")
    @Update("<script> update OrderInfo "
    		+ "<set> "
    		+ "<if test='orderid!=null'> orderid = #{orderid}, </if> "
    		+ "<if test='householdname!=null'> householdname = #{householdname}, </if> "
    		+ "<if test='id!=null'> id = #{id}, </if>"
    		+ "<if test='starttime!=null'> starttime = #{starttime}, </if>"
    		+ "<if test='endtime!=null'> endtime = #{endtime}, </if>"
    		+ "<if test='money!=null'> money = #{money}, </if>"
    		+ "<if test='state!=null'> state = #{state}, </if>"
    		+ "<if test='roomid!=null'> roomid = #{roomid}, </if>"
    		+ "<if test='userid!=null'> userid = #{userid}, </if>"
    		+ "</set> "
    		+ "where orderid = #{orderid} </script>")
    int updateByPrimaryKey(Order record);
	
	@Select("SELECT * FROM OrderInfo")
    List<Order> getAllUser();
}