package com.example.jokempo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView esquerda;
    ImageView direita;
    ImageButton pedra;
    ImageButton papel;
    ImageButton tesoura;
    Animation some;
    Animation aparece;
    int jogada1 = 0;
    int jogada2 = 0;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        esquerda = findViewById(R.id.esquerda);
        direita = findViewById(R.id.direita);
        pedra = findViewById(R.id.pedra);
        papel = findViewById(R.id.papel);
        tesoura = findViewById(R.id.tesoura);
        some = new AlphaAnimation(1,0);
        aparece = new AlphaAnimation(0,1);
        some.setDuration(1500);
        aparece.setDuration(100);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.alex_play);

    }

    public void clicouBotao(View view){
        tocaSom();
        esquerda.setScaleX(-1);
        switch(view.getId()){
            case(R.id.pedra):esquerda.setImageResource(R.drawable.pedra);
            jogada1 = 1;
            break;
            case(R.id.papel):esquerda.setImageResource(R.drawable.papel);
            jogada1 = 2;
            break;
            case(R.id.tesoura):esquerda.setImageResource(R.drawable.tesoura);
            jogada1 = 3;
            break;
        }
        direita.setImageResource(R.drawable.interrogacao);
        direita.startAnimation(some);

        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                direita.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                direita.setVisibility(View.INVISIBLE);
                direita.startAnimation(aparece);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                sorteiaJogada();
                direita.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                verificaJogada();
                direita.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public void sorteiaJogada(){
        Random r = new Random();
        int numero = r.nextInt(3);
        switch (numero){
            case 0:direita.setImageResource(R.drawable.pedra);
            jogada2 = 1;
            break;
            case 1:direita.setImageResource(R.drawable.papel);
            jogada2 = 2;
            break;
            case 2:direita.setImageResource(R.drawable.tesoura);
            jogada2 = 3;
            break;

        }
    }
    public void verificaJogada(){
        if(jogada1 == jogada2){
            Toast.makeText(this,"Empate!", Toast.LENGTH_SHORT).show();
        }
        if((jogada1 == 1 && jogada2 == 2)||(jogada1 == 2 && jogada2 == 3)||(jogada1 == 3 && jogada2 == 1)){
            Toast.makeText(this,"Perdeu!", Toast.LENGTH_SHORT).show();
        }
        if((jogada1 == 1 && jogada2 == 3)||(jogada1 == 2 && jogada2 == 1)||(jogada1 == 3 && jogada2 == 2)){
            Toast.makeText(this,"Ganhou!", Toast.LENGTH_SHORT).show();
        }
    }
    public void tocaSom(){
        if(mediaPlayer != null){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.release();
        }
    }
}
