package hello.springmvc.basic.reponse;


import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {

    @GetMapping("response-body-string-v1")
    public void responseBodyV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        ResponseEntity<String> re = new ResponseEntity<>("ok", HttpStatus.OK);
        return re;
    }

    @ResponseBody
    @GetMapping("response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    @GetMapping("response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        // HttpStatus 를 동적으로 생성하려면 이걸 쓴다.
        HelloData hd = new HelloData();
        hd.setUsername("james");
        hd.setAge(20);
        return new ResponseEntity<>(hd, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData hd = new HelloData();
        hd.setUsername("james");
        hd.setAge(20);
        return hd;
    }

}
