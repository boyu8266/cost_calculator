package soho.boyo.cost_calculator.template;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

abstract class ItemView extends RecyclerView.ViewHolder {
    public ItemView(@NonNull View itemView) {
        super(itemView);
    }

    public abstract TextView[] getTitleTVArray();

    public abstract EditText[] getValueETArray();

    public abstract TextView getSubtotalTV();
}
