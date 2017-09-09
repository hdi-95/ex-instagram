package com.houalef.insta;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.toolbox.NetworkImageView;
import com.houalef.insta.model.SocialPicture;

/**
 * Created by Mehdi on 09/09/2017.
 */

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_DATA = "EXTRA_DATA";
    private NetworkImageView mNetworkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));


        Intent intent = getIntent();

        SocialPicture sp = (SocialPicture) intent.getSerializableExtra(EXTRA_DATA);

        if (sp != null) {
            getSupportActionBar().setTitle(sp.getText());

            mNetworkImageView = (NetworkImageView) findViewById(R.id.detail_niv);
            mNetworkImageView.setDefaultImageResId(R.drawable.placeholder);
            mNetworkImageView.setImageUrl(sp.getStandard_resolution(), InstaApplication.getImageLoader());
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
