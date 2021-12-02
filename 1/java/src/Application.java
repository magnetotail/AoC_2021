public class Application {

    public static void main(String[] args) {

        System.out.println(getDepthIncreaseTime(args));

    }

    private static int getDepthIncreaseTime(String[] depths) {
        int increased = 0;

        int prev = -1;
        for (int i = 0, depthsLength = depths.length; i < depthsLength - 2; i++) {
            int depth1 = Integer.parseInt(depths[i]);
            int depth2 = Integer.parseInt(depths[i+1]);
            int depth3 = Integer.parseInt(depths[i+2]);
            int depthSum = depth1 + depth2 + depth3;

            if (prev > 0 && prev < depthSum) {
                increased++;
            }

            prev = depthSum;
        }
        return increased;
    }
}
