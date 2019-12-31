
class perceptron {
    double[] weights = new double[2];
    int epoch = 100;
    double lr = 0.1, b = Math.random();
    perceptron() {
        for(int i=0;i<weights.length;i++) weights[i] = Math.random();
    }

    void show() {
        for(int i=0;i<weights.length;i++) System.out.print(weights[i]+" ");
        System.out.println();
    }
    int predict(int[] x) {
        double sum = 0;
        for(int i=0;i<weights.length;i++) 
            sum += weights[i]*x[i];
        sum += b;
        return sum > 0?1:0;
    }
    void train(int[][] inputs, int[] targets) {
        while(epoch-->0) {
            int totalError = 0;
            for(int i=0;i<inputs.length;i++) {
                int out = predict(inputs[i]);
                int target = targets[i];
                int error = target-out;
                totalError += error*error;
                for(int j=0;j<weights.length;j++) weights[j] += lr*error*inputs[i][j];
                b += error*lr;
            }
            System.out.print("total-error: "+totalError+" weights are ");
            show();
            if(totalError==0) break;
        }
    }
}
class demo {
    public static void main(String[] args) {
        perceptron p = new perceptron();
        p.show();

        int[][] data = new int[][] {{0,0},{0,1},{1,0},{1,1}};
        int[] targets = new int[] {0,1,1,1};
        p.train(data, targets);
        System.out.println(p.predict(new int[]{0,1})+"--"+p.predict(new int[]{1,0})+"--"+p.predict(new int[]{0,0})+"--"+p.predict(new int[]{1,1}));
    }
}