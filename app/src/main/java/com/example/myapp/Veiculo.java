package com.example.myapp;
import android.util.Log;

public abstract class Veiculo {
    protected String marca;

    public Veiculo(String marca) {
        this.marca = marca;
    }

    public void ligarMotor() {
        Log.d("DEBUG", marca + " ligou o motor!");
    }

    public abstract void mover();
}

