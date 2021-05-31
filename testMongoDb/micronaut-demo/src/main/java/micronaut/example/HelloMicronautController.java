package micronaut.example;

import io.micronaut.http.annotation.*;

@Controller("/hello")
public class HelloMicronautController {

    @Get(uri="/message", produces="text/plain")
    public String index() {
        return "Hello from Micronaut Controller";
    }

    @Get(uri="/employee", produces="text/plain")
    public Employee getEmployee() {
        return new Employee(101, "Supriya");
    }
}