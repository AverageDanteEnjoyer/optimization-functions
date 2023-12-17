public class Main {
    public static void main(String[] args) {
        String rosenbrockFnExpr = "(1-x1)^2+100*(x2-x1^2)^2";
        Function rosenbrockFn = new Function(rosenbrockFnExpr);
        System.out.println(rosenbrockFn.evaluate(1, 2));
    }
}