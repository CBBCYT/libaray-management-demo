package com.service;

import com.mapper.BookMapper;
import com.pojo.Book;
import com.pojo.PageBean;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public interface BookService {

    //创建sqlSessionFactory
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /*
     * 查询所有
     * */
    public List<Book> selectAll();

    public Book selectById(Integer id);

    public void add(Book book);

    public void update(Book book);

    public void delete(Integer id);

    public void deleteByIds(int[] ids);

    //分页查询
    public PageBean<Book> selectByPage(int current, int size);

    //分页条件查询
    public PageBean<Book> selectByPageAndCondition(int current, int size, Book book);
}
