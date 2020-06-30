package atjie.community.community.mapper.impl;
import atjie.community.community.mapper.UserMapper;
import atjie.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserService{

    @Autowired
    private UserMapper userMapper;
    //添加1
    public int insertsService(User user){
        int inserts = userMapper.inserts(user);
        if(inserts>0){
            return 1;
        }
            return 0;
    }


    //添加2
    public void add(User user){
        userMapper.insert(user);

    }

}
