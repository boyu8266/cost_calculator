package soho.boyo.cost_calculator.template;

import android.graphics.Color;

public class Item {

    public String key;
    public String color;

    public Item(String key, String color) {
        this.key = key;
        this.color = color;
    }

    public int getColor() {
        if (null == color) {
            return Color.parseColor("#000000");
        }
        return Color.parseColor(color);
    }
}
