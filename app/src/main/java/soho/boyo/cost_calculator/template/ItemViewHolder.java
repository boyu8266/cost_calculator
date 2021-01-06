package soho.boyo.cost_calculator.template;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import soho.boyo.cost_calculator.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    protected TextView key;
    protected TextView colon;
    protected EditText value;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        key = itemView.findViewById(R.id.key);
        colon = itemView.findViewById(R.id.colon);
        value = itemView.findViewById(R.id.value);
    }
}
