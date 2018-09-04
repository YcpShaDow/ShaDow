package com.ycp.service;

import com.ycp.pojo.ItemsCustom;
import com.ycp.pojo.ItemsQueryVo;

import java.util.List;

/**
 * Created by ycp on 2018/8/26.
 */

public interface ItemsService {
    //商品查询列表
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
    //根据id查询商品信息
    public ItemsCustom findItemsById(Integer id) throws  Exception;
    //修改商品信息
    public void updateItems(Integer id,ItemsCustom itemsCustom) throws  Exception;
    //批量删除商品信息
    public Integer deleteItems(int[] id) throws  Exception;
}
