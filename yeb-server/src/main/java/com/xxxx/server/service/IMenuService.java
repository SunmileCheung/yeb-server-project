package com.xxxx.server.service;

import com.xxxx.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huyelin
 * @since 2023-06-30
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 通过用户id查询菜单列表
     * @return
     */
    List<Menu> getMenuByAdminId();

    /**
     * 通过角色获取菜单
     * @return
     */
    List<Menu> getMenusWithRoles();

    List<Menu> getAllMenus();
}
