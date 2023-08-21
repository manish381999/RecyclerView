package com.tie.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Person> selectedPersons;
    private SelectedRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.selectedRecyclerView);
        selectedPersons = getIntent().getParcelableArrayListExtra("selectedPersons");

        adapter = new SelectedRecyclerViewAdapter(selectedPersons, new SelectedRecyclerViewAdapter.ItemDeleteListener() {
            @Override
            public void onItemDelete(int position) {
                // Remove the item from the list and update the RecyclerView
                selectedPersons.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.backButton).setOnClickListener(view -> onBackButtonClicked());
    }

    private void onBackButtonClicked() {
        // Return to the first activity and pass back the original selected persons list
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("updatedSelectedPersons", new ArrayList<>(selectedPersons));
        setResult(RESULT_OK, intent);
        finish();
    }
}
