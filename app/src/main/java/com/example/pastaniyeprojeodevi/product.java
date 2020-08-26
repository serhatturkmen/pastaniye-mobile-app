package com.example.pastaniyeprojeodevi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class product extends AppCompatActivity {
    String kuladi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        //önceki sayfadan gelen kullanıcı adı bilgisini alma
        kuladi =  getIntent().getExtras().getString("username");
    }
    //kare pasta butonuna tıklandığında çalışacak fonksiyon
    public void karepasta(View v){
        Intent gonder = new Intent(getApplicationContext(), productselection.class);
        gonder.putExtra("fiyat",10.90);
        gonder.putExtra("şekil","Kare Pasta");
        gonder.putExtra("kuladi",kuladi);
        startActivity(gonder);
    }
    //yuvarlak pasta butonuna tıklandığında çalışacak fonksiyon
    public void yuvarlakpasta(View v){
        Intent gonder = new Intent(getApplicationContext(), productselection.class);
        gonder.putExtra("fiyat",10.90);
        gonder.putExtra("şekil","Yuvarlak Pasta");
        gonder.putExtra("kuladi",kuladi);
        startActivity(gonder);
    }
    //üçgen pasta butonuna tıklandığında çalışacak fonksiyon
    public void ucgenpasta(View v){
        Intent gonder = new Intent(getApplicationContext(), productselection.class);
        gonder.putExtra("fiyat",12.90);
        gonder.putExtra("şekil","Üçgen Pasta");
        gonder.putExtra("kuladi",kuladi);
        startActivity(gonder);
    }
    //yıldız pasta butonuna tıklandığında çalışacak fonksiyon
    public void yildizpasta(View v){
        Intent gonder = new Intent(getApplicationContext(), productselection.class);
        gonder.putExtra("fiyat",15.90);
        gonder.putExtra("şekil","Yıldız Pasta");
        gonder.putExtra("kuladi",kuladi);
        startActivity(gonder);
    }
    //kalpli pasta butonuna tıklandığında çalışacak fonksiyon
    public void  kalplipasta(View v){
        Intent gonder = new Intent(getApplicationContext(), productselection.class);
        gonder.putExtra("fiyat",15.90);
        gonder.putExtra("şekil","Kalpli Pasta");
        gonder.putExtra("kuladi",kuladi);
        startActivity(gonder);
    }
    //top pasta butonuna tıklandığında çalışacak fonksiyon
    public void  topkek(View v){
        Intent gonder = new Intent(getApplicationContext(), productselection.class);
        gonder.putExtra("fiyat",16.90);
        gonder.putExtra("şekil","Top Şekilli Pasta");
        gonder.putExtra("kuladi",kuladi);
        startActivity(gonder);
    }
}
