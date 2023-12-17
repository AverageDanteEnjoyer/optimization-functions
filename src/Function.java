import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

public class Function {
    private final DoubleEvaluator eval = new DoubleEvaluator();
    private final StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
    private final String evalExpression;
    public Function(String evalExpression){
        this.evalExpression = evalExpression;
    }

    public double evaluate(double x1, double x2){
        variables.set("x1", x1);
        variables.set("x2", x2);
        return eval.evaluate(evalExpression, variables);
    }
    public double evaluate(double x1){
        variables.set("x1", x1);
        return eval.evaluate(evalExpression, variables);
    }
}
