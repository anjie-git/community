package atjie.community.community.controller;

import atjie.community.community.DTO.AccesTokenDTO;
import atjie.community.community.DTO.GithubUser;
import atjie.community.community.provider.GithubPrvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccesTokenDTO accesTokenDTO=  new  AccesTokenDTO();
        accesTokenDTO.setClient_id("716c162888e90c820d69");
        accesTokenDTO.setClient_secret("98c03ec06ec1e96112ebbba7e35c4fecfe2b7ea9");
        accesTokenDTO.setCode(code);
        accesTokenDTO.setRedirect_uri("http://127.0.1.1:8080/callback");
        accesTokenDTO.setState(state);
        String accessToken = githubPrvider.gitAccess_token(accesTokenDTO);
        GithubUser user = githubPrvider.getUser(accessToken);
        System.out.println("打印出名字："+user.getName());
        return "index";
    }

}
