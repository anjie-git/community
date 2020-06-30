package atjie.community.community.model;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *数据库模型
 */
@Data
@Table(name = "user")//映射数据库表,name为数据库的表名。
public class User {
    @Id//注解定义主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//定义主键自增
    private Integer id;
    private String name;//名称
    private String account_id;//账户id
    private String token;//一个客户端请求的令牌
    private Long gmt_create;//创建时间
    private Long gmt_modified;//修改时间


}
