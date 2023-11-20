package com.totoro.mall.manager.service;

import com.totoro.mall.model.vo.system.ValidateCodeVo;

/**
 * @author Totoro
 * @create 18 21:36
 * @Description: 验证码
 */
public interface ValidateCodeService {
    public abstract ValidateCodeVo generateValidateCode();
}
