package com.example.pastaniyeprojeodevi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {
    private EditText username;
    private EditText  pass;
    private EditText adres;
    private EditText email;
    private EditText telefon;
    private EditText namesurname;
    DatabaseHelperUser nDatabaseHelperUser;
    String kadi, sifre, adress, eposta, Telefon, adsoyad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        //sayfa açılında edittextleri tanımlama
        adres = findViewById(R.id.adres);
        namesurname = findViewById(R.id.namesurname);
        telefon = findViewById(R.id.telefon);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
    }
    //üye ol butonuna tıklandığında çalıştırılacak fonksiyon
    public void btn_sign_up_Click (View v) {
        //girilen değerleri çekme
        kadi = username.getText().toString();
        sifre = pass.getText().toString();
        adress = adres.getText().toString();
        eposta = email.getText().toString();
        Telefon = telefon.getText().toString();
        adsoyad = namesurname.getText().toString();
        //kullanıcı veritabanı sınıfını tanımlama
        nDatabaseHelperUser = new DatabaseHelperUser(this);
        //kullanıcıı ekleme eklenirse true hata alırsa false değerini döndürür
        boolean insertuser = nDatabaseHelperUser.addUser(kadi, sifre, Telefon, adress, eposta, adsoyad);
        if (insertuser) {
            //eklenme başarılıysa giriş sayfasına yönlendir
            Toast.makeText(getApplicationContext(), "Kayıt başarılı ", Toast.LENGTH_LONG).show();
            Intent gecisYap = new Intent(this, login.class);
            startActivity(gecisYap);
        } else {
            //eklenme başarısızsa bildirim gönder
            Toast.makeText(getApplicationContext(), "Kayıt Başarısız.", Toast.LENGTH_LONG).show();
        }
    }
    //anasayfa bytonuna tıklnadığında çalışacak fonksiyon
    public void btn_geri_Click (View v){
        //giriş sayfasına geri git
        Intent gecisYap = new Intent(this, login.class);
        startActivity(gecisYap);
    }
}