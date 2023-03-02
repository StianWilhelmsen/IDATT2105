package fullstack.stian.fullstack.Model;

public class Equation {
    private String a;
    private String b;
    private String operator;

    public Equation(String a, String b, String operator) {
        this.a = a;
        this.b = b;
        this.operator = operator;
    }

    public String getEquation() {
        return a + b+ operator;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "a = " + a +
                "b = " + b +
                "operator = " + operator;

    }
}
