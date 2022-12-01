package com.example.pr23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        View view = findViewById(R.id.list_layout);
        View scrollview = findViewById(R.id.scrollview);
        view.setOnTouchListener(new OnSwipeTouchListener(ListActivity.this) {
            @Override
            public void onSwipeRight() {
                Intent i = new Intent(ListActivity.this, MainActivity.class);
                startActivity(i);
            }

        });
        scrollview.setOnTouchListener(new OnSwipeTouchListener(ListActivity.this) {
            @Override
            public void onSwipeRight() {
                Intent i = new Intent(ListActivity.this, MainActivity.class);
                startActivity(i);
            }

        });

    }
    public void onClick(View view) {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, type TEXT, UNIQUE(name))");
        db.execSQL("INSERT OR IGNORE INTO users VALUES ('Пенициллин', 'Антибиотик'), ('Ниацин', 'Витамин'), ('БЦЖ', 'Вакцина'), ('Туберкулин', 'Диагностический препарат'), ('Рифампицин','Антибиотик'),('Амоксициллин','Антибиотик'),('Ципрофлоксаци́н','Антибиотик');");
        Cursor query = db.rawQuery("SELECT * FROM users;", null);
        TextView textView = findViewById(R.id.tv_db);
        textView.setText("");
        while (query.moveToNext()) {
            String name = query.getString(0);
            String type = query.getString(1);
            textView.append("Название: " + name + ",\nТип: " + type + "\n");
        }
        query.close();
        db.close();
    }
}