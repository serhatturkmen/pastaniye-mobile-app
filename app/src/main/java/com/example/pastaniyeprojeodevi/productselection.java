package com.example.pastaniyeprojeodevi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class productselection extends AppCompatActivity {
    //gerekli değişkenlerin tanımlanması
    String secilenrbdegeri, secilensekil, kuladi, secilenpastaboyutu, secilenpastasusu="";
    Double secilenfiyat, pastasusufiyati=0.0, secilenpastaboyutufiyati=0.0;
    Button siparisgonder;
    RadioGroup rG;
    RadioButton rB;
    CheckBox cb_sos, cb_gboyalari, cb_plastik_suslemeler,cb_sfigurler,cb_yenilebilir_suslemeler,cb_shamurlari;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productselection);
        //önceki sayfadan gelen verilerin değişkenlere aktarılması
        secilensekil = getIntent().getExtras().getString("şekil");
        secilenfiyat = getIntent().getExtras().getDouble("fiyat");
        kuladi = getIntent().getExtras().getString("kuladi");
        //sayfa içinde girilmesi istenen componentlerin sayfa oluşturulurken tanımlanması
        cb_sos = findViewById(R.id.cb_sos);
        cb_gboyalari = findViewById(R.id.cb_gboyalari);
        cb_plastik_suslemeler = findViewById(R.id.cb_plastik_suslemeler);
        cb_sfigurler = findViewById(R.id.cb_sfigurler);
        cb_yenilebilir_suslemeler = findViewById(R.id.cb_yenilebilir_suslemeler);
        cb_shamurlari = findViewById(R.id.cb_shamurlari);
        rG = (RadioGroup) findViewById(R.id.pastaboyut);
        //siparişi gnder butonu tanımlanması
        siparisgonder = (Button) findViewById(R.id.btn_nextorder);
        //siparişi gönder butonuna tıklandığında  çalışacak fonksiyon
        siparisgonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //seçili radio buttonun idsini alma
                int selectedId = rG.getCheckedRadioButtonId();
                //secili radiobuttonu tanımlama
                rB = (RadioButton) findViewById(selectedId);
                //seçili radiobuttonun metnini alma
                secilenrbdegeri = rB.getText().toString();
                //metine koşul sağlama
                if(secilenrbdegeri.equals("Küçük")){
                    secilenpastaboyutu = "Küçük";
                    Toast.makeText(getApplicationContext(),"Seçilen pasta boyutu: " + secilenrbdegeri,Toast.LENGTH_LONG).show();
                    secilenpastaboyutufiyati += 12.5;
                }
                if(secilenrbdegeri.equals("Orta")) {
                    secilenpastaboyutu = "Orta";
                    Toast.makeText(getApplicationContext(),"Seçilen pasta boyutu: " + secilenrbdegeri,Toast.LENGTH_LONG).show();
                    secilenpastaboyutufiyati += 15.0;
                }
                if(secilenrbdegeri.equals("Büyük")){
                    secilenpastaboyutu = "Büyük";
                   Toast.makeText(getApplicationContext(),"Seçilen pasta boyutu: " + secilenrbdegeri,Toast.LENGTH_LONG).show();
                    secilenpastaboyutufiyati += 17.5;
                }
                //checkboxların tıklanmış olma durumunu kontrol etme
                if(cb_sos.isChecked() == true){
                    secilenpastasusu += "sos ";
                    pastasusufiyati += 2.5;
                }
                if(cb_gboyalari.isChecked() == true){
                    secilenpastasusu +="gıda boyaları ";
                    pastasusufiyati += 2.5;
                }
                if(cb_plastik_suslemeler.isChecked() == true){
                    secilenpastasusu +="plastik süslemeler ";
                    pastasusufiyati += 2.5;
                }
                if(cb_sfigurler.isChecked() == true){
                    secilenpastasusu +="pasta üstü süs figürleri ";
                    pastasusufiyati += 2.5;
                }
                if(cb_shamurlari.isChecked() == true){
                    secilenpastasusu +="şeker hamurları ";
                    pastasusufiyati += 2.5;
                }
                if(cb_yenilebilir_suslemeler.isChecked() == true){
                    secilenpastasusu +="yenilebilir süslemeler ";
                    pastasusufiyati += 2.5;
                }
                //sipariş toplamını hesaplama
                double toplam = secilenpastaboyutufiyati+pastasusufiyati+secilenfiyat;

                //sipariş detayını çalışma paneline yazdırma
                System.out.println("Sipariş detayı");
                System.out.println("Pasta şekli "+ secilensekil + " Pasta şekli fiyati "+ secilenfiyat);
                System.out.println("Pasta boyu "+ secilenpastaboyutu + " Pasta boyu fiyati "+ secilenpastaboyutufiyati);
                System.out.println("Pasta süslemeleri "+ secilenpastasusu+ " pasta süsü fiyatı " + pastasusufiyati);
                System.out.println("Sipariş tutarı " + toplam );
                System.out.println("Kullanıcı adı " + kuladi );
                //verileri alıp ödeme sayfasına yönlendirme
                Intent gonder = new Intent(getApplicationContext(), payment.class);
                gonder.putExtra("şekil",secilensekil);
                gonder.putExtra("şekilfiyatı",secilenfiyat);
                gonder.putExtra("boyu",secilenpastaboyutu);
                gonder.putExtra("boyufiyatı",secilenpastaboyutufiyati);
                gonder.putExtra("süslemeler",secilenpastasusu);
                gonder.putExtra("süsfiyatı",pastasusufiyati);
                gonder.putExtra("kuladi",kuladi);
                gonder.putExtra("Sipariştutarı",toplam);
                startActivity(gonder);
            }
        });
    }
}
