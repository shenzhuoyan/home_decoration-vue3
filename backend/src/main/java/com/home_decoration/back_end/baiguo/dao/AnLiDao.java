package com.home_decoration.back_end.baiguo.dao;


import com.home_decoration.back_end.baiguo.domain.AnLi;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnLiDao {
    @Insert("insert into anli (title,content,designer_id,introduction,picture)value(#{title},#{content},#{designer.id},#{introduction},#{picture})")
    int insert(AnLi anLi);

    @Delete("delete from anli where id=#{id}")
    int delete(int id);

    @Select("select id,picture from anli")
    List<AnLi> selectAllSimple();

    @Select("select * from anli")
    @Results({
            @Result(column = "id",id=true,property = "id"),
            @Result(column = "designer_id", property = "designer",
                    one = @One(select = "com.home_decoration.back_end.baiguo.dao.DesignerDao.selectSimple"))
    })
    List<AnLi> selectAll();

    @Select("select id,picture from anli limit 0,6")
    List<AnLi> select6Simple();


    @Select("select * from anli where id = #{id}")
    @Results({
            @Result(column = "id",id=true,property = "id"),
            @Result(column = "designer_id", property = "designer",
                    one = @One(select = "com.home_decoration.back_end.baiguo.dao.DesignerDao.selectSimple"))
    })
    AnLi selectById(int id);

}
