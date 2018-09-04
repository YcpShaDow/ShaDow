package com.ycp.controller;

import com.ycp.pojo.ItemsCustom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ycp on 2018/9/1.
 */
@Controller
public class JsonTest {
    //请求json(商品信息)，输出json商品信息
    @RequestMapping("/requestJson")
    public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom){
        ItemsCustom itemsCustom1=new ItemsCustom();
        System.out.println(itemsCustom.getName());
        itemsCustom.setName(itemsCustom.getName()+"ycp----");
        return  itemsCustom;
    }

    @RequestMapping("/responseJson")
    @ResponseBody
    public  ItemsCustom responseJson(ItemsCustom itemsCustom){
        System.out.println(itemsCustom.getName());
        itemsCustom.setName(itemsCustom.getName()+"ycp1----");
        return  itemsCustom;
    }
}
