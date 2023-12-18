public class Main {
    public static void main(String[] args) {
        String rosenbrockFnExpr = "(1-x1)^2+100*(x2-x1^2)^2";
//        OptimizationIterator iterator = new ParticleSwarmIterator(new double[][]{{-1000, 1000}, {-1000, 1000}}, rosenbrockFnExpr, 1.3, 1.1, 0.2);
        OptimizationIterator iterator = new SimulatedAnnealingIterator(new double[][]{{-100, 100}, {-100, 100}}, rosenbrockFnExpr, 0.95, 0.95, new double[]{15, -50});
        double[] nextBest = null;
        for(int i=0;i<1000;i++){
            double[] tmp = iterator.next();
            if(tmp == nextBest)continue;
            nextBest=tmp;
            System.out.println(i);
            System.out.println(nextBest[0]);
            System.out.println(nextBest[1]);
            System.out.println(nextBest[2]);
            System.out.print("\n");
        }
    }
}