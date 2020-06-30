package atjie.community.community.DTO;

import lombok.Data;

/**
 *网络数据传输对象
 */
@Data
public class AccesTokenDTO {
    private  String client_id;
    private  String client_secret;
    private  String code;
    private  String redirect_uri;
    private  String state;
}
