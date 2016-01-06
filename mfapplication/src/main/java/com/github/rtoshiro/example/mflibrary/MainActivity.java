package com.github.rtoshiro.example.mflibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.github.rtoshiro.mflibrary.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class MainActivity extends AppCompatActivity {

    private android.widget.EditText edit1;
    private android.widget.EditText edit2;
    private android.widget.EditText edit3;
    private android.widget.EditText edit4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.edit4 = (EditText) findViewById(R.id.edit_4);
        this.edit3 = (EditText) findViewById(R.id.edit_3);
        this.edit2 = (EditText) findViewById(R.id.edit_2);
        this.edit1 = (EditText) findViewById(R.id.edit_1);

        edit1.addTextChangedListener(new MaskTextWatcher(edit1, new SimpleMaskFormatter("(NN) NNNN-NNNN")));
        edit2.addTextChangedListener(new MaskTextWatcher(edit2, new SimpleMaskFormatter("(NN) NNNN-NNNN")));
        edit3.addTextChangedListener(new MaskTextWatcher(edit3, new SimpleMaskFormatter("(NN) NNNN-NNNN")));
        edit4.addTextChangedListener(new MaskTextWatcher(edit4, new SimpleMaskFormatter("(NN) NNNN-NNNN")));
    }
}
