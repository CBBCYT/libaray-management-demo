package com.service.impl;

import com.mapper.BookMapper;
import com.pojo.Book;
import com.pojo.PageBean;
import com.service.BookService;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BookServiceImpl implements BookService {
    //获取工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Book> selectAll() {
        //获取Sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取Bookmapper对象
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        //调用方法
        List<Book> books = mapper.selectAll();
        //释放资源
        sqlSession.close();
        return books;
    }

    @Override
    public Book selectById(Integer id) {
        //获取Sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取Bookmapper对象
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        //调用方法
        Book book = mapper.selectByIdBook(id);
        //释放资源
        sqlSession.close();
        return book;
    }

    @Override
    public void add(Book book) {
        //获取Sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取Bookmapper对象
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);

        mapper.add(book);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Override
    public void update(Book book) {
        //获取Sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取Bookmapper对象
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);

        mapper.update(book);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Override
    public void delete(Integer id) {
        //获取Sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取Bookmapper对象
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);

        mapper.delete(id);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //获取Sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取Bookmapper对象
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);

        mapper.deleteByIds(ids);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //分页查询
    @Override
    public PageBean<Book> selectByPage(int current, int size) {
        //获取Sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取Bookmapper对象
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);

        //计算开始索引
        int begin =  (current - 1) * size;
        List<Book> rows = mapper.selectByPage(begin, size);
        
        //查询总记录数
        int totalCount = mapper.selectTotalCount();

        //封装PageBean对象
        PageBean<Book> pageBean = new PageBean<Book>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //释放资源
        sqlSession.close();

        return pageBean;
    }

    //分页条件查询
    @Override
    public PageBean<Book> selectByPageAndCondition(int current, int size, Book book) {
        //获取Sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取Bookmapper对象
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);

        //计算开始索引
        int begin =  (current - 1) * size;

        //处理book对象，模糊处理
        String bookName = book.getBookName();
        if(bookName != null && bookName.length()>0)
        {
            book.setBookName("%"+bookName+"%");
        }
        String category = book.getCategory();
        if(category != null && category.length()>0)
        {
            book.setCategory("%"+category+"%");
        }
        /*String label= book.getLabel();
        if(label != null && label.length()>0)
        {
            book.setLabel("%"+label+"%");
        }*/

        //查询当页数据
        List<Book> rows = mapper.selectByPageAndCondition(begin, size, book);

        //查询总记录数
        int totalCount = mapper.selectTotalCount();

        //封装PageBean对象
        PageBean<Book> pageBean = new PageBean<Book>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //释放资源
        sqlSession.close();

        return pageBean;
    }
}
