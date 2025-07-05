package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {


    @GetMapping
    public String user() {
        return "get users";
    }

    @PostMapping
    public String addUser() {
        return "post addUser";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId) {
        return "get user = " + userId;
    }

    @PatchMapping ("/{userId}")
    public String update(@PathVariable String userId) {
        return "pathch user = " + userId;
    }

    @DeleteMapping("/{userId}")
    public String delete(@PathVariable String userId) {
        return "delete user = " + userId;
    }

}
