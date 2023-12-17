import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ParticleSwarmIterator extends OptimizationIterator {
    public double c1;
    public double c2;
    public int nParticles = 10;
    public double[][] particlesVector;
    public double[][] velocityVector;
    public double[] gBest = null;
    public double[][] pBest;
    public ParticleSwarmIterator(double[][] domain, String evalExpr, float c1, float c2){
        super(domain, evalExpr);
        this.c1 = c1;
        this.c2 = c2;
        this.velocityVector = generateInitialVector(0.2);
        this.particlesVector = generateInitialVector(1);
        displayVector(this.particlesVector);
        initBestValues();
    }

    public double[][] generateInitialVector(double domainMultiplier){
        double[][] vector = new double[nParticles][domain.length];
        Random random = new Random();
        for (int i = 0; i < nParticles; i++) {
            for (int j = 0; j < domain.length; j++) {
                vector[i][j] = random.nextDouble() *
                        (domainMultiplier*domain[j][1] - domainMultiplier*domain[j][0])
                        + domainMultiplier*domain[j][0];
            }
        }
        return vector;
    }

    public double getFunctionValue(double[] particle){
        if(particle.length == 1){
            return this.targetFunction.evaluate(particle[0]);
        }
        return this.targetFunction.evaluate(particle[0], particle[1]);
    }

    public int findIndexOfMinValue() {
        return Arrays.stream(pBest)
                .min((p1, p2) -> Double.compare(p1[domain.length], p2[domain.length]))
                .orElseThrow(() -> new RuntimeException("Array is empty or contains NaN"))
                .length - 1;
    }

    public void initBestValues(){
        pBest = new double[nParticles][domain.length+1];
        for (int i = 0; i < nParticles; i++) {
            int j;
            for (j = 0; j < domain.length; j++) {
                pBest[i][j] = particlesVector[i][j];
            }
            pBest[i][j] = getFunctionValue(particlesVector[i]);
            if(gBest == null || pBest[i][domain.length] < gBest[domain.length]){
                gBest = pBest[i];
            }
        }
    }
    public void displayVector(double[][] vector){
        for (int i = 0; i < nParticles; i++) {
            for (int j = 0; j < domain.length; j++) {
                System.out.print(vector[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
