package com.example.myapp;

import android.util.Log;

public class ExcecaoDemo {

    public int dividir(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            Log.e("ERRO", "Divisão por zero detectada!");
            return 0;
        } finally {
            Log.d("DEBUG", "Fim da divisão.");
        }
    }
}

