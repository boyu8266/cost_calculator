package soho.boyo.cost_calculator.template;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import soho.boyo.cost_calculator.R;
import soho.boyo.cost_calculator.template.item.Item;

public class ItemAdapter extends RecyclerView.Adapter<ItemView> {
    private List<Item> list;

    public ItemAdapter(List<Item> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layout = list.get(position).getType() == Item.Type.GROUP ?
                R.layout.group_item : R.layout.template_key_value;
        View view = inflater.inflate(layout, viewGroup, false);

        ItemView itemView = list.get(position).getType() == Item.Type.GROUP ?
                new GroupItemView(view) : new SingleItemView(view);
        return itemView;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemView holder, int position) {
        final Item item = list.get(position);

        switch (item.getType()) {
            case GROUP:
                group(holder, item);
                break;

            case SINGLE:
                single(holder, item);
                break;

            default:
        }
//
//        TextView key = holder.key;
//        TextView colon = holder.colon;
//        EditText value = holder.value;
//
//        key.setText(singleItem.key);
//        key.setTextColor(singleItem.getColor());
//        colon.setTextColor(singleItem.getColor());
//        final int index = position;
//        value.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                onTextChangedListener.onTextChanged(index, editable.toString());
//            }
//        });
    }

    private void group(ItemView holder, Item item) {
        TextView firstTV = holder.getTitleTVArray()[0];
        EditText firstET = holder.getValueETArray()[0];

        TextView secondTV = holder.getTitleTVArray()[1];
        EditText secondET = holder.getValueETArray()[1];

        TextView subtotalTV = holder.getSubtotalTV();

        firstTV.setText(item.getNames()[0]);
        secondTV.setText(item.getNames()[1]);
    }

    private void single(ItemView holder, Item item) {
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
