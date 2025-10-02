package com.example.myapp;

import android.util.Log;

public class SMSService implements Notificacao {
    @Override
    public void enviarMensagem(String destino, String msg) {
        Log.d("DEBUG", "SMS enviado para " + destino + ": " + msg);
    }
}
