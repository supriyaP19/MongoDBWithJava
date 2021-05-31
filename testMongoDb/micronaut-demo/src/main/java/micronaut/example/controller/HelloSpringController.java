package micronaut.example.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.micronaut.http.annotation.*;

@RestController
@Controller("/spring")
public class HelloSpringController
{
    @RequestMapping("/")
    @Get(uri="/message", produces="text/plain")
    public String hello()
    {
        return "Hello from Spring Controller";
    }
}