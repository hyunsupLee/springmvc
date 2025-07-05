package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequetBodyStringController {

    //http://localhost:8080/request-body-string-v1
    @PostMapping("/request-body-string-v1")
    public void requestBodyString1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }
    //http://localhost:8080/request-body-string-v2
    @PostMapping("/request-body-string-v2")
    public void requestBodyString2(InputStream inputStream , Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    // http://localhost:8080/request-body-string-v3
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyString3(HttpEntity<HelloData> helloData) throws IOException {
        HelloData helloData1 = helloData.getBody();
        HttpHeaders headers = helloData.getHeaders();
        log.info("messageBody={} , headers={}", helloData1 , headers);
        // messageBody=HelloData(username=james3, age=33) ,
        // headers=[content-length:"34", accept-encoding:"gzip, deflate, br", accept:"*/*",
        // user-agent:"Thunder Client (https://www.thunderclient.com)", host:"localhost:8080",
        // connection:"close", Content-Type:"application/json;charset=UTF-8"]
        return new HttpEntity<String>("ok");
    }

    // http://localhost:8080/request-body-string-v4
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public HelloData requestBodyString4(@RequestBody HelloData helloData ) throws IOException {
        log.info("messageBody={}", helloData );
        return helloData;
    }

}
