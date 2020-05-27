package soho.boyo.cost_calculator.Calculate;

public class Calculate {

    public static float groundCost(int groundCostPerHour, float hour) {
        boolean b = groundCostPerHour > 0 && hour > 0;
        return b ? groundCostPerHour * hour : 0;
    }

    public static String getGroundCost(int groundCostPerHour, float hour) {
        boolean b = groundCostPerHour > 0 && hour > 0;
        String string;
        string = b ? "場地(小時/元) x 使用小時 = 總場地費\n" +
                groundCostPerHour + " x " + hour + " = " + groundCost(groundCostPerHour, hour) + "\n"
                : "";
        return string;
    }

    public static float ballCost(float ballCostEach, int numbers) {
        boolean b = ballCostEach > 0 && numbers > 0;
        return b ? ballCostEach * numbers : 0;
    }

    public static String getBallCost(float ballCostEach, int numbers) {
        boolean b = ballCostEach > 0 && numbers > 0;
        String string;
        string = b ? "羽球一顆(元) x 使用數量 = 總羽球花費\n" +
                ballCostEach + " x " + numbers + " = " + ballCost(ballCostEach, numbers) + "\n"
                : "";
        return string;
    }

    public static String getTotalCost(float groundCost, float ballCost) {
        int number = 0;
        number += groundCost > 0 ? 1 : 0;
        number += ballCost > 0 ? 1 : 0;

        String string = "";
        string += groundCost > 0 ? "總場地費 " : "";
        string += number >= 2 ? "+ " : "";
        string += ballCost > 0 ? "總羽球花費 " : "";
        string += groundCost > 0 || ballCost > 0 ? "= 今日總支出\n" : "";

        string += groundCost > 0 ? groundCost + " " : "";
        string += number >= 2 ? "+ " : "";
        string += ballCost > 0 ? ballCost + " " : "";
        string += groundCost > 0 || ballCost > 0 ? "= " + (groundCost + ballCost) + "\n" : "";
        return string;
    }

    public static int getEachPay(int average) {
        int eachPost = average;
        while (eachPost % 10 != 0) {
            eachPost++;
        }
        return eachPost;
    }

    public static String getAverage(float totalCost, float people, int average) {
        int each_cost = getEachPay(average);

        String string;
        string = "----------\n" +
                "今日總支出 / 打球人數 = 平均每人分攤\n" +
                totalCost + " / " + people + " = " + (totalCost / people) + "\n" +
                "建議每人收費：" + each_cost + "\n" +
                "----------\n";
        return string;
    }

    public static String getPublicExpense(float people, int money, float totalCost) {
        String string;
        string = "每人收費 x 人數 = 今日總收金額\n" +
                money + " x " + people + " = " + (money * people) + "\n" +
                "今日總收金額 - 今日總支出 = 今日公費\n" +
                (money * people) + " - " + totalCost + " = " + ((money * people) - totalCost) + "\n";
        string = money > 0 ? string : "";
        return string;
    }

}
