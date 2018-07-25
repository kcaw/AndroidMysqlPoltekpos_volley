package com.example.user_pc.tbcrudsql.ortu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user_pc.tbcrudsql.R;
import com.example.user_pc.tbcrudsql.Util.AppController;
import com.example.user_pc.tbcrudsql.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertOrtu extends AppCompatActivity {
    EditText nik,nortu,pekerjaan,alamat;
    Button btnbatalOrtu,btnsimpanOrtu;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_ortu);

        /*get data from intent*/

        Intent data = getIntent();
        final int update = data.getIntExtra("update", 0);
        String intent_nik = data.getStringExtra("nik");
        String intent_nortu = data.getStringExtra("nortu");
        String intent_pekerjaan = data.getStringExtra("pekerjaan");
        String intent_alamat = data.getStringExtra("alamat");
        /*end get data from intent*/

        nik = (EditText) findViewById(R.id.inp_nik);
        nortu = (EditText) findViewById(R.id.inp_nortu);
        pekerjaan = (EditText) findViewById(R.id.inp_pekerjaan);
        alamat = (EditText) findViewById(R.id.inp_alamat);
        btnbatalOrtu = (Button) findViewById(R.id.btn_cancelOrtu);
        btnsimpanOrtu = (Button) findViewById(R.id.btn_simpanOrtu);
        pd = new ProgressDialog(InsertOrtu.this);

        /*kondisi update / insert*/
        if (update == 1) {
            btnsimpanOrtu.setText("update Data");
            nik.setText(intent_nik);
            nik.setVisibility(View.GONE);
            nortu.setText(intent_nortu);
            pekerjaan.setText(intent_pekerjaan);
            alamat.setText(intent_alamat);

        }

        btnsimpanOrtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (update == 1) {
                    Update_data();
                } else {
                    simpanData();
                }
            }
        });

        btnbatalOrtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(InsertOrtu.this, HalamanOrtu.class);
                startActivity(main);
            }
        });
    }
    private void Update_data()
    {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE_ORTU,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertOrtu.this, "pesan : "+  res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertOrtu.this, HalamanOrtu.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertOrtu.this, "Pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nik",nik.getText().toString());
                map.put("nortu",nortu.getText().toString());
                map.put("pekerjaan",pekerjaan.getText().toString());
                map.put("alamat",alamat.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);
    }

    private void simpanData()
    {
        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT_ORTU,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertOrtu.this, "pesan : "+  res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertOrtu.this,HalamanOrtu.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertOrtu.this, "Pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nik",nik.getText().toString());
                map.put("nortu",nortu.getText().toString());
                map.put("pekerjaan",pekerjaan.getText().toString());
                map.put("alamat",alamat.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}