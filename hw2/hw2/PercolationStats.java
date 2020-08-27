package hw2;

public class PercolationStats {
    private int montcarloNum;
    private int gridN;

    public PercolationStats(int montcarloNum, int gridN) {
        this.montcarloNum = montcarloNum;
        this.gridN = gridN;
    }

    public double getStats(){
        double[] results = new double[montcarloNum];
        double result = 0;
        for (int cnt = 0; cnt < montcarloNum; cnt++) {
            PercolationFactory perF = new PercolationFactory();
            Percolation perc = perF.make(gridN);
            while(!perc.percolates()) {
                int targetRow = (int)(Math.random()*gridN);
                int targetCol = (int)(Math.random()*gridN);
                perc.open(targetRow, targetCol);
            }
            results[cnt] = (double)perc.numberOfOpenSites() / (double)(gridN*gridN);
            System.out.println(results[cnt]);
        }
        for (double i:results) {
            result += i;
        }
        return result/montcarloNum;
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(100, 100);
        double result = ps.getStats();
        System.out.println("Final result: " + result);
    }

}
