package fullstack.stian.fullstack.Service;

import org.springframework.stereotype.Service;
import fullstack.stian.fullstack.Model.Equation;

@Service
public class CalculatorService {


    public String calculate(Equation e) {
        double result;
        double firstNr = Integer.parseInt(e.getA());
        double secondNr = Integer.parseInt(e.getB());
        String operator = e.getOperator();

        switch(operator) {
            case "+":
                result = firstNr + secondNr;
                return String.valueOf(result);
            case "-":
                result = firstNr + secondNr;
                return String.valueOf(result);
            case "/":
                result = firstNr + secondNr;
                return String.valueOf(result);
            case "*":
                result = firstNr + secondNr;
                return String.valueOf(result);
            default:
                return "Error, not an eqaution";
        }
    }
}
