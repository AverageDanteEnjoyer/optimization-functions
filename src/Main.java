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
        int epochs;
        switch (iteratorType){
            case "SA":
                iterator = new SimulatedAnnealingIterator(new double[][]{{-10, 10}, {-10, 10}}, fnExpr, 0.9, 0.95, new double[]{5, -5});
                epochs = 1000;
                break;
            case "PSO":
                iterator = new ParticleSwarmIterator(new double[][]{{-10, 10}, {-10, 10}}, fnExpr, 1.3, 1.1, 0.2);
                epochs = 100;
                break;
            default:
                System.out.println("Nie istnieje typ: " + iteratorType);
                return;
        }
        iterateThroughEpochs(iterator, epochs);
        scanner.close();
    }

    public static void iterateThroughEpochs(OptimizationIterator iterator, int epochs){
        double[] nextBest = null;
        for(int i=0;i<epochs;i++){
            double[] tmp = iterator.next();
            if(nextBest != null && tmp[tmp.length-1] == nextBest[tmp.length-1])continue;
            nextBest=tmp;
            System.out.println("Epoka: " + i);
            int j;
            for(j=0;j<nextBest.length-1;j++){
                System.out.println("x" + j + ": " + nextBest[j]);
            }
            System.out.println("Wartość funkcji: " + nextBest[j]);
            System.out.print("\n");
        }
    }
}