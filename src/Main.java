public class Main {
    public static void main(String[] args) {
        String rosenbrockFnExpr = "(1-x1)^2+100*(x2-x1^2)^2";
        ParticleSwarmIterator iter = new ParticleSwarmIterator(new double[][]{{-10, 10}, {-5, 5}}, rosenbrockFnExpr, 2, 2);
    }
}