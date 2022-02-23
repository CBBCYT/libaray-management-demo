package com.mapper;


import com.pojo.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {

    @Select("select * from tb_book")
    @ResultMap("bookResultMap")
    List<Book> selectAll();

    @Select("select * from tb_book where id = #{id}")
    @ResultMap("bookResultMap")
    Book selectByIdBook(Integer id);

    @Insert("insert into tb_book values(null,#{bookName},#{author},#{publisher},#{category},#{label},#{totalNumber},#{remainNumber})")
    void add(Book book);

    @Update("update tb_book set book_name=#{bookName},author=#{author},publisher=#{publisher},category=#{category},label=#{label},total_number=#{totalNumber},remain_number=#{remainNumber} where id=#{id}")
    void update(Book book);

    @Delete("delete from tb_book where id = #{id}")
    void delete(Integer id);

    /*
    * 批量删除
    * */
    void deleteByIds(@Param("ids") int[] ids);

    /*
    * 分页查询
    * */
    @Select("select * from tb_book limit #{begin}, #{size}")
    @ResultMap("bookResultMap")
    List<Book> selectByPage(@Param("begin") int begin, @Param("size") int size);

    /*
    * 查询总量
    * */
    @Select("select count(*) from tb_book")
    int selectTotalCount();

    /*
     * 分页条件查询
     * */

    List<Book> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size,@Param("book")Book book);

    /*
     * 根据条件查询总量
     * */
    int selectTotalCountByCondition(Book book);
}
