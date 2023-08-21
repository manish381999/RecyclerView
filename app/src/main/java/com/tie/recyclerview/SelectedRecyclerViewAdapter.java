package com.tie.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tie.recyclerview.Person;
import com.tie.recyclerview.R;

import java.util.List;

public class SelectedRecyclerViewAdapter extends RecyclerView.Adapter<SelectedRecyclerViewAdapter.ViewHolder> {
    private List<Person> selectedPersons;
    private ItemDeleteListener deleteListener;

    public SelectedRecyclerViewAdapter(List<Person> selectedPersons, ItemDeleteListener deleteListener) {
        this.selectedPersons = selectedPersons;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_selected_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person person = selectedPersons.get(position);
        holder.nameTextView.setText(person.getName());
        holder.mobileTextView.setText(person.getMobile());
        holder.genderTextView.setText(person.getGender());

        holder.deleteButton.setOnClickListener(v -> {
            deleteListener.onItemDelete(position);
        });
    }

    @Override
    public int getItemCount() {
        return selectedPersons.size();
    }

    public interface ItemDeleteListener {
        void onItemDelete(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView mobileTextView;
        TextView genderTextView;
        ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.selectedNameTextView);
            mobileTextView = itemView.findViewById(R.id.selectedMobileTextView);
            genderTextView = itemView.findViewById(R.id.selectedGenderTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
