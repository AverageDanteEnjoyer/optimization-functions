import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String rosenbrockFnExpr = "(1-x1)^2+100*(x2-x1^2)^2";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podać funkcję (zmienne proszę nazwać x1, x2, ..., xn)");
        String fnExpr = scanner.nextLine();
        System.out.println("Proszę podać algorytm [SA/PSO]");
        String iteratorType = scanner.nextLine();

        OptimizationIterator iterator;
        switch (iteratorType){
            case "SA":
                iterator = new SimulatedAnnealingIterator(new double[][]{{-10, 10}, {-10, 10}}, fnExpr, 0.9, 0.95, new double[]{5, -5});
                break;
            case "PSO":
                iterator = new ParticleSwarmIterator(new double[][]{{-10, 10}, {-10, 10}}, fnExpr, 1.3, 1.1, 0.2);
                break;
            default:
                System.out.println("Nie istnieje typ: " + iteratorType);
                return;
        }

        double[] nextBest = null;
        for(int i=0;i<1000;i++){
            double[] tmp = iterator.next();
            if(nextBest != null && tmp[tmp.length-1] == nextBest[tmp.length-1])continue;
            nextBest=tmp;
            System.out.println(i);
            System.out.println(nextBest[0]);
            System.out.println(nextBest[1]);
            System.out.println(nextBest[2]);
            System.out.print("\n");
        }
        scanner.close();
    }

    private void iterateThroughEpochs(OptimizationIterator iterator, int epochs){

    }
}