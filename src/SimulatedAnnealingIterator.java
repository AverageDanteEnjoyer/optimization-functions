import java.util.Random;

public class SimulatedAnnealingIterator extends OptimizationIterator {
    private double temperature;
    private final double coolingRate;
    private double[] currentSolution;

    private double sigmaCooling = 1;
    private final double sigmaCoolingRate = 0.993;

    public SimulatedAnnealingIterator(double[][] domain, String evalExpr, double initialTemperature, double coolingRate, double[] initialSolution) {
        super(domain, evalExpr);
        this.temperature = initialTemperature;
        this.coolingRate = coolingRate;
        this.currentSolution = initialSolution;
    }

    @Override
    public double[] next() {
        double[] neighbor = generateNeighbor();
        if(acceptNeighbor(neighbor)){
            currentSolution = neighbor;
        }
        coolDown();
        double[] solutionWithEnergy = new double[currentSolution.length+1];
        int i;
        for(i=0;i<currentSolution.length;i++){
            solutionWithEnergy[i] = currentSolution[i];
        }
        solutionWithEnergy[i] = targetFunction.evaluate(currentSolution);

        return solutionWithEnergy;
    }

    private double[] generateNeighbor() {
        Random random = new Random();
        double[] neighbor = new double[domain.length];

        for (int j = 0; j < currentSolution.length; j++) {
            double sigma = (domain[j][1] - domain[j][0]) / 4;
            double noise = sigmaCooling * sigma * (random.nextDouble() * 2 - 1);
            if (currentSolution[j] + noise < domain[j][0] || currentSolution[j] + noise > domain[j][1]) {
                noise = -noise;
            }
            neighbor[j] = currentSolution[j] + noise;
        }
        sigmaCooling *= sigmaCoolingRate;

        return neighbor;
    }
    private double getEnergyDelta(double[] neighbor){
        return (targetFunction.evaluate(neighbor) - targetFunction.evaluate(currentSolution)) / targetFunction.evaluate(neighbor);
    }
    private boolean acceptNeighbor(double[] neighbor){
        Random random = new Random();
        double energyDelta = getEnergyDelta(neighbor);
        return energyDelta < 0 || random.nextDouble() < Math.exp(-energyDelta / temperature);
    }

    private void coolDown(){
        temperature *= coolingRate;
    }

}
