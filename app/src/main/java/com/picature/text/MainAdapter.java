package com.picature.text;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.picature.text.recyclerview.BaseRecyclerAdapter;

public class MainAdapter extends BaseRecyclerAdapter<TypeBean> {

    public MainAdapter(Context context, int mode) {
        super(context, mode);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new MainViewHolder(mInflater.inflate(R.layout.item_home, parent, false), mContext);
    }

    @Override
    protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, TypeBean item, int position) {
        MainViewHolder mainViewHolder = (MainViewHolder) holder;
        mainViewHolder.setData(item, position);
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {


        private Context context;
        private TextView tvTitle;
        private TextView tvNum;

        public MainViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvNum = itemView.findViewById(R.id.tv_num);
        }

        public void setData(TypeBean item, int position) {
            tvTitle.setText(item.getTitle());
            tvNum.setText(String.format("%s", item.getNum()));
        }
    }
}
