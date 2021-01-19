package soho.boyo.cost_calculator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import soho.boyo.cost_calculator.template.ItemAdapter;
import soho.boyo.cost_calculator.template.item.GroupItem;
import soho.boyo.cost_calculator.template.item.Item;

public class TempActivity extends Activity
        implements Key {

    private List<Item> list = new LinkedList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.template);

        list.add(new GroupItem(Item.Arithmetic.MINUS, GROUND_COST, GROUND_HOUR, "#0066FF"));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ItemAdapter itemAdapter = new ItemAdapter(list);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
