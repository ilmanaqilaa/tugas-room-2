package com.denjand.tugasroom.crud.kasir;

import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.denjand.tugasroom.MainActivity;
import com.denjand.tugasroom.R;
import com.denjand.tugasroom.database.DatabaseClient;
import com.denjand.tugasroom.database.model.Kasir;

public class UpdateKasirActivity extends AppCompatActivity {

    private EditText editTextNama, editTextEmail, editTextJnsKelamin, editTextNoHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kasir);

        editTextNama = findViewById(R.id.tie_nama_kasir_tp);
        editTextEmail = findViewById(R.id.tie_email_tp);
        editTextJnsKelamin = findViewById(R.id.tie_jns_kelamin_tp);
        editTextNoHP = findViewById(R.id.tie_nohp_tp);

        final Kasir kasir = (Kasir) getIntent().getSerializableExtra("kasir");

        loadKasir(kasir);

        findViewById(R.id.btn_simpan_kasir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                updateTask(kasir);
            }
        });

        findViewById(R.id.btn_hapus_kasir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(com.denjand.tugasroom.crud.kasir.UpdateKasirActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteTask(kasir);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });
    }

    private void loadKasir(Kasir kasir) {
        editTextNama.setText(kasir.getNama());
        editTextEmail.setText(kasir.getEmail());
        editTextJnsKelamin.setText(kasir.getJns_Kelamin());
        editTextNoHP.setText(kasir.getNoHP());

    }

    private void updateTask(final Kasir kasir) {
        final String sNama = editTextNama.getText().toString().trim();
        final String sEmail = editTextEmail.getText().toString().trim();
        final String sJnsKelamin = editTextJnsKelamin.getText().toString().trim();
        final String sNoHP = editTextNoHP.getText().toString().trim();

        if (sNama.isEmpty()) {
            editTextNama.setError("Nama required");
            editTextNama.requestFocus();
            return;
        }

        if (sEmail.isEmpty()) {
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            return;
        }

        if (sJnsKelamin.isEmpty()) {
            editTextJnsKelamin.setError("Jenis Kelamin required");
            editTextJnsKelamin.requestFocus();
            return;
        }

        if (sNoHP.isEmpty()) {
            editTextNoHP.setError("No HP required");
            editTextNoHP.requestFocus();
            return;
        }

        class UpdateKasir extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                kasir.setNama(sNama);
                kasir.setEmail(sEmail);
                kasir.setJns_Kelamin(sJnsKelamin);
                kasir.setNoHP(sNoHP);
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .kasirDao()
                        .update(kasir);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(com.denjand.tugasroom.crud.kasir.UpdateKasirActivity.this, MainActivity.class));
            }
        }

        UpdateKasir up = new UpdateKasir();
        up.execute();
    }


    private void deleteTask(final Kasir kasir) {
        class DeleteKasir extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .kasirDao()
                        .delete(kasir);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(com.denjand.tugasroom.crud.kasir.UpdateKasirActivity.this, MainActivity.class));
            }
        }

        DeleteKasir dt = new DeleteKasir();
        dt.execute();

    }

}