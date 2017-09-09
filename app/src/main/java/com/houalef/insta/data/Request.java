package com.houalef.insta.data;

import android.util.Log;

import com.houalef.insta.InstaApplication;
import com.houalef.insta.model.GridItem;
import com.houalef.insta.model.SocialPicture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * Created by Mehdi on 09/09/2017.
 */

public class Request {

    private static String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = InstaApplication.getAppContext().getAssets().open("instagram.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static ArrayList<GridItem> getData() {

        ArrayList<GridItem> list = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("data");

            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject data = m_jArry.getJSONObject(i);
                JSONObject img_data = data.getJSONObject("images");
                JSONObject thumbnail = img_data.getJSONObject("thumbnail");
                String thumbnail_url = thumbnail.getString("url");
                Log.v("Request", "thumbnail = " + thumbnail_url);
                list.add(new GridItem(new SocialPicture(thumbnail_url)));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;

    }


}
