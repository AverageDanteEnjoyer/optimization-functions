import java.util.Random;

public class SimulatedAnnealingIterator extends OptimizationIterator {
    private double initialTemperature;
    private double coolingRate;
    public double[] currentSolution = null;

    private int neighborsNumber = 20;

    public SimulatedAnnealingIterator(double[][] domain, String evalExpr, double initialTemperature, double coolingRate, double[] initialSolution) {
        super(domain, evalExpr);
        this.initialTemperature = initialTemperature;
        this.coolingRate = coolingRate;
        this.currentSolution = initialSolution;
    }

    @Override
    public double[] next() {

        return currentSolution;
    }

    public double getFunctionValue(double[] particle){
        if(particle.length == 1){
            return this.targetFunction.evaluate(particle[0]);
        }
        return this.targetFunction.evaluate(particle[0], particle[1]);
    }

    public double[][] generateNeighbors() {
        Random random = new Random();
        double[][] neighbors = new double[neighborsNumber][domain.length];

        for (int i = 0; i < neighborsNumber; i++) {
            for (int j = 0; j < currentSolution.length; j++) {
                double sigma = (domain[j][1] - domain[j][0])/4;
                double noise = sigma * random.nextGaussian();
                if(currentSolution[j]+noise < domain[j][0] || currentSolution[j]+noise > domain[j][1]){
                    noise = -noise;
                }
                neighbors[i][j] = currentSolution[j]+noise;
            }
        }

        return neighbors;
    }
}
