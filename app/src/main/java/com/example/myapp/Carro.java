package com.example.myapp;

import android.util.Log;

public class Carro extends Veiculo {
    public Carro(String marca) {
        super(marca);
    }

    @Override
    public void mover() {
        Log.d("DEBUG", "O carro " + marca + " está andando.");
    }
}
