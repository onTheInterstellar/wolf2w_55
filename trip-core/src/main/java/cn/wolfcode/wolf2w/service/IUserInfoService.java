package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IUserInfoService extends IService<UserInfo> {

    Object checkPhone(String phoneNumber);

}
