public class RouteCipher {
    private String[][] letterBlock;
    private int numRows;
    private int numCols;

    private void fillBlock(String str) {
        if (str.length() > numRows*numCols)
            str = str.substring(0, numRows*numCols);
        else if (str.length() < numRows*numCols) {
            for (int i = 0; i < numRows*numCols-str.length(); i++) {
                str += "A";
            }
        }
        for (int r = 0; r < numRows; r++) {
            String temp = str.substring(str.length() / numRows * r, str.length() / numRows * (r + 1));
            for (int c = 0; c < numCols; c++) {
                letterBlock[r][c] = temp.substring(c, c + 1);
            }
        }
    }

    private String encryptBlock() {
        return "";
    }

    public String encryptMessage(String message) {
        int num = message.length() / (numRows * numCols);
        if (message.length() % (numRows * numCols) != 0)
            num++;
        String result = "";
        for (int i = 0; i < num; i++) {
            if (i < num - 1)
                fillBlock(message.substring(i * numRows * numCols, i * numRows * numCols + (numRows * numCols)));
            else
                fillBlock(message.substring(i * numRows * numCols));
            result += encryptBlock();
        }
        return result;
    }
}
