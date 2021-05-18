public class GameSpinner {
    private int sectors;
    private int previousResult;
    private int result;

    public GameSpinner(int sectors) {
        this.sectors = sectors;
        previousResult = 0;
        result = -1;
    }

    public int spin() {
        previousResult = result;
        result = (int) (Math.random() * sectors + 1);
        return result;
    }

    public int currentRun() {
        int currentRun = 1;
        if (previousResult == result)
            currentRun++;
        else
            currentRun = 1;
        return currentRun;
    }
}
