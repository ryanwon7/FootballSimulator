package plays;

public class AverageOutput {
    public static void main(String[] args) {
        int startYard = 0;
        int startPlays = 0;
        int interceptions = 0;
        int fumbles = 0;

        while (startPlays < 1000000) {
            int gained = StretchRun.run(3);
            if (gained == 10000){
                interceptions++;
                continue;
            } else if (gained == 10001) {
                fumbles++;
            } else {
                startYard = startYard + gained;
                startPlays++;
                System.out.println(gained);
            }
        }
        double YPC = (double) startYard/startPlays;
        double fmbPct = (double) fumbles/(startPlays+fumbles) * 100;
        double intPct = (double) interceptions/(startPlays+interceptions) * 100;
        System.out.println("Summary: YPC is " + YPC);
        System.out.println("Summary: Fumble PCT % " + fmbPct + '%');
        System.out.println("Summary: Int PCT % " + intPct + '%');
    }
}
