package atjie.community.community.provider;

import atjie.community.community.DTO.AccesTokenDTO;

import atjie.community.community.DTO.GithubUser;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 获取github信息类
 */
@Component
public class GithubPrvider {

    public String gitAccess_token(AccesTokenDTO accesTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accesTokenDTO));
        //发送网络请求
        //.post传入参数
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String split = string.split("&")[0].split("=")[1];
            return split;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accesToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accesToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            //把string的json对象转化成java类对象
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
