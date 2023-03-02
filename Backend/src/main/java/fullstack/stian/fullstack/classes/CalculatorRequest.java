package fullstack.stian.fullstack.classes;

public class CalculatorRequest {
    public int a;
    public int b;
    public String operator;

    public CalculatorRequest(int a, int b, String operator) {
        this.a = a;
        this.b = b;
        this.operator = operator;
    }

    public CalculatorRequest() {
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public String getOperator() {
        return operator;
    }
}
