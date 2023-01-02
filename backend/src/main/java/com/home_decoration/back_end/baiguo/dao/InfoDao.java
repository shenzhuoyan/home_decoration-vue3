package com.home_decoration.back_end.baiguo.dao;

import com.home_decoration.back_end.baiguo.domain.Info;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoDao {
    @Insert("insert into info(type,content) value (#{type},#{content})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Info info);

    @Select("select content from info where type=#{type} order by id desc limit 0,1")
    String select(int type);

    @Select("select * from info where type=#{type} order by id desc")
    List<Info> selectAllByType(int type);

    @Delete("delete from info where id=#{id} and type=5")
    int delete(int id);

}
