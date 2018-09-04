package com.ycp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ycp.pojo.Items;
import com.ycp.pojo.ItemsCustom;
import com.ycp.pojo.ItemsQueryVo;
import com.ycp.service.ItemsService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ycp on 2018/8/26.
 */
@Controller
//为了方便分类管理，最终访问url是根路径+子路径
@RequestMapping("items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    //商品的查询
    /*@RequestMapping("/queryItems")
    public ModelAndView queryItems(HttpServletRequest request,ItemsQueryVo itemsQueryVo) throws Exception {
        System.out.println(request.getParameter("itemsCustom.name"));
        //调用service查找数据库，查询商品列表

        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

        //返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //相当于request的setAttribute
        modelAndView.addObject("itemsList", itemsList);
        //指定视图
        modelAndView.setViewName("item");
        return modelAndView;
    }*/
@RequestMapping("/queryItems")
    public String queryItems(Model model,@RequestParam(value = "pageNum" ,defaultValue="1") Integer pageNum,ItemsQueryVo itemsQueryVo,@RequestParam(value = "itemsCustom.name",defaultValue = "") String name,@RequestParam(value = "pageSize" ,defaultValue="3") Integer pageSize) throws Exception{
        //定义页码数
        ItemsCustom itemsCustom=new ItemsCustom();
        PageHelper.startPage(pageNum, pageSize);
        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
      //  System.out.println(itemsQueryVo+"1111");
        //将查询的结果包装在PageInfo
        PageInfo<ItemsCustom> pageInfo=new PageInfo<ItemsCustom>(itemsList);
        System.out.println(pageInfo.getNavigatePages()+"21313");
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("name",name);
        return "item";
    }

    @RequestMapping(value = "/editItems",method = {RequestMethod.POST,RequestMethod.GET})
    public  String editItems( Model model,@RequestParam(value = "id") Integer itemid) throws Exception {
        //调用service根据id查询
        ItemsCustom itemsCustom = itemsService.findItemsById(itemid);
     /*   System.out.println(itemsCustom.getPic()+"123");*/
        model.addAttribute("itemsCustom",itemsCustom);
        return "editItems";
    }
    //商品信息的修改提交
    @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit(Model model, HttpServletRequest request, Integer id, @ModelAttribute(value = "itemsCustom") @Validated ItemsCustom itemsCustom,
                                  BindingResult bindingResult, MultipartFile items_pic)throws Exception{
    //获取错误信息
        if(bindingResult.hasErrors()){
            //输出错误信息
            List<ObjectError> allErrors= bindingResult.getAllErrors();
            for(ObjectError objectError:allErrors){
                System.out.println(objectError.getDefaultMessage());
            }
            model.addAttribute("allErrors",allErrors);
            model.addAttribute("itemsCustom",itemsCustom);
            return "editItems";
        }

    //上传图片
        String originalFilename=items_pic.getOriginalFilename();
        if(items_pic!=null&&originalFilename!=null&&originalFilename.length()>0){
            System.out.println("ycp++++");
            //存储图片的物理路径
            String pic_path="E:\\ideaa\\pic\\";
            //新的图片名称
            String newFileName= UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
            //新的图片
            File newFile=new File(pic_path+newFileName);
            //将内存的数据写入磁盘
            items_pic.transferTo(newFile);
            System.out.println(newFileName+"12323");
            //将pic写入itemsCustoms
            itemsCustom.setPic(newFileName);
        }
        itemsService.updateItems(id,itemsCustom);
        return "forward:queryItems";

    }
    @RequestMapping("/deleteItems")
public  String deleteItems(int [] item_id) throws  Exception{
    if(item_id==null){
        return "forward:queryItems";
    }
        for (int i = 0; i <item_id.length ; i++) {
            System.out.println(item_id[i]+"ycp");
        }
        itemsService.deleteItems(item_id);
        return "forward:queryItems";
}
}
