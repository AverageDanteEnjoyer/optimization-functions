public class Main {
    public static void main(String[] args) {
        String rosenbrockFnExpr = "(1-x1)^2+100*(x2-x1^2)^2";
        ParticleSwarmIterator iterator = new ParticleSwarmIterator(new double[][]{{-10, 10}, {-10, 10}}, rosenbrockFnExpr, 1.3, 1.1, 0.2);
        double[] nextBest = null;
        for(int i=0;i<80;i++){
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