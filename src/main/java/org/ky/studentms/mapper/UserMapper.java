package org.ky.studentms.mapper;

import org.apache.ibatis.annotations.*;
import org.ky.studentms.entity.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO user(username, password, role) VALUES(#{username}, #{password}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Delete("DELETE FROM user WHERE id = #{userId}")
    void deleteUser(Integer userId);
}