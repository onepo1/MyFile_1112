package com.example.edu.mymulitmedia_1112;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextInput;
    Button btn_read;
    Button btn_write;

    FileInputStream fileInputStream = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = (EditText)findViewById(R.id.editTextInput);
        btn_read = (Button)findViewById(R.id.btn_read);
        btn_read.setOnClickListener(this);
        btn_write = (Button)findViewById(R.id.btn_write);
        btn_write.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        switch (v.getId()){
            case R.id.btn_read:
                try {
                    fileInputStream = openFileInput("storeFile.txt");
                    byte[] buffer = new byte[fileInputStream.available()];
                    fileInputStream.read(buffer);
                    editTextInput.setText(new String(buffer));
                    fileInputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btn_write:
                try {
                    fileOutputStream = openFileOutput("storeFile.txt", Context.MODE_PRIVATE);
                    fileOutputStream.write(editTextInput.getText().toString().getBytes());
                    editTextInput.setText("");
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
