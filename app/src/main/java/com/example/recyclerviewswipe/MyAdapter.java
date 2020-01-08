package com.example.recyclerviewswipe;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements SelectMode{
    private List<String> nameList;
    private SelectMode mListener;

    public MyAdapter(List<String> list) {
        nameList = list;
    }

    public MyAdapter(List<String> list, SelectMode listener) {
        nameList = list;
        mListener = listener;
    }

    private SparseArray<Boolean> selectedList = new SparseArray<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final String name = nameList.get(position);
        holder.textView.setText(name);

        // Evento para eliminar elemento
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            /*@Override
            public void onClick(View v){
                remove( position );
            }*/
            @Override
            public void onClick(View v) {
                holder.itemView.setSelected(!holder.itemView.isSelected());
                if (holder.itemView.isSelected()) {
                    selectedList.put(position, true);
                } else {
                    selectedList.remove(position);
                }
                onSelect();
            }
        });
    }


    @Override
    public int getItemCount() {
        if (nameList==null) {
            return 0;
        } else {
            return nameList.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public MyViewHolder(View itemVieww) {
            super(itemVieww);
            textView = itemView.findViewById(R.id.textView);
        }
    }

    private void remove(int position ){
        //if(position >= nameList.size()) return;
        nameList.remove( position );
        //notifyItemRemoved( position );
        notifyDataSetChanged();
        selectedList.clear();
    }

    public void add(String elm){
        nameList.add( elm );
        notifyDataSetChanged();
    }

    @Override
    public void onSelect() {
        if (mListener!=null) {
            mListener.onSelect();
        }
    }

    public void deleteAllSelected() {
        if (selectedList.size()==0) return;

        for (int index = nameList.size()-1; index >=0; index--) {
            if (selectedList.get(index,false)) {
                remove(index);
            }
        }
        selectedList.clear();
    }
}