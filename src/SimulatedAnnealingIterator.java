import java.util.Arrays;
import java.util.Random;

public class SimulatedAnnealingIterator extends OptimizationIterator {
    private double initialTemperature;
    private double coolingRate;

    public SimulatedAnnealingIterator(double[][] domain, String evalExpr, double initialTemperature, double coolingRate) {
        super(domain, evalExpr);
        this.initialTemperature = initialTemperature;
        this.coolingRate = coolingRate;
    }

    @Override
    public double[] next() {
        return new double[3];
    }

    public double getFunctionValue(double[] particle){
        if(particle.length == 1){
            return this.targetFunction.evaluate(particle[0]);
        }
        return this.targetFunction.evaluate(particle[0], particle[1]);
    }

    private double[] generateRandomSolution() {
        Random random = new Random();
        double[] solution = new double[domain.length];
        for (int i = 0; i < domain.length; i++) {
            solution[i] = random.nextDouble() * (domain[i][1] - domain[i][0]) + domain[i][0];
        }
        return solution;
    }

}
