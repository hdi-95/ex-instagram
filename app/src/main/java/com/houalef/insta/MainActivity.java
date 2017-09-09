package com.houalef.insta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.houalef.insta.adapter.GridAdapter;
import com.houalef.insta.data.Request;
import com.houalef.insta.model.GridItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private GridAdapter mAdapter;
    private ArrayList<GridItem> mIems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("#LIMONADE");

        // set up the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // set up layout manager
        mLayoutManager = new GridLayoutManager(this, 3);

        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mAdapter.getItemViewType(position)) {
                    case GridItem.Header:
                        return 3;

                    case GridItem.Image:
                        return 1;

                    default:
                        return 1;
                }
            }
        });

        mRecyclerView.setLayoutManager(mLayoutManager);

        // set up RecyclerView adapter

        mAdapter = new GridAdapter(new GridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GridItem item) {

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_DATA, item.getSocialPicture());
                startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(mAdapter);

        // set up RecyclerView's data

        mIems = new ArrayList<>();
        mIems.add(new GridItem("MEILLEURES PUBLICATIONS"));

        List<GridItem> data = Request.getData();

        if (data.size() > 9)
            mIems.addAll(data.subList(0, 9));

        mIems.add(new GridItem("PLUS RÉCENTES"));
        mIems.addAll(data);

        mAdapter.setData(mIems);

        mAdapter.notifyDataSetChanged();

    }
}
