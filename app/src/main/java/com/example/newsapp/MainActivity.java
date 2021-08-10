package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements recyclerViewOnClick {
    RecyclerView recyclerview;
    List<News> newslist;
    JSONArray array;
    News news;
    JSONArray jsonArray;
    JSONObject jsonObject;
    JSONObject newsbject;
    CustomAdapter adapter;
    private static String JSON_URL="https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview=findViewById(R.id.recyclerView);

        //newslist= new ArrayList<>();
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerview.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
        adapter = new CustomAdapter(getApplicationContext(),this);
        recyclerview.setAdapter(adapter);

        //recyclerview.setLayoutManager(new LinearLayoutManager(this));

        news_genarator();

    }
    private void news_genarator() {
        RequestQueue queue = Volley.newRequestQueue(this);
        List<News> newone=new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL,null , new Response.Listener<JSONObject>()  {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    jsonArray=response.getJSONArray("articles");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(int i=0;i<jsonArray.length();i++){
                    try {
                         jsonObject=jsonArray.getJSONObject(i);
                        try {
                            news=new News(jsonObject.getString("title"),jsonObject.getString("description"),jsonObject.getString("urlToImage"),
                                    jsonObject.getString("url"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        newone.add(news);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }



                adapter.updateData(newone);


            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });


        queue.add(jsonObjectRequest);

    }


    @Override
    public void onLongItemClick(int position) {
        //Toast.makeText(this, "Yes it's working", Toast.LENGTH_SHORT).show();
        String lin= null;
        try {
            lin = jsonObject.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Uri webpage = Uri.parse(lin);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}


