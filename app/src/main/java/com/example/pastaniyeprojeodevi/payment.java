package com.example.pastaniyeprojeodevi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class payment extends AppCompatActivity {
    String pastasekil, pastaboyu, pastasusleme, kuladi;
    Double sekilfiyat, boyfiyat, suslemefiyat, siparistutari;
    TextView sekil, susler,susfiyat,pastaboyut,boyutfiyat,toptutar, tvsekilfiyat;

    RadioButton rB;
    RadioGroup rG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        //odeme methodu için radiogroup tanımlanması
        rG = (RadioGroup) findViewById(R.id.rBpaymentmethod);

        //sayfa textview tanımlamaları
        sekil = findViewById(R.id.sekil);
        pastaboyut=findViewById(R.id.pastaboyut);
        susler= findViewById(R.id.susler);

        tvsekilfiyat=findViewById(R.id.sekilfiyat);
        boyutfiyat=findViewById(R.id.boyutfiyat);
        susfiyat=findViewById(R.id.susfiyat);
        toptutar=findViewById(R.id.toptutar);

        //önceki sayfadan gelen bilgiler
        kuladi = getIntent().getExtras().getString("kuladi");

        pastasekil = getIntent().getExtras().getString("şekil");
        pastaboyu = getIntent().getExtras().getString("boyu");
        pastasusleme = getIntent().getExtras().getString("süslemeler");

        sekilfiyat = getIntent().getExtras().getDouble("şekilfiyatı");
        boyfiyat = getIntent().getExtras().getDouble("boyufiyatı");
        suslemefiyat = getIntent().getExtras().getDouble("süsfiyatı");
        siparistutari = getIntent().getExtras().getDouble("Sipariştutarı");

        //sayfa içerik doldurma
        sekil.setText(pastasekil);
        pastaboyut.setText(pastaboyu);
        susler.setText(pastasusleme);

        tvsekilfiyat.setText(sekilfiyat.toString());
        boyutfiyat.setText(boyfiyat.toString());
        susfiyat.setText(suslemefiyat.toString());
        toptutar.setText(siparistutari.toString());
    }
    //ödeme yap butonuna tıklandığında çalıştırılacak fonksiyon
    public void do_payment(View view){
        //seçili radio buttonun idsini alma
        int selectedId = rG.getCheckedRadioButtonId();
        //secili radiobuttonu tanımlama
        rB = findViewById(selectedId);
        //seçili radiobuttonun metnini alma
        String secilenrbdegeri = rB.getText().toString();
        //metine koşul sağlama
        if(secilenrbdegeri.equals("Kredi Kartı")){
            //eğer kredi kartı şeçildiyse credi kartı sayfasına yönlendir
            Intent gondercredi = new Intent(getApplicationContext(), creditcard.class);
            String aciklamametni = "Pasta şekli : "+pastasekil+
                    " Şeklin ücreti : "+sekilfiyat.toString()+
                    " Pastanın boyu : "+pastaboyu +
                    " Boyutun ücreti : "+ boyfiyat.toString()+
                    " Süslemeleri : "+pastasusleme+
                    " Süsleme ücreti : "+suslemefiyat.toString()+
                    " Toplam tutar : "+siparistutari.toString();
            gondercredi.putExtra("odemeyontemi","Kredi Kartı");
            gondercredi.putExtra("aciklama",aciklamametni);
            gondercredi.putExtra("Sipariştutarı",siparistutari);
            gondercredi.putExtra("kuladi",kuladi);
            startActivity(gondercredi);
        }else if(secilenrbdegeri.equals("Kapıda Ödeme")) {
            //eğer kredi kartı şeçildiyse sipariş tutarına 5.45 ekle sipariş tamamlama sayfasına yönlendir
            Intent gonder = new Intent(getApplicationContext(), completeorder.class);
            Double toplamtutar = siparistutari + 5.45;
            String aciklamametni = "Pasta şekli : "+pastasekil+
                    " Şeklin ücreti : "+sekilfiyat.toString()+
                    " Pastanın boyu : "+pastaboyu +
                    " Boyutun ücreti : "+ boyfiyat.toString()+
                    " Süslemeleri : "+pastasusleme+
                    " Süsleme ücreti : "+suslemefiyat.toString()+
                    " Toplam tutar : "+siparistutari;
            gonder.putExtra("odemeyontemi","Kapıda Ödeme");
            gonder.putExtra("aciklama",aciklamametni);
            gonder.putExtra("Sipariştutarı",toplamtutar);
            gonder.putExtra("kuladi",kuladi);
            startActivity(gonder);
        }
    }
}