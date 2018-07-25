package com.example.user_pc.tbcrudsql.pembayaran;

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

public class InsertPembayaran extends AppCompatActivity {

    EditText nisn,nsiswa,pilprodi,piljurusan,jumlah;
    Button btnbatalPbyr,btnsimpanPbyr;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_pembayaran);

        /*get data from intent*/

        Intent data = getIntent();
        final int update = data.getIntExtra("update", 0);
        String intent_nisn= data.getStringExtra("nisn");
        String intent_nsiswa = data.getStringExtra("nsiswa");
        String intent_pilprodi = data.getStringExtra("pilprodi");
        String intent_piljurusan = data.getStringExtra("piljurusan");
        String intent_jumlah = data.getStringExtra("jumlah");
        /*end get data from intent*/

        nisn = (EditText) findViewById(R.id.inp_nisn);
        nsiswa = (EditText) findViewById(R.id.inp_nsiswa);
        pilprodi = (EditText) findViewById(R.id.inp_pilprodi);
        piljurusan = (EditText) findViewById(R.id.inp_piljurusan);
        jumlah = (EditText) findViewById(R.id.inp_jumlah);
        btnbatalPbyr = (Button) findViewById(R.id.btn_cancelPbyr);
        btnsimpanPbyr = (Button) findViewById(R.id.btn_simpanPbyr);
        pd = new ProgressDialog(InsertPembayaran.this);

        /*kondisi update / insert*/
        if (update == 1) {
            btnsimpanPbyr.setText("update Data");
            nisn.setText(intent_nisn);
            nisn.setVisibility(View.GONE);
            nsiswa.setText(intent_nsiswa);
            pilprodi.setText(intent_pilprodi);
            piljurusan.setText(intent_piljurusan);
            jumlah.setText(intent_jumlah);

        }

        btnsimpanPbyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (update == 1) {
                    Update_data();
                } else {
                    simpanData();
                }
            }
        });

        btnbatalPbyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(InsertPembayaran.this, HalamanPembayaran.class);
                startActivity(main);
            }
        });
    }
    private void Update_data()
    {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE_PBYR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertPembayaran.this, "pesan : "+  res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertPembayaran.this,HalamanPembayaran.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertPembayaran.this, "Pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nisn",nisn.getText().toString());
                map.put("nsiswa",nsiswa.getText().toString());
                map.put("pilprodi",pilprodi.getText().toString());
                map.put("piljurusan",piljurusan.getText().toString());
                map.put("jumlah",jumlah.getText().toString());

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

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT_PBYR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertPembayaran.this, "pesan : "+  res.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertPembayaran.this,HalamanPembayaran.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertPembayaran.this, "Pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nisn",nisn.getText().toString());
                map.put("nsiswa",nsiswa.getText().toString());
                map.put("pilprodi",pilprodi.getText().toString());
                map.put("piljurusan",piljurusan.getText().toString());
                map.put("jumlah",jumlah.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}