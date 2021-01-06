package soho.boyo.cost_calculator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import soho.boyo.cost_calculator.template.Item;
import soho.boyo.cost_calculator.template.ItemAdapter;

public class TempActivity extends Activity
        implements ItemAdapter.OnTextChangedListener, Key {

    private List<Item> list = new LinkedList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.template);

        list.add(new Item(GROUND_COST, "#0066FF"));
        list.add(new Item(GROUND_HOUR, "#0066FF"));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ItemAdapter itemAdapter = new ItemAdapter(list, this::onTextChanged);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void onTextChanged(int index, String str) {
        switch (list.get(index).key) {
            case GROUND_COST:
                break;

            case GROUND_HOUR:
                break;

            default:
        }
    }
}
