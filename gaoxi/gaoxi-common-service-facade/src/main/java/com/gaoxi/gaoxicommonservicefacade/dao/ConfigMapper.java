package com.gaoxi.gaoxicommonservicefacade.dao;

import com.gaoxi.gaoxicommonservicefacade.model.Config;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigMapper {
	
	@Delete("DELETE FROM ConfigInfo WHERE id =#{id}")
    int deleteByPrimaryKey(Integer id);
    
    @Insert("INSERT INTO ConfigInfo(id,managesalary,staffsalary,cleanerssalary,manage,staff,cleaner,totalmoney,totalroom) VALUES(#{id}, #{managesalary}, #{staffsalary}, #{cleanerssalary}, #{manage}, #{staff}, #{cleaner}, #{totalmoney}, #{totalroom})")
 	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    int insert(Config record);

    @Select("SELECT * FROM ConfigInfo WHERE id=#{arg0}")
    Config selectByPrimaryKey(Integer id);
    
    @Update("UPDATE ConfigInfo SET id=#{id},managesalary=#{managesalary},staffsalary=#{staffsalary},cleanerssalary=#{cleanerssalary},manage=#{manage},staff=#{staff},cleaner=#{cleaner},totalmoney=#{totalmoney},totalroom=#{totalroom} WHERE id =#{id}")
    int updateByPrimaryKeySelective(Config record);
    
    @Update("UPDATE ConfigInfo SET id=#{id},managesalary=#{managesalary},staffsalary=#{staffsalary},cleanerssalary=#{cleanerssalary},manage=#{manage},staff=#{staff},cleaner=#{cleaner},totalmoney=#{totalmoney},totalroom=#{totalroom} WHERE id =#{id}")
    int updateByPrimaryKey(Config record);
    
    @Insert("INSERT INTO ConfigInfo(id,managesalary,staffsalary,cleanerssalary,manage,staff,cleaner,totalmoney,totalroom) VALUES(#{id}, #{managesalary}, #{staffsalary}, #{cleanerssalary}, #{manage}, #{staff}, #{cleaner}, #{totalmoney}, #{totalroom})")
 	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    int insertSelective(Config record);
}