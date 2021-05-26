package anns;

public class Example {

    final private double[] instance;
    final private double[] target;

    public Example(double[] instance, double[] target) {
        this.instance = new double[instance.length];
        System.arraycopy(instance, 0, this.instance, 0, instance.length);
        this.target = new double[target.length];
        System.arraycopy(target, 0, this.target, 0, target.length);
    }

    public double[] getInstance() {
        return instance;
    }

    public double[] getTarget() {
        return target;
    }

    public int getInstanceLength(){
        return instance.length;
    }

    public int getTargetLength(){
        return target.length;
    }

    public double getInstanceValue(int index) {
        if(index < 0 || index >= instance.length){
            throw new ArrayIndexOutOfBoundsException();
        }
        return instance[index];
    }

    public double getTargetValue(int index) {
        if(index < 0 || index >= instance.length){
            throw new ArrayIndexOutOfBoundsException();
        }
        return target[index];
    }
}
