package com.tie.recyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        personList = generateStaticData();

        Objects.requireNonNull(getSupportActionBar()).setTitle("First Screen");

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(personList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.nextButton).setOnClickListener(view -> onNextButtonClicked());
    }

    private List<Person> generateStaticData() {
        List<Person> staticData = new ArrayList<>();
        staticData.add(new Person("Manish Kr Singh", "9123141559", "Male"));
        staticData.add(new Person("Priyanka", "9076543245", "Female"));
        staticData.add(new Person("Shrajal", "9876543245", "Male"));
        staticData.add(new Person("Shashikant Singh", "9896543245", "Male"));
        staticData.add(new Person("Arnav Dutt", "9896543245", "Male"));
        staticData.add(new Person("Bob", "9876543210", "Male"));
        staticData.add(new Person("Alice", "1234567890", "Female"));

        // Add more persons
        return staticData;
    }

    private void onNextButtonClicked() {
        List<Person> selectedPersons = new ArrayList<>();
        for (Person person : personList) {
            if (person.isSelected()) {
                selectedPersons.add(person);
            }
        }

        Log.d("MainActivity", "Selected persons size before starting MainActivity2: " + selectedPersons.size());

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putParcelableArrayListExtra("selectedPersons", (ArrayList<Person>) selectedPersons);
        startActivityForResult(intent, 1);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            ArrayList<Person> updatedPersons = data.getParcelableArrayListExtra("updatedSelectedPersons");
            if (updatedPersons != null) {
                for (Person person : personList) {
                    person.setSelected(updatedPersons.contains(person));
                }
                Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
            }
        }
    }
}
