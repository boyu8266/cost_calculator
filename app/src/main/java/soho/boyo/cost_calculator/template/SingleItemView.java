package soho.boyo.cost_calculator.template;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import soho.boyo.cost_calculator.R;

class SingleItemView extends ItemView {
    private TextView titleTV;
    private EditText valueET;

    public SingleItemView(@NonNull View itemView) {
        super(itemView);

        titleTV = itemView.findViewById(R.id.key);
        valueET = itemView.findViewById(R.id.value);
    }

    @Override
    public TextView[] getTitleTVArray() {
        return new TextView[]{titleTV};
    }

    @Override
    public EditText[] getValueETArray() {
        return new EditText[]{valueET};
    }

    @Override
    public TextView getSubtotalTV() {
        return null;
    }
}
