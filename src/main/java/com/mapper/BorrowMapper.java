package com.mapper;

import com.pojo.Borrow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import javax.servlet.annotation.WebServlet;
import java.util.List;

public interface BorrowMapper {

    @Insert("insert into tb_borrow values(null,#{userId},#{bookId},#{borrowTime},#{returnTime})")
    void borrow(Borrow borrow);

    @Select("select * from tb_borrow where user_id=#{userid}")
    @ResultMap("BorrowMapper")
    List<Borrow> selectByIds(Integer userId);

    @Delete("delete from tb_borrow where id=#{id}")
    void deleteById(Integer id);

    @Select("select * from tb_borrow where id=#{id}")
    @ResultMap("BorrowMapper")
    Borrow selectById(Integer id);
}
