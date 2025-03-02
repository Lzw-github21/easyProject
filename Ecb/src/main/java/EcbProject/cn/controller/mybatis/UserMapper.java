//package EcbProject.cn.controller.mybatis;
//
//import org.apache.ibatis.annotations.*;
//
//import java.util.List;
//
//@Mapper
//public interface UserMapper {
//
//    @Select("SELECT * FROM user WHERE id = #{id}")
//    User getUserById2(Integer id);
//
//    @Select("SELECT * FROM user")
//    List<User> getAllUsers2();
//
//    @Insert("INSERT INTO user(name, email) VALUES(#{name}, #{email})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    void insertUser2(User user);
//
//    @Update("UPDATE user SET name=#{name}, email=#{email} WHERE id=#{id}")
//    void updateUser2(User user);
//
//    @Delete("DELETE FROM user WHERE id=#{id}")
//    void deleteUser2(Integer id);
//
//    User getUserById(Integer id);
//    List<User> getAllUsers();
//    int insertUser(User user);
//    int updateUser(User user);
//    int deleteUser(Integer id);
//}