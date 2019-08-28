package com.gaoxi.gaoxiuser.redis;

import com.gaoxi.gaoxicommonservicefacade.dao.UserInfo;
import com.gaoxi.gaoxicommonservicefacade.model.UserInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.Null;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
@CacheConfig(cacheNames="UserInfoCache") // 本类内方法指定使用缓存时，默认的名称就是userInfoCache
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
public class AdminServiceRedis {
	@Autowired
    private UserInfo adminMapper;
	
	// 因为必须要有返回值，才能保存到数据库中，如果保存的对象的某些字段是需要数据库生成的，
    //那保存对象进数据库的时候，就没必要放到缓存了
    @CachePut(key="#p0.id")  //#p0表示第一个参数
    //必须要有返回值，否则没数据放到缓存中
    public UserInfoData insertUser(UserInfoData u){
        this.adminMapper.insertSelective(u);
        //u对象中可能只有只几个有效字段，其他字段值靠数据库生成，比如id
        return this.adminMapper.selectByPrimaryKey(u.getUserid());
    }
    
    @CachePut(key="#p0.id")
    public UserInfoData updateUser(UserInfoData u){
        this.adminMapper.updateByPrimaryKeySelective(u);
        //可能只是更新某几个字段而已，所以查次数据库把数据全部拿出来全部
        return this.adminMapper.selectByPrimaryKey(u.getUserid());
    }
    
    @Null//Nullable
    @Cacheable(key="#p0") // @Cacheable 会先查询缓存，如果缓存中存在，则不执行方法
    public UserInfoData findById(String id){
        System.err.println("根据id=" + id +"获取用户对象，从数据库中获取");
        Assert.notNull(id,"id不用为空");
        int uid = Integer.parseInt(id);
        return this.adminMapper.selectByPrimaryKey(uid);
    }
    
    @CacheEvict(key="#p0")  //删除缓存名称为userInfoCache,key等于指定的id对应的缓存
    public void deleteById(String id){
    	int uid = Integer.parseInt(id);
        this.adminMapper.deleteByPrimaryKey(uid);
    }
}
