package soho.boyo.cost_calculator.template;

import android.graphics.Color;

public class Item {

    public String key;
    public String value;

    public String color;

    public Item(String key, String value, String color) {
        this.key = key;
        this.value = value;
        this.color = color;
    }

    public int getColor() {
        if (null == color) {
            return Color.parseColor("#000000");
        }
        return Color.parseColor(color);
    }
}
