package com.github.rtoshiro.util.format.text;

import android.widget.TextView;

import com.github.rtoshiro.util.format.MaskFormatter;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;

/**
 * Created by Tox on 1/11/16.
 */
public class SimpleMaskTextWatcher extends MaskTextWatcher {

    public SimpleMaskTextWatcher(TextView textView, MaskFormatter formatter) {
        super(textView, formatter);
    }

    public SimpleMaskTextWatcher(TextView textView, String mask) {
        super(textView, new SimpleMaskFormatter(mask));
    }
}
