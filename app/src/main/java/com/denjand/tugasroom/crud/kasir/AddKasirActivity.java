package com.denjand.tugasroom.crud.kasir;

import android.content.Intent;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denjand.tugasroom.MainActivity;
import com.denjand.tugasroom.R;
import com.denjand.tugasroom.database.DatabaseClient;
import com.denjand.tugasroom.database.model.Kasir;

public class AddKasirActivity extends AppCompatActivity {

    private EditText editTextNama, editTextEmail, editTextJnsKelamin, editTextNoHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kasir);

        editTextNama = findViewById(R.id.tie_nama_kasir_tp);
        editTextEmail = findViewById(R.id.tie_email_tp);
        editTextJnsKelamin = findViewById(R.id.tie_jns_kelamin_tp);
        editTextNoHP = findViewById(R.id.tie_nohp_tp);

        findViewById(R.id.btn_simpan_kasir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        final String sNama = editTextNama.getText().toString().trim();
        final String sEmail = editTextEmail.getText().toString().trim();
        final String sJnsKelamin = editTextJnsKelamin.getText().toString().trim();
        final String sNoHP = editTextNoHP.getText().toString().trim();

        if (sNama.isEmpty()) {
            editTextNama.setError("Name required");
            editTextNama.requestFocus();
            return;
        }

        if (sEmail.isEmpty()) {
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            return;
        }

        if (sJnsKelamin.isEmpty()) {
            editTextJnsKelamin.setError("JnsKelamin required");
            editTextJnsKelamin.requestFocus();
            return;
        }

        if (sNoHP.isEmpty()) {
            editTextNoHP.setError("NoHP required");
            editTextNoHP.requestFocus();
            return;
        }

        class SaveKasir extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Kasir kasir = new Kasir();
                kasir.setNama(sNama);
                kasir.setEmail(sEmail);
                kasir.setJns_Kelamin(sJnsKelamin);
                kasir.setNoHP(sNoHP);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .kasirDao()
                        .insert(kasir);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveKasir st = new SaveKasir();
        st.execute();
    }

}