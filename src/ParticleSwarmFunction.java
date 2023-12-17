public class ParticleSwarmFunction extends OptimizationFunction {
    public float c1;
    public float c2;



    public ParticleSwarmFunction(float[] domain, float c1, float c2){
        super(domain);
        this.c1 = c1;
        this.c2 = c2;
    }
}
