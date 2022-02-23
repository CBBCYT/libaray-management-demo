package com.service;

import com.mapper.BookMapper;
import com.mapper.BorrowMapper;
import com.mapper.UserMapper;
import com.pojo.Book;
import com.pojo.Borrow;

import com.pojo.PageBean;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

public class BorrowService {
    //创建sqlSessionFactory
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public void borrow(Borrow borrow)
    {
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BorrowMapper borrowMapper = sqlSession.getMapper(BorrowMapper.class);

        //调用方法
        borrowMapper.borrow(borrow);

        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Test
    public void test()
    {
        System.out.println(selectByIds(5));
    }


    public List<Borrow> selectByIds(Integer userId)
    {
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BorrowMapper borrowMapper = sqlSession.getMapper(BorrowMapper.class);

        //调用方法
        List<Borrow> borrows = borrowMapper.selectByIds(userId);

        //释放资源
        sqlSession.close();

        return borrows;
    }

    public Borrow selectById(int id)
    {
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BorrowMapper borrowMapper = sqlSession.getMapper(BorrowMapper.class);

        //调用方法
        Borrow borrow = borrowMapper.selectById(id);

        //释放资源
        sqlSession.close();

        return borrow;
    }

    public void deleteById(int id)
    {
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BorrowMapper borrowMapper = sqlSession.getMapper(BorrowMapper.class);

        //调用方法
        borrowMapper.deleteById(id);

        //提交事务
        sqlSession.commit();

        //释放资源
        sqlSession.close();
    }
}
