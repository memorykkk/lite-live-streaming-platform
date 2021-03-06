package com.xinf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinf.entity.UserFocus;

/**
 * (UserFocus)表服务接口
 *
 * @author makejava
 * @since 2021-08-31 19:25:18
 */
public interface UserFocusService extends IService<UserFocus> {

    void add(UserFocus userFocus);

    void remove(UserFocus userFocus);

    boolean isFocus(long focusId, long focusedId);
}
