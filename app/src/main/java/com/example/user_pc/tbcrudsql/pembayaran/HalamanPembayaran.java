package com.example.user_pc.tbcrudsql.pembayaran;

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
import com.example.user_pc.tbcrudsql.Adapter.AdapterDataPembayaran;
import com.example.user_pc.tbcrudsql.Utama;
import com.example.user_pc.tbcrudsql.pendaftaran.MainActivity;
import com.example.user_pc.tbcrudsql.Model.ModelData;
import com.example.user_pc.tbcrudsql.R;
import com.example.user_pc.tbcrudsql.Util.AppController;
import com.example.user_pc.tbcrudsql.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HalamanPembayaran extends AppCompatActivity {

    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelData> mItems;
    Button btnInsertPbyr, btnDeletePbyr, btnHome2;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_pembayaran);

        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerviewTemp);
        btnInsertPbyr = (Button) findViewById(R.id.btn_insertPbyr);
        btnDeletePbyr = (Button) findViewById(R.id.btn_deletePbyr);
        btnHome2 = (Button) findViewById(R.id.btn_home2);
        pd = new ProgressDialog(HalamanPembayaran.this);
        mItems = new ArrayList<>();

        loadJson();

        mManager = new LinearLayoutManager(HalamanPembayaran.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new AdapterDataPembayaran(HalamanPembayaran.this,mItems);
        mRecyclerview.setAdapter(mAdapter);

        btnInsertPbyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanPembayaran.this,InsertPembayaran.class);
                startActivity(intent);
            }
        });

        btnDeletePbyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanPembayaran.this,DeletePembayaran.class);
                startActivity(intent);
            }
        });

        btnHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HalamanPembayaran.this,Utama.class);
                startActivity(intent);
            }
        });


    }

    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_VIEW_PBYR,null,
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
                                md.setNisn(data.getString("nisn"));
                                md.setNsiswa(data.getString("nsiswa"));
                                md.setPilprodi(data.getString("pilprodi"));
                                md.setPiljurusan(data.getString("piljurusan"));
                                md.setJumlah(data.getString("jumlah"));
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