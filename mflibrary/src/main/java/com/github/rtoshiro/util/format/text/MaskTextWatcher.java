package com.github.rtoshiro.util.format.text;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.github.rtoshiro.util.format.MaskFormatter;

/**
 * Created by Tox on 12/9/15.
 */
public class MaskTextWatcher implements TextWatcher {

    protected MaskFormatter formatter;

    /**
     * A reference of watched TextView (or EditText)
     */
    protected TextView textView;

    protected String currentText;

    public MaskTextWatcher(TextView textView, MaskFormatter formatter) {
        this.textView = textView;
        this.formatter = formatter;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (!charSequence.toString().equals(currentText)) {
            currentText = formatter.format(charSequence.toString());
            textView.setText(currentText);
            if (textView instanceof EditText) {
                EditText editText = (EditText) textView;
                editText.setSelection(currentText.length());
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
