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
    private List<Item> list;
    private OnTextChangedListener onTextChangedListener;

    public ItemAdapter(List<Item> list, OnTextChangedListener onTextChangedListener) {
        if (null == onTextChangedListener) {
            throw new RuntimeException("null == onTextChangedListener");
        }

        this.list = list;
        this.onTextChangedListener = onTextChangedListener;
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
        final Item item = list.get(position);

        TextView key = holder.key;
        TextView colon = holder.colon;
        EditText value = holder.value;

        key.setText(item.key);
        key.setTextColor(item.getColor());
        colon.setTextColor(item.getColor());
        final int index = position;
        value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                onTextChangedListener.onTextChanged(index, editable.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onViewRecycled(@NonNull ItemViewHolder holder) {
        super.onViewRecycled(holder);
        holder.key.setText("");
        holder.value.addTextChangedListener(null);
    }

    public interface OnTextChangedListener {
        void onTextChanged(int index, String str);
    }
}
