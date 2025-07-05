package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={} , age ={}", username, age);
        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String username ,
                                 @RequestParam("age") String age){
        log.info("username={} , age ={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username ,
                                 @RequestParam String age){
        // required = true 가 기본값.
        log.info("username={} , age ={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username ,
                                 String age){
        // RequestParam 없는 경우 required = false 로 설정 된다.
        log.info("username={} , age ={}", username, age);
        return "ok";
    }


    //http://localhost:8080/request-param-required?username=james
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username ,
                                       @RequestParam(required = false) Integer age){
        // required false 이면 기본형인 경우 에러 난다.( null )  Integer null
        log.info("username={} , age ={}", username, age); // username=james , age =null
        return "ok";
    }

    // http://localhost:8080/request-param-default
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true , defaultValue = "guest") String username ,
                                      @RequestParam(required = false , defaultValue = "-1") int age){
        // defaultValue 값이 guest 설정되므로 @RequestParam(required = true 의미 없음)
        log.info("username={} , age ={}", username, age); // username=guest , age =-1
        return "ok";
    }

    // http://localhost:8080/request-param-map?username=james&age=30
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object> paramMap){
        // defaultValue 값이 guest 설정되므로 @RequestParam(required = true 의미 없음)
        log.info("username={} , age ={}", paramMap.get("username"), paramMap.get("age"));
        // username=james , age =30
        return "ok";
    }


    // http://localhost:8080/model-attribute-v1?username=james&age=30
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String requestParamModelAttr1(@ModelAttribute HelloData helloData){
        log.info("username={} , age ={}", helloData.getUsername() , helloData.getAge());
        // username=james , age =30
        log.info("helloData={}", helloData);
        // helloData=HelloData(username=james, age=30)
        return "ok";
    }

    // http://localhost:8080/model-attribute-v2?username=james2&age=40
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String requestParamModelAttr2(HelloData helloData){
        // @ModelAttribute 생략가능
        log.info("username={} , age ={}", helloData.getUsername() , helloData.getAge());
        // username=james2 , age =40
        log.info("helloData={}", helloData);
        // helloData=HelloData(username=james2, age=40)
        return "ok";
    }

}
