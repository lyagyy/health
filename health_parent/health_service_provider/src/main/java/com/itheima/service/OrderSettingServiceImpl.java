package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预约设置服务
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements  OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    //批量添加
    public void add(List<OrderSetting> list) {
        if(list!=null && list.size()>0){
            //检查此数据（日期）是否存在
            for (OrderSetting orderSetting : list) {
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if(count>0){
                    //存在，更新
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                }else {
                    //不存在，添加
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {//2019-3
        String dateBegin = date + "-1";//2019-3-1
        String dateEnd = date + "-31";//2019-3-31
        Map map = new HashMap();
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);

        List<Map> data = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map orderSettingMap = new HashMap();
            orderSettingMap.put("date",orderSetting.getOrderDate().getDate());//获得日期（几号）
            orderSettingMap.put("number",orderSetting.getNumber());//可预约人数
            orderSettingMap.put("reservations",orderSetting.getReservations());//已预约人数
            data.add(orderSettingMap);
        }

        return data;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
            //根据日期修改可预约人数
            long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
            if(count > 0){
                //当前日期已经进行了预约设置，需要进行修改操作
                orderSettingDao.editNumberByOrderDate(orderSetting);
            }else{
                //当前日期没有进行预约设置，进行添加操作
                orderSettingDao.add(orderSetting);
            }
    }



}
