package hello.springmvc.basic.requestmapping;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/hello-basic", "/hello-ok"}, method = RequestMethod.GET)
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @GetMapping("/hello-get")
    public String helloGet() {
        log.info("helloGet");
        return "ok";
    }


    @GetMapping("/hello-path/{id}")
    public String helloPath(@PathVariable(name = "id") String id) {
        log.info("userId = {} ", id);
        return id;
    }

    @GetMapping("/hello-path2/users/{userId}/orders/{orderId}")
    public String helloPath(@PathVariable String userId , @PathVariable String orderId) {
        log.info("userId = {} , orderId = {} ", userId, orderId);
        return userId + " , " + orderId;
    }

    // http://localhost:8080/hello-param?mode=debug  , mode=debug 가 있어야만 정상실행
    @GetMapping(value = "/hello-param" , params = "mode=debug")
    public String helloParam() {
        log.info("helloParam ");
        return "helloParam";
    }

    // header key SERVICE , value SNAPS
    @GetMapping(value = "/hello-header" , headers = "SERVICE=SNAPS")
    public String helloHeaderSnaps() {
        log.info("helloHeader - snaps ");
        return "helloHeader - snaps";
    }

    @GetMapping(value = "/hello-header" , headers = "SERVICE=OHPRINT")
    public String helloHeaderOhPrint() {
        log.info("helloHeader - ohPrint");
        return "helloHeader - ohPrint";
    }

    // media type 요청
    // header Content-type application/json 인 경우 호출 - json으로 데이터 요청을 보낸다.
    // @PostMapping(value = "/hello-consume" , consumes = "application/json")
    @PostMapping(value = "/hello-consume" , consumes = MediaType.APPLICATION_JSON_VALUE )
    public String helloConsumes() {
        log.info("hello-consume");
        return "hello-consume - application/json";
    }

    // header Accept text/html 인 경우 호출 - 요청한 나에게 HTML 로 달라는 뜻
    //@PostMapping(value = "/hello-produces" , produces = "text/html")
    @PostMapping(value = "/hello-produces" , produces = MediaType.TEXT_HTML_VALUE)
    public String helloProduces() {
        log.info("hello-Produces");
        return "hello-Produces - text/html";
    }



}
