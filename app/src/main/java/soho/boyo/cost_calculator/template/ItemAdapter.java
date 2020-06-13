package soho.boyo.cost_calculator.template;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import soho.boyo.cost_calculator.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private static List<Item> sData;

    public ItemAdapter(List<Item> data) {
        sData = data;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.template_key_value, viewGroup, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = sData.get(position);

        TextView key = holder.key;
        TextView value = holder.value;

        key.setText(item.key);
        value.setText(item.value);
    }

    @Override
    public int getItemCount() {
        return sData.size();
    }
}