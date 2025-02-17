package cn.bdqn.service;

import cn.bdqn.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("JiSuanService")
public class JiSuanServiceImple implements JiSuanService{
    @Autowired
    private userMapper userMapper;

    @Override
    public String getName(String name) {
        return userMapper.getName(name);
    }
}
