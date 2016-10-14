package com.therishka.fragmania;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.therishka.fragmania.cats.Cat;

import java.util.List;

/**
 * @author Rishad Mustafaev
 */

public class CatsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Cat> cats;
    private CatItemClickListener mCatItemClickListener;

    public void setCats(List<Cat> cats) {
        this.cats = cats;
        notifyDataSetChanged();
    }

    public void setCatItemClickListener(CatItemClickListener catItemClickListener) {
        mCatItemClickListener = catItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_list_item, parent, false);
        return new CatViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CatViewHolder) {
            ((CatViewHolder) holder).mCat = cats.get(position);
            ((CatViewHolder) holder).mCatName.setText(cats.get(position).getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cat cat = ((CatViewHolder) holder).mCat;
                    mCatItemClickListener.onItemClick(cat.getName());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return cats != null ? cats.size() : 0;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {

        TextView mCatName;
        Cat mCat;

        public CatViewHolder(View itemView) {
            super(itemView);
            mCatName = (TextView) itemView.findViewById(R.id.cat_name);
        }
    }

    public interface CatItemClickListener {
        void onItemClick(String catName);
    }
}
