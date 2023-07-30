package com.study.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.gulimall.common.utils.PageUtils;
import com.study.gulimall.common.utils.Query;

import com.study.gulimall.product.dao.CategoryDao;
import com.study.gulimall.product.entity.CategoryEntity;
import com.study.gulimall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 获取树状结构的分类数据
     * @return 树状结构的分类数据
     */
    @Override
    public List<CategoryEntity> listWithTree() {
        //1、查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);
        //2、组装成树形结构
        List<CategoryEntity> level1Menus = entities.stream()
                //2.1、获取所有一级分类
                .filter(categoryEntity -> categoryEntity.getParentCid().longValue() == 0)
                //2.2、递归设置子分类
                .map((menu) -> {
                    menu.setChildren(getChildrens(menu, entities));
                    return menu;
                })
                //2.3、排序
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                //2.4、收集
                .collect(Collectors.toList());
        return level1Menus;
    }

    /**
     * 删除所有指定的元素，应该在表中设置逻辑删除字段，删除该元素时只设置标志位为删除标志，实际数据并不删除
     * @param asList 需要删除的菜单的id列表
     */
    @Override
    public void removeMenuByIds(List<Long> asList) {
        // TODO 1 检查当前删除的菜单，是否被别的地方引用

        // 逻辑删除
        baseMapper.deleteBatchIds(asList);
    }

    /**
     * 递归查找所有菜单的子菜单
     * @param root 当前节点
     * @param all 表中所有数据
     * @return 当前节点的所有子节点
     */
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> children = all.stream()
                //1、获取所有子节点
                .filter(categoryEntity -> categoryEntity.getParentCid().longValue() == root.getCatId().longValue())
                //2、递归获取并设置所有子节点的子节点
                .map(categoryEntity -> {
                    categoryEntity.setChildren(getChildrens(categoryEntity, all));
                    return categoryEntity;
                })
                //3、排序
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                //4、收集结果
                .collect(Collectors.toList());
        return children;
    }

}