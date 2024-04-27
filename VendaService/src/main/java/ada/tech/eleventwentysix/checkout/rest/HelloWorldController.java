package ada.tech.eleventwentysix.checkout.rest;

import ada.tech.eleventwentysix.checkout.payloads.HelloResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/hello")
@RequiredArgsConstructor
public class HelloWorldController {

    @Operation(summary = "Say hello sample request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "greeting accepted")

    })
    @GetMapping(value = "/say/{name}")
    public HelloResponse sayHello(@PathVariable("name") String name){

        return new HelloResponse("Olá ", name);
    }
}
