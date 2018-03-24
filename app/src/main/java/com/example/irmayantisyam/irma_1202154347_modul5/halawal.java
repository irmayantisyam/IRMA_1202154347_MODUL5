package com.example.irmayantisyam.irma_1202154347_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class halawal extends AppCompatActivity {
    //mendeklarasikan semua variabel yang digunakan
    dbase db;
    RecyclerView review;
    adapt adapter;
    ArrayList<itemtodo> listnya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halawal);

        //menginsiasi semua variabel
        review = findViewById(R.id.listtodo);
        listnya = new ArrayList<>();
        db = new dbase(this);
        db.getAllItems(listnya);

        //mendeklarasikan varaibel untuk type sharedpreferences yang digunakan untuk menyimpan data kecil
        SharedPreferences pref = this.getApplicationContext().getSharedPreferences("pref", 0);
        //mengatur warna background awal
        int warna = pref.getInt("background", R.color.putih);

        //menentukan adapter untuk recycler view
        adapter = new adapt(this, listnya, warna);
        review.setHasFixedSize(true); //mengatur ukurannya
        review.setLayoutManager(new LinearLayoutManager(this)); //mengatur layout manager
        review.setAdapter(adapter);//mengatur adapternya
        initswipe(); //memanggil method initswipe
    }
    public void initswipe(){
        //Method untuk menambhkan ItemTouchHelper pada RecyclerView
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            //mengatur perpindahan recyclerview
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            //Method ketika recyclerview digeser
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                itemtodo cur = adapter.getItem(pos);

                if(direction==ItemTouchHelper.LEFT||direction==ItemTouchHelper.RIGHT){ //membuat pengkondisian
                    if(db.deletedata(cur.getName())){//menghapus data dari database ketika di swipe
                        adapter.removeitem(pos); //menghapus data
                        Snackbar.make(findViewById(R.id.roothome), "Item deleted", 1500).show();
                        //menjalankan fungsi snackbar dari id coordinator layout dan menampilkan pesan itemdeleted
                    }
                }
            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        //memanggil variabel callback yang disimpan pada method itemtouchhelper
        helper.attachToRecyclerView(review);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Method ketika item pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //menghandle action bar secara otomatis ketika item diklik
        int id = item.getItemId(); //mendapatkan id
        if(id==R.id.menusetting){ //membuat pengkondisian saat menu diklik
            startActivity(new Intent(halawal.this, Setting.class)); //melakukan intent
            finish();// mengakhiri intent
        }
        return true;
    }

    //Method untuk berpindah ke activity adding (ketika float action button di klik)
    public void tambahin(View view) {
        startActivity(new Intent(halawal.this, add.class));//membuat dan memulai intent
        finish();//mengakhiri intent
    }
}
