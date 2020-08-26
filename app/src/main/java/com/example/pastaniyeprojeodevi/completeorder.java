package com.example.pastaniyeprojeodevi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class completeorder extends AppCompatActivity {
    String odemeyontemi, aciklamametni,kuladi;
    Double toplamtutar;
    TextView aciklama;
    DatabaseHelperOrder orderdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completeorder);

        //önceki sayfadan gelen bilgiler
        toplamtutar = getIntent().getExtras().getDouble("Sipariştutarı");
        odemeyontemi = getIntent().getExtras().getString("odemeyontemi");
        aciklamametni =  getIntent().getExtras().getString("aciklama");
        kuladi =  getIntent().getExtras().getString("kuladi");

        //güncel tarihi al

        //bilgileri doldurma
        aciklama = findViewById(R.id.aciklama);
        aciklama.setText("Sipariş tutarınız "+toplamtutar.toString()+" TL 'dir. Sipariş tarihi 02.06.2020 'dir.");

        //veritabanı kaydetme :D
        orderdb = new DatabaseHelperOrder(this);
        orderdb.addOrder(aciklamametni,odemeyontemi,toplamtutar.toString(),kuladi);
        //Anasayfa dönüş
    }
    public void go_home(View view){
        Intent gecisYap = new Intent(this, login.class);
        startActivity(gecisYap);
    }
}