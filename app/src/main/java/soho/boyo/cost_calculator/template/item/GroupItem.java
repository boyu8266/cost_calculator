package soho.boyo.cost_calculator.template.item;

import android.graphics.Color;

public class GroupItem implements Item {
    private Type type = Type.GROUP;
    private Arithmetic arithmetic;
    private String firstKey;
    private String secondKey;
    private String color;

    public GroupItem(Arithmetic arithmetic, String firstKey, String secondKey, String color) {
        this.arithmetic = arithmetic;
        this.firstKey = firstKey;
        this.secondKey = secondKey;
        this.color = color;
    }

    @Override
    public String[] getNames() {
        return new String[]{firstKey, secondKey};
    }

    @Override
    public int getColor() {
        if (null == color) {
            return Color.parseColor("#000000");
        }
        return Color.parseColor(color);
    }

    @Override
    public Arithmetic getArithmetic() {
        return arithmetic;
    }

    @Override
    public Type getType() {
        return type;
    }
}
