package soho.boyo.cost_calculator.Calculate;

public class Cost {

    private static int cost(int groundCostPerHour, int hour) {
        return groundCostPerHour * hour;
    }

    public static String getString(int groundCostPerHour, int hour) {
        String string;
        string = "場地(小時/元) x 使用小時 = 總場地費\n" +
                groundCostPerHour + " x " + hour + " = " + cost(groundCostPerHour, hour) + "\n";
        return string;
    }

}
