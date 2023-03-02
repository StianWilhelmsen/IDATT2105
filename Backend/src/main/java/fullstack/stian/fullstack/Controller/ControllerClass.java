package fullstack.stian.fullstack.Controller;

import fullstack.stian.fullstack.FullstackApplication;
import fullstack.stian.fullstack.Model.Equation;
import fullstack.stian.fullstack.Service.CalculatorService;
import fullstack.stian.fullstack.classes.CalculatorRequest;
import fullstack.stian.fullstack.classes.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
    public class ControllerClass {
    private static final Logger logger = LoggerFactory.getLogger(ControllerClass.class);


    @GetMapping("/Calculator")
    public String testConnection() {
        return "Success";
    }

    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/Calculator")
    public String calculate(@RequestBody Equation equation) {
        logger.info("Received calculation request");
        return calculatorService.calculate(equation);
    }
}
