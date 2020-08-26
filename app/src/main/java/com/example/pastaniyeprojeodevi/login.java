package com.example.pastaniyeprojeodevi;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    private EditText username;
    private EditText  pass;
    DatabaseHelperUser mDatabaseHelperUser;
    String kadi;
    String sifre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //veritabanı sınıfını çağırma
        mDatabaseHelperUser = new DatabaseHelperUser(this);
        //veritabanı yoksa oluşturma
        mDatabaseHelperUser.CreateDataBase();
        setContentView(R.layout.login);
        //girilen kullanıcı adı ve parola bilgilerini almak için tanımlamalar
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
    }
    //giriş butonu tıklanma olduğunda çalışacak fonksiyon
    public void btn_login_Click (View v){
        //kullanıcı adı ve parola bilgisini değişkene aktarma
        kadi = username.getText().toString();
        sifre = pass.getText().toString();
        //veritabanında kullanıcı varlığını sorgulama geriye true veya false dönrürür
        boolean loginstatus = mDatabaseHelperUser.logintrust(kadi,sifre);

        if (loginstatus){
            //kullanıcı varlığı veritabanında kayıtlıysa
            //kullanıcı adından idsine erişme
            int id = mDatabaseHelperUser.getItemID(kadi);
            //idsinden adsoyad bilgisine erişme
            String adsoyad = mDatabaseHelperUser.getAdSoyad(id);
            //bildirim yayınlama
            Toast.makeText(getApplicationContext(),"Hoşgeldiniz " + adsoyad,Toast.LENGTH_LONG).show();
            //ürünler sayfasına yönlendirme
            Intent gonder = new Intent(getApplicationContext(), product.class);
            //ürünler sayfasına username adında değişkene değer olarak kullanıcı adını aktarıp gönderme
            gonder.putExtra("username",kadi);
            //sayfayı açma
            startActivity(gonder);
        }else{
            //kullanıcı varlığı veritabanında kayıtlı değilse bir bildirim verir
            Toast.makeText(getApplicationContext(),"Kullanıcı adı veya parola yanlış.",Toast.LENGTH_LONG).show();
        }
    }
    //üye ol butonuna tıklandığında çalışacak fonksiyon
    public void btn_sign_up (View v){
        //üye ol sayfasına yönlendirme
        Intent gecisYap = new Intent(this, signup.class);
        //sayfayı açma
        startActivity(gecisYap);
    }
}