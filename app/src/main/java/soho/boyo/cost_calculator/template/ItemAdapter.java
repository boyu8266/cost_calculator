package soho.boyo.cost_calculator.template;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
        final Item item = sData.get(position);

        TextView key = holder.key;
        EditText value = holder.value;

        key.setText(item.key);
        value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                item.value = editable.toString();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sData.size();
    }
}
