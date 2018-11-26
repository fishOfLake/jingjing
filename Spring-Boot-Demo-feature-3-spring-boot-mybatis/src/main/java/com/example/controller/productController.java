package com.example.controller;

import com.example.entity.ProductGoods;
import com.example.mapper.ProductGoodsMapper;
import com.example.util.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class productController {
    @Autowired
    private ProductGoodsMapper productGoodsMapper;
    @RequestMapping("/products")
    public Result list(String q,@RequestParam(defaultValue = "1")String categoryId,@RequestParam(defaultValue = "1")Integer pageNo, @RequestParam(defaultValue = "16")Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        Example example=new Example(ProductGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("categoryId","%,"+categoryId+",%");
        if(!StringUtils.isNullOrEmpty(q)){
            criteria.andLike("goodsName","%"+q+"%");
        }
        Page<ProductGoods> page=(Page<ProductGoods>)productGoodsMapper.selectByExample(example);
        List<ProductGoods> list=page.getResult();
        List<ProductGoods> listPic=new ArrayList<ProductGoods>();
        for (int i=0;i<list.size();i++){
            ProductGoods productGoods=list.get(i);
            String pic=productGoods.getPicturePath();
            //System.out.println(pic);
            if(pic!=null&&pic.contains(",")){
                pic=pic.substring(0,pic.indexOf(","));
                productGoods.setPicturePath(pic);
            }
            listPic.add(productGoods);
        }


        Map<String,Object> resMap = new HashMap<String,Object>();
        resMap.put("pageNo",pageNo);
        resMap.put("pageSize",pageSize);
        resMap.put("totalPage",page.getPages());
        resMap.put("total",page.getTotal());
        resMap.put("list",listPic);
        return Result.OK("成功获取到商品列表",resMap);
    }


    @RequestMapping("/listRecommend")
    public Result listRecommend(@RequestParam(defaultValue = "1")String categoryId,@RequestParam(defaultValue = "1")Integer pageNo, @RequestParam(defaultValue = "5")Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        Example example=new Example(ProductGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("categoryId","%,"+categoryId+",%");
        criteria.andEqualTo("isRecommend",1);
        Page<ProductGoods> page=(Page<ProductGoods>)productGoodsMapper.selectByExample(example);
        List<ProductGoods> list=page.getResult();
        List<ProductGoods> listPic=new ArrayList<ProductGoods>();
        for (int i=0;i<list.size();i++){
            ProductGoods productGoods=list.get(i);
            String pic=productGoods.getPicturePath();
            //System.out.println(pic);
            if(pic!=null&&pic.contains(",")){
                pic=pic.substring(0,pic.indexOf(","));
                productGoods.setPicturePath(pic);
            }
            listPic.add(productGoods);
        }


//        Map<String,Object> resMap = new HashMap<String,Object>();
//        resMap.put("pageNo",pageNo);
//        resMap.put("pageSize",pageSize);
//        resMap.put("totalPage",page.getPages());
//        resMap.put("total",page.getTotal());
//        resMap.put("data",listPic);
        return Result.OK("成功获取到商品列表",listPic);
    }


}
