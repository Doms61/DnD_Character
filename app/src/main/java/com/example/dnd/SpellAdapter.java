package com.example.dnd;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SpellAdapter extends RecyclerView.Adapter<SpellAdapter.ViewHolder> {

    private ArrayList<Spell> spells;
    final private OnListItemClickListener onListItemClickListener;

   SpellAdapter (ArrayList<Spell> spells, OnListItemClickListener listener) {
       this.spells = spells;
       onListItemClickListener = listener;
   }

    @NonNull
    @Override
    public SpellAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.spell_list_item, viewGroup, false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpellAdapter.ViewHolder viewHolder, int position) {
       viewHolder.name.setText(spells.get(position).getSpellName());
    }

    @Override
    public int getItemCount() {
        return spells.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

       TextView name;

       public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_spell_name);
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
