package com.home_decoration.back_end.baiguo.dao;

import com.home_decoration.back_end.baiguo.domain.Designer;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignerDao {
    //@Insert("insert into designer(title,content,picture)value(#{title},#{content},#{picture})")
    //int insert(Designer designer);


    @Update("update designer set title=#{title},content=#{content},picture=#{picture} where id=#{id}")
    int update(Designer designer);
    @Select("select * from designer where id=#{id}")
    Designer selectById(int id);

    @Select("select id,title from designer where id=#{id}")
    Designer selectSimple(int id);
    @Select("select * from designer")
    List<Designer> selectAll();
}
