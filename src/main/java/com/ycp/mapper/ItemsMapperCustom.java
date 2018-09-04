package com.ycp.mapper;

import com.ycp.pojo.*;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemsMapperCustom {
    //商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
	//批量删除
     public Integer deleteItem(int[] itemsId) throws  Exception;
}