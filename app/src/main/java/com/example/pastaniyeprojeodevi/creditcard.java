package com.example.pastaniyeprojeodevi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class creditcard extends AppCompatActivity {
    String odemeyontemi, aciklamametni, kuladi;
    Double toplamtutar;
    Spinner month, year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creditcard);

        //önceki sayfadan gelen bilgiler
        toplamtutar = getIntent().getExtras().getDouble("Sipariştutarı");
        odemeyontemi = getIntent().getExtras().getString("odemeyontemi");
        aciklamametni =  getIntent().getExtras().getString("aciklama");
        kuladi =  getIntent().getExtras().getString("kuladi");
    }
    public void paycreditcard(View view){
        Intent gonder = new Intent(getApplicationContext(), completeorder.class);
        gonder.putExtra("odemeyontemi","Kredi Kartı");
        gonder.putExtra("aciklama",aciklamametni);
        gonder.putExtra("Sipariştutarı",toplamtutar);
        gonder.putExtra("kuladi",kuladi);
        startActivity(gonder);
    }
}