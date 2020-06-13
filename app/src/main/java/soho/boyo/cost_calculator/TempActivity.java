package soho.boyo.cost_calculator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import soho.boyo.cost_calculator.template.Item;
import soho.boyo.cost_calculator.template.ItemAdapter;

public class TempActivity extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.template);

        List<Item> data = new LinkedList<Item>() {{
            add(new Item("場地費用", "1"));
            add(new Item("實際小時", "2"));
        }};

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ItemAdapter itemAdapter = new ItemAdapter(data);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

}
