package com.houalef.insta;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Mehdi Houalef on 09/09/2017.
 */

public class InstaApplication extends Application {

    private final static String TAG = InstaApplication.class.getSimpleName();

    private static Context context;
    private static RequestQueue mVolleyRequestQueue;
    private static ImageLoader mImageLoader;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        // On initialise notre Thread-Pool
        mVolleyRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mVolleyRequestQueue.start();

        mImageLoader = new ImageLoader(mVolleyRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
    }

    @Override
    public void onTerminate() {
        mVolleyRequestQueue.stop();
        super.onTerminate();
    }

    public static RequestQueue getVolleyRequestQueue() {
        return mVolleyRequestQueue;
    }

    public static ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public static Context getAppContext() {
        return context;
    }

}