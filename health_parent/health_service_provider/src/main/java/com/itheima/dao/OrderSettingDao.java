package com.itheima.dao;

import com.itheima.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {

    public void add(OrderSetting orderSetting);
    //更新可预约人数
    public void editNumberByOrderDate(OrderSetting orderSetting);
    //更新已预约人数
    public long findCountByOrderDate(Date orderDate);
    //根据日期范围查询预约设置信息
    List<OrderSetting> getOrderSettingByMonth(Map map);
    //根据预约日期查询预约设置信息
    OrderSetting findByOrderDate(Date orderDate);
    void editReservationsByOrderDate(OrderSetting orderSetting);
}
