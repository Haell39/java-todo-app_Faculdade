package com.example.myapp;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResultado = findViewById(R.id.textView);
        Button btn = findViewById(R.id.btnTestar);

        btn.setOnClickListener(v -> rodarRevisao());
    }

    private void rodarRevisao() {
        StringBuilder sb = new StringBuilder();

        // 1. Classe básica
        Pessoa p = new Pessoa("Rafael", 22);
        sb.append("Pessoa: ").append(p.exibirDados()).append("\n");

        // 2. Classe abstrata e herança
        Carro c = new Carro("Ford");
        c.ligarMotor();
        c.mover();
        sb.append("Carro testado (veja Logcat).\n");

        // 3. Interface + múltipla implementação
        Notificacao notif = new EmailService();
        notif.enviarMensagem("teste@email.com", "Bem-vindo!");
        notif = new SMSService();
        notif.enviarMensagem("11999999999", "Login detectado.");
        sb.append("Notificações testadas (veja Logcat).\n");

        // 4. Tratamento de exceção
        ExcecaoDemo ex = new ExcecaoDemo();
        int resultado = ex.dividir(10, 0);
        sb.append("Resultado da divisão: ").append(resultado).append("\n");

        // Exibir tudo no TextView
        tvResultado.setText(sb.toString());
    }
}
