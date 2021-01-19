package soho.boyo.cost_calculator.template.item;

import android.graphics.Color;

public class SingleItem implements Item {
    private Type type = Type.SINGLE;
    private Arithmetic arithmetic;
    private String key;
    private String color;

    public SingleItem(Arithmetic arithmetic, String key, String color) {
        this.arithmetic = arithmetic;
        this.key = key;
        this.color = color;
    }

    @Override
    public String[] getNames() {
        return new String[]{key};
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
