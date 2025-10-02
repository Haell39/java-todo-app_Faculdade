package com.example.myapp;

import android.util.Log;

public interface Notificacao {
    void enviarMensagem(String destino, String msg);
}

public class EmailService implements Notificacao {
    @Override
    public void enviarMensagem(String destino, String msg) {
        Log.d("DEBUG", "EMAIL enviado para " + destino + ": " + msg);
    }
}

