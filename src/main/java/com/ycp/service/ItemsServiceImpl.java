package com.ycp.service;

import com.ycp.mapper.ItemsMapper;
import com.ycp.mapper.ItemsMapperCustom;
import com.ycp.pojo.Items;
import com.ycp.pojo.ItemsCustom;
import com.ycp.pojo.ItemsQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ycp on 2018/8/26.
 */
@Service
public class ItemsServiceImpl implements ItemsService {
    @Autowired(required = false)
    private ItemsMapperCustom itemsMapperCustom;
    @Autowired(required = false)
    private ItemsMapper itemsMapper;
    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        //通过itmesMapperCustom来查询数据库
        return itemsMapperCustom.findItemsList(itemsQueryVo);
    }

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {
        Items items =itemsMapper.selectByPrimaryKey(id);
      /* 以后写是需要在扩展业务
      * -----
      * */
        ItemsCustom itemsCustom=new ItemsCustom();
        //将items的属性值拷贝到itemsCuston
        BeanUtils.copyProperties(items,itemsCustom);
        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
        //添加业务校验，通常在service接口对关键参数进行校验
        //如果为空，抛出异常
        //更新商品信息，使用ByPrimaryKeyWithBLOB根据id更新items的所有字段,但必须传id
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }

    @Override
    public Integer deleteItems(int[] id) throws Exception {
        return itemsMapperCustom.deleteItem(id);
    }
}
