package tech.yukino.community.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tech.yukino.community.model.Question;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title, description, gmt_create, gmt_modified, creator, tag) values (#{title}, #{description},#{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    public void create(Question question);

    //分页查询
    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    //question查询
    @Select("select count(1) from question")
    Integer count();
}
