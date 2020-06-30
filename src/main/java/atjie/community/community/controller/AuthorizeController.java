package atjie.community.community.controller;

import atjie.community.community.DTO.AccesTokenDTO;
import atjie.community.community.DTO.GithubUser;
import atjie.community.community.mapper.UserMapper;
import atjie.community.community.mapper.impl.UserService;
import atjie.community.community.model.User;
import atjie.community.community.provider.GithubPrvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Controller
public class AuthorizeController {

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;


    @Autowired
    private GithubPrvider githubPrvider;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        //request  spring会自动把上下文的地方写到里面
        AccesTokenDTO accesTokenDTO = new AccesTokenDTO();
        accesTokenDTO.setClient_id(clientId);
        accesTokenDTO.setClient_secret(clientSecret);
        accesTokenDTO.setCode(code);
        accesTokenDTO.setRedirect_uri(redirectUrl);
        accesTokenDTO.setState(state);
        String accessToken = githubPrvider.gitAccess_token(accesTokenDTO);
        GithubUser githubUser = githubPrvider.getUser(accessToken);//获取到了github账号信息
        if (githubUser != null) {
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            //userMapper.inserts(user);//普通的方法
            int i = userService.insertsService(user);//使用tkmybatis方法
            if(i>0){
                //登录成功 写cookie 和session
                request.getSession().setAttribute("user", githubUser);//把user对象存到session中
            }else {
                request.getSession().setAttribute("user", null);//传入空对象
            }
            return  "redirect:/";//重定向到index   如果不写就会把所有的地址显示出来
        }else{
            //登录失败
            return "redirect:/";
        }
    }

}
