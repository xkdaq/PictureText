package com.picature.text;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.picature.text.recyclerview.BaseRecyclerAdapter;
import com.picature.text.recyclerview.RecyclerRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class TotalActivity extends AppCompatActivity implements RecyclerRefreshLayout.SuperRefreshLayoutListener {


    private MainAdapter mAdapter;
    private RecyclerRefreshLayout mRefreshLayout;
    private RecyclerView recyclerView;

    private List<TypeBean> mdata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        mRefreshLayout = findViewById(R.id.refreshLayout);
        recyclerView = findViewById(R.id.recyclerView);


        mAdapter = new MainAdapter(this, BaseRecyclerAdapter.ONLY_FOOTER);
        mRefreshLayout.setSuperRefreshLayoutListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);


        for (int i = 0; i < 10; i++) {
            TypeBean typeBean = new TypeBean();
            typeBean.setId("00" + i);
            typeBean.setTitle("xuke" + i);
            typeBean.setNum(0);
            mdata.add(typeBean);
        }

        mAdapter.addAll(mdata);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long itemId) {
                TypeBean item = mAdapter.getItem(position);
                item.setNum(item.getNum() + 1);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onRefreshing() {

    }

    @Override
    public void onLoadMore() {

    }
}
