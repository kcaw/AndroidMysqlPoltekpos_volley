package com.example.user_pc.tbcrudsql.pembayaran;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class DeletePembayaran extends AppCompatActivity {

    EditText deletePembayaranID;
    Button btnDeletePbyr;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_pembayaran);

        deletePembayaranID = (EditText) findViewById(R.id.nisn_param);
        btnDeletePbyr = (Button) findViewById(R.id.btn_deletePbyr);
        pd = new ProgressDialog(DeletePembayaran.this);

        btnDeletePbyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete hare action
                deleteData();
            }
        });
    }
    private void deleteData()
    {
        pd.setMessage("Delete Data...");
        pd.setCancelable(false);
        pd.show();

        StringRequest delReq = new StringRequest(Request.Method.POST, ServerAPI.URL_DELETE_PBYR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(DeletePembayaran.this, "pesam : " +res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        startActivity(new Intent(DeletePembayaran.this,HalamanPembayaran.class));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                        Toast.makeText(DeletePembayaran.this, "pesan : gagal menghapus data", Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("nisn",deletePembayaranID.getText().toString());
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(delReq);
    }
}