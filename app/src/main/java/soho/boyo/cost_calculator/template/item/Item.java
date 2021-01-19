package soho.boyo.cost_calculator.template.item;

public interface Item {
    String[] getNames();

    int getColor();

    Arithmetic getArithmetic();

    Type getType();

    enum Arithmetic {
        ADDITION,
        MINUS
    }

    enum Type {
        GROUP,
        SINGLE
    }
}
