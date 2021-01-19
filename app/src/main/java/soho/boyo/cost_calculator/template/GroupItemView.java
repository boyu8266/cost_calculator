package soho.boyo.cost_calculator.template;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import soho.boyo.cost_calculator.R;

class GroupItemView extends ItemView {
    protected SingleItemView typeSingleItemView;
    protected SingleItemView numberSingleItemView;

    /* subtotal */
    protected TextView subtotalTV;

    public GroupItemView(@NonNull View itemView) {
        super(itemView);

        View typeView = itemView.findViewById(R.id.type);
        typeSingleItemView = new SingleItemView(typeView);

        View numberView = itemView.findViewById(R.id.number);
        numberSingleItemView = new SingleItemView(numberView);

        View layout = itemView.findViewById(R.id.subtotal_layout);
        subtotalTV = layout.findViewById(R.id.subtotal);
    }

    @Override
    public TextView[] getTitleTVArray() {
        return new TextView[]{
                typeSingleItemView.getTitleTVArray()[0],
                numberSingleItemView.getTitleTVArray()[0]
        };
    }

    @Override
    public EditText[] getValueETArray() {
        return new EditText[]{
                typeSingleItemView.getValueETArray()[0],
                numberSingleItemView.getValueETArray()[0]
        };
    }

    @Override
    public TextView getSubtotalTV() {
        return subtotalTV;
    }
}
