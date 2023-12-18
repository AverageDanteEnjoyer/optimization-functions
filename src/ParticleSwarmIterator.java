import java.util.Random;

public class ParticleSwarmIterator extends OptimizationIterator {
    public double c1;
    public double c2;
    public int nParticles = 900;
    public double[][] particlesVector;
    public double[][] velocityVector;
    public double[] gBest = null;
    public double[][] pBest;
    public int epoch = 0;
    public ParticleSwarmIterator(double[][] domain, String evalExpr, double c1, double c2, double initialVelocityMultiplier){
        super(domain, evalExpr);
        this.c1 = c1;
        this.c2 = c2;
        this.velocityVector = generateInitialVector(initialVelocityMultiplier);
        this.particlesVector = generateInitialVector(1);
        pBest = new double[nParticles][domain.length+1];
    }
    @Override
    public double[] next(){
        updateBestValues();
        updateParticles();
        epoch++;
        return gBest;
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
    public void updateBestValues(){
        for (int i = 0; i < nParticles; i++) {
            double functionValue = targetFunction.evaluate(particlesVector[i]);
            if(!(epoch == 0) && !(functionValue < pBest[i][domain.length]))continue;

            int j;
            for (j = 0; j < domain.length; j++) {
                pBest[i][j] = particlesVector[i][j];
            }
            pBest[i][j] = functionValue;
            if(gBest == null || pBest[i][domain.length] < gBest[domain.length]){
                gBest = pBest[i];
            }
        }
    }
    public void updateParticles(){
        Random random = new Random();
        for (int i = 0; i < nParticles; i++) {
            for (int j = 0; j < domain.length; j++) {
                velocityVector[i][j] += (c1* random.nextDouble()*(pBest[i][j] - particlesVector[i][j])
                        + c2*random.nextDouble()*(gBest[j]-particlesVector[i][j]));
                double updatedParticle = particlesVector[i][j] + velocityVector[i][j];
                if(updatedParticle > domain[j][0] && updatedParticle < domain[j][1]){
                    particlesVector[i][j] = updatedParticle;
                }
            }
        }
    }
    public void displayVector(double[][] vector){
        for (int i = 0; i < nParticles; i++) {
            for (int j = 0; j < vector[i].length; j++) {
                System.out.print(vector[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
