public class SkyView {
    private double[][] view;

    public SkyView(int numRows, int numCols, double[] scanned) {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (r % 2 == 1)
                    view[r][c] = scanned[numCols - (c + 1) + r * numCols];
                else
                    view[r][c] = scanned[c + r * numCols];
            }
        }
    }

    public double getAverage(int startRow, int endRow, int startCol, int endCol) {
        double sum = 0.0;
        for (int r = startRow; r < endRow; r++) {
            for (int c = startCol; c < endCol; c++) {
                sum += view[r][c];
            }
        }
        return (sum / ((endRow - startRow) * (endCol - startCol)));
    }
    
}
