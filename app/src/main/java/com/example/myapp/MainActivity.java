package com.example.myapp; // Verifique se este é o nome do seu pacote

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextTask;
    Button buttonAddTask;
    ListView listViewTasks;

    ArrayList<String> tasksList;
    ArrayAdapter<String> tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAddTask = findViewById(R.id.buttonAddTask);
        listViewTasks = findViewById(R.id.listViewTasks);

        tasksList = new ArrayList<>();
        tasksAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                tasksList
        );

        listViewTasks.setAdapter(tasksAdapter);

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        // Adiciona os novos listeners para a lista de tarefas
        setUpListViewListener();
    }

    private void addTask() {
        String task = editTextTask.getText().toString().trim();

        if (!task.isEmpty()) {
            tasksList.add(task);
            tasksAdapter.notifyDataSetChanged();
            editTextTask.setText("");
        } else {
            Toast.makeText(this, "Por favor, digite uma tarefa.", Toast.LENGTH_SHORT).show();
        }
    }

    // NOVO MÉTODO: Configura os cliques na lista
    private void setUpListViewListener() {
        // Listener para clique curto (marcar/desmarcar como concluída)
        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView taskTextView = (TextView) view;

                // Alterna o efeito de riscado (strike-through)
                if ((taskTextView.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG) > 0) {
                    // Se já estiver riscado, remove o risco
                    taskTextView.setPaintFlags(taskTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                } else {
                    // Se não estiver riscado, adiciona o risco
                    taskTextView.setPaintFlags(taskTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        });

        // Listener para clique longo (excluir)
        listViewTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                // Cria um diálogo de confirmação para evitar exclusões acidentais
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Excluir Tarefa")
                        .setMessage("Você tem certeza que deseja excluir esta tarefa?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Remove a tarefa da lista
                                tasksList.remove(position);
                                // Notifica o adaptador sobre a mudança
                                tasksAdapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "Tarefa excluída", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Não", null) // Não faz nada se o usuário clicar em "Não"
                        .show();

                return true; // Retorna true para indicar que o evento foi consumido
            }
        });
    }
}

