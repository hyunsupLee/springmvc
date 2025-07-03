package hello.springmvc.basic;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass()); lombok의 @Slf4j 애노테이션을 사용하면 <-- 이코드는 필요 없다.

    @GetMapping("/log-test")
    public String logTet() {
        String name = "spring";

        // logging.level.hello.springmvc=info 기본셋팅은 INFO 이다.
        // log level trace > debug > info > warn > error
        System.out.println("name = " + name); // 상황에 따른 로그 제한 없이 다 찍히는 게 문제다.
        log.trace("trace log={}",name);
        log.debug("debug log={}",name);
        log.info("info log={}",name);
        log.warn("warn log={}",name);
        log.error("error log={}", name);
        return "ok";
    }
}
