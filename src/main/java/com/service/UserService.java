package com.service;

import com.mapper.UserMapper;
import com.pojo.User;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

public class UserService {

    //创建sqlSessionFactory
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    /**
     * 登陆方法
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password)
    {
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User user = userMapper.select(username, password);

        //释放资源
        sqlSession.close();
        return user;
    }

    public User selectByUsername(String username)
    {
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User user = userMapper.selectByUsername(username);

        //释放资源
        sqlSession.close();
        return user;
    }

    public boolean register(User user)
    {
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用方法

        User u = userMapper.selectByUsername(user.getUsername());

        if(u == null)
        {
            //用户名不存在
            userMapper.add(user);
            sqlSession.commit();
        }

        //释放资源
        sqlSession.close();
        return u==null;
    }

    public boolean register(String username)
    {
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User u = userMapper.selectByUsername(username);
        //释放资源
        sqlSession.close();
        return u==null;
    }

    public void updatePass(User user)
    {
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        userMapper.update(user);

        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Test
    public void test()
    {
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User u = userMapper.selectByUsername("张三");

        System.out.println(u);
        //释放资源
        sqlSession.close();
    }

}
