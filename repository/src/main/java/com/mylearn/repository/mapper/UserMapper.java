package com.mylearn.repository.mapper;

import com.mylearn.repository.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/23
 * Time: ����11:21
 * CopyRight: taobao
 * Descrption:user��Mapper
 */

public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUser(@Param("id") Integer id);
}
