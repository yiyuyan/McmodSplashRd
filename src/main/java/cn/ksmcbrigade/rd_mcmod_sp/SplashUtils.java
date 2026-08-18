package cn.ksmcbrigade.rd_mcmod_sp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//参考了 https://github.com/187J3X1-114514/MCmod-flashing-slogan/blob/1.20.1/common/src/main/java/io/homo/mcmodsplash/mcmodsplash/utils/SplashManager.java
public class SplashUtils {


    public String getOnlineSplash() {
        try {
            URL url = new URI("https://mcmod.cn/").toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(1000);
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                String htmlContent = content.toString();
                Pattern pattern = Pattern.compile("<div class=\"shadow\">(.+?)</div>");
                Matcher matcher = pattern.matcher(htmlContent);
                if (matcher.find()) {
                    McmodsplashppMod.LOGGER.info("成功从 MC百科 主页获取标语：{}", matcher.group(1));
                    return matcher.group(1);
                } else {
                    McmodsplashppMod.LOGGER.warn("无法从 MC百科 主页获取标语，请联系作者");
                    return null;
                }
            } else {
                McmodsplashppMod.LOGGER.warn("无法从 MC百科 主页获取标语，状态码 {}", responseCode);
                return null;
            }
        } catch (Exception e) {
            McmodsplashppMod.LOGGER.warn("无法从 MC百科 主页获取标语，错误：", e);
        }
        return null;
    }

}
