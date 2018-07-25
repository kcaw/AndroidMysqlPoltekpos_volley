package com.example.user_pc.tbcrudsql.ortu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.user_pc.tbcrudsql.Adapter.AdapterDataOrtu;
import com.example.user_pc.tbcrudsql.Utama;
import com.example.user_pc.tbcrudsql.pembayaran.HalamanPembayaran;
import com.example.user_pc.tbcrudsql.Model.ModelData;
import com.example.user_pc.tbcrudsql.R;
import com.example.user_pc.tbcrudsql.Util.AppController;
import com.example.user_pc.tbcrudsql.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HalamanOrtu extends AppCompatActivity {
    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelData> mItems;
    Button btnInsertOrtu, btnDeleteOrtu, btnHome1;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_ortu);

        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerviewTemp);
        btnInsertOrtu = (Button) findViewById(R.id.btn_insertOrtu);
        btnDeleteOrtu = (Button) findViewById(R.id.btn_deleteOrtu);
        btnHome1 = (Button) findViewById(R.id.btn_home1);
        pd = new ProgressDialog(HalamanOrtu.this);
        mItems = new ArrayList<>();

        loadJson();

        mManager = new LinearLayoutManager(HalamanOrtu.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new AdapterDataOrtu(HalamanOrtu.this,mItems);
        mRecyclerview.setAdapter(mAdapter);

        btnInsertOrtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanOrtu.this,InsertOrtu.class);
                startActivity(intent);
            }
        });

        btnDeleteOrtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanOrtu.this,deleteOrtu.class);
                startActivity(intent);
            }
        });

        btnHome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanOrtu.this,Utama.class);
                startActivity(intent);
            }
        });

    }

    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_VIEW_ORTU,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelData md = new ModelData();
                                md.setNik(data.getString("nik"));
                                md.setNortu(data.getString("nortu"));
                                md.setPekerjaan(data.getString("pekerjaan"));
                                md.setAlamat(data.getString("alamat"));
                                mItems.add(md);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(reqData);
    }

}

