package com.example.dnd;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder> {

    private ArrayList<Skill> skills;
    final private OnListItemClickListener onListItemClickListener;

    SkillAdapter (ArrayList<Skill> skills, OnListItemClickListener listener) {
        this.skills = skills;
        onListItemClickListener = listener;
    }

    @NonNull
    @Override
    public SkillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.skillset_list_item, viewGroup, false);
        return new SkillAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillAdapter.ViewHolder viewHolder, int position) {
        viewHolder.name.setText(skills.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_skill_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
