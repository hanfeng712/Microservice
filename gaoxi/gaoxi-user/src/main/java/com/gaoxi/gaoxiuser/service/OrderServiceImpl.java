package com.gaoxi.gaoxiuser.service;

import com.github.pagehelper.PageHelper;
import cn.java.mapper.ConfigMapper;
import cn.java.mapper.OrderMapper;
import cn.java.mapper.RoomMapper;
import cn.java.mapper.model.Config;
import cn.java.mapper.model.Order;
import cn.java.mapper.model.Room;
import cn.java.service.OrderService;
import cn.java.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    ConfigMapper configMapper;

    @Override
    public boolean addOrder(String householdname, String id, String starttime, String endtime, int roomid, int userid) {
        Room room = roomMapper.selectByPrimaryKey(roomid);
        if(room.getState()!=1){
            return false;
        }
        Order order = new Order();
        order.setHouseholdname(householdname);
        order.setId(id);
        order.setStarttime(TimeUtil.formatterTime(starttime));
        order.setEndtime(TimeUtil.formatterTime(endtime));
        order.setRoomid(roomid);
        order.setUserid(userid);
        order.setState(0);
        double money = TimeUtil.getBetweenDay(starttime,endtime)*room.getMoney();
        order.setMoney(money);

        Config config = configMapper.selectByPrimaryKey(1);
        config.setTotalroom(config.getTotalroom()+1);
        config.setTotalmoney(config.getTotalmoney()+money);
        configMapper.updateByPrimaryKeySelective(config);

        int insert = orderMapper.insertSelective(order);
        if(insert>0){
            Room room1 = new Room();
            room1.setRoomid(roomid);
            room1.setState(2);
            int i = roomMapper.updateByPrimaryKeySelective(room1);
            if(i>0){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public boolean delOrder(int orderid) {
        Order order = orderMapper.selectByPrimaryKey(orderid);
        Integer roomid = order.getRoomid();
        Room room = new Room();
        room.setRoomid(roomid);
        room.setState(1);
        int i = roomMapper.updateByPrimaryKeySelective(room);
        if(i>0){
            int i1 = orderMapper.deleteByPrimaryKey(orderid);
            if(i1>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateOrderState(int orderid, int state) {
        Order order = orderMapper.selectByPrimaryKey(orderid);
        if(order==null){
            return false;
        }
        Integer roomid = order.getRoomid();
        Room room = new Room();
        room.setRoomid(roomid);
        int i = 1;
        if(state==2){
            room.setState(3);
            i = roomMapper.updateByPrimaryKeySelective(room);
        }
        if(state==3){
            room.setState(1);
            i = roomMapper.updateByPrimaryKeySelective(room);
        }
        order.setState(state);
        if(i>0){
            int i1 = orderMapper.updateByPrimaryKeySelective(order);
            if(i1>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Order> getAllOrder(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return orderMapper.getAllUser();
    }
}
