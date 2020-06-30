package atjie.community.community.mapper;
import atjie.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
/**
 *
 */
@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {

    //添加1
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified})")
    int inserts(User user);



}
