package com.example.irmayantisyam.irma_1202154347_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class add extends AppCompatActivity {
    //mendeklarasikan semua variable yang digunakan
    EditText nama, desc, prio;
    dbase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //membuat inisiasi pada masing-masing variabel
        nama = findViewById(R.id.todoname);
        desc = findViewById(R.id.description);
        prio = findViewById(R.id.priority);
        db = new dbase(this);
    }
    
    @Override
    public void onBackPressed() { //method ketika tombol back diklik
        Intent a = new Intent(add.this,halawal.class); //melakukan perpindahan halaman
        startActivity(a); //memulai intent
        this.finish(); //mengakhiri atau mematikan intent
    }
    public void tambah (View view){ //method ketika button tambah todo ditekan
        if (nama.getText().toString().length()==0){ //kondisi yang mengecek nama kegiatan sudah dimasukkan atau belum
            nama.setError("Harap masukkan nama kegiatan"); //mengatur pesan error agar field tidak kosong
            Toast.makeText(this, "Adding todo failed", Toast.LENGTH_SHORT).show(); //membuat pesan toast bahwa input todo gagal
            //menampilkan pesan toast ketika tidak berhasil menginputkan todo
        } else if (prio.getText().toString().length()==0){ //kondisi yang mengecek nomor prioritas sudah dimasukkan atau belum
            prio.setError("Masukkan nomor prioritas"); //mengatur pesan error agar field terisi
            Toast.makeText(this, "Adding todo failed", Toast.LENGTH_SHORT).show(); //menampilkan pesan toast bahwa input todo gagal
        }
        else if
            (db.insertdata(new itemtodo(nama.getText().toString(), desc.getText().toString(), prio.getText().toString())))
            {
                //membuat kondisi yang mengecek database pada kolom nama, deskripsi, dan prioritas diinputkan
                Toast.makeText(this, "Todo added", Toast.LENGTH_SHORT).show();
                //menampilkan pesan toast ketika berhasil diinputkan
                startActivity(new Intent(add.this, halawal.class));
                //memulai aktivitas intent
                this.finish();
                //mengakhiri atau mematikan intent
            }
    }
}
