public abstract class OptimizationIterator {
    double[][] domain;
    Function targetFunction;
    public OptimizationIterator(double[][] domain, String evalExpr){
        this.domain = domain;
        targetFunction = new Function(evalExpr);
    }
}
