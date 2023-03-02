package fullstack.stian.fullstack.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorLogger {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorLogger.class);

    public void logCalculation(int a, int b, String operator, int result) {
        logger.info("Calculation reqeust received: {} {} {} = {}", a, operator, b, result);
    }
}
