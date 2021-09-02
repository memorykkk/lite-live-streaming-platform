package com.xinf.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinf.dto.UserInfo;
import com.xinf.entity.User;
import com.xinf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2021-08-31 19:25:18
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param user 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<User> page, User user) {
        return success(this.userService.page(page, new QueryWrapper<>(user)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.userService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody User user) {
        return success(this.userService.save(user));
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody User user) {
        return success(this.userService.updateById(user));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.userService.removeByIds(idList));
    }

    @PostMapping("/register")
    public R register(@RequestBody User user) {
        if (userService.registerUser(user)) {
            return success(null);
        } else {
            return failed("注册失败");
        }
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public R login(String auth, String passwd) {

        if (StringUtils.isBlank(auth)) {
            return failed("用户名为空！");
        }

        if (StringUtils.isBlank(passwd)) {
            return failed("密码为空！");
        }

        log.debug("auth : {}, passwd : {}", auth, passwd);

        UserInfo loginUser = userService.login(auth, passwd);

        // 登录成功返回用户信息
        return success(loginUser);
    }



    /**
     * description: 登出
     */
    @GetMapping("/logout")
    public R logOut() {
        userService.logout();
        return success("登出成功！");
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     */
    @RequestMapping("/un_auth")
    public ResponseEntity<?> unAuth() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * 未授权，无权限，此处返回未授权状态信息由前端控制跳转页面
     */
    @RequestMapping("/unauthorized")
    public ResponseEntity<?> unauthorized() {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}