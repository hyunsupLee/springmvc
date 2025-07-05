package hello.springmvc.basic.request;


import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        // Jackson(ObjectMapper)은 내부적으로 리플렉션을 이용해서 객체를 생성할 때 기본 생성자가 필요합니다.
        // HelloData 객체에 기본 생성자가 없으면 500 에러남 @Data 만 설정하던지 - 기본생성자 자동 생성
        // @AllArgsConstructor 설정했다면 <- 저것으로 인해 기본 생성자가 생성되지 않으므로
        // @NoArgsConstructor 로 추가해야 한다.

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username ={} , age= {}", helloData.getUsername(), helloData.getAge());
        response.getWriter().write("ok");
    }
}
