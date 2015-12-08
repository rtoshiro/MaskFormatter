package com.github.rtoshiro.mflibrary;

import com.github.rtoshiro.mflibrary.maskcharacter.AlphaNumMaskChar;
import com.github.rtoshiro.mflibrary.maskcharacter.LetterMaskChar;
import com.github.rtoshiro.mflibrary.maskcharacter.LowerCaseMaskChar;
import com.github.rtoshiro.mflibrary.maskcharacter.MaskChar;
import com.github.rtoshiro.mflibrary.maskcharacter.NumberMaskChar;
import com.github.rtoshiro.mflibrary.maskcharacter.UpperCaseMaskChar;

/**
 * Created by Tox on 12/8/15.
 */
public class MaskFormatter {

    public class MaskCharType {
        public static final char NUMBER = 'N';
        public static final char LETTER = 'L';
        public static final char ALPHANUMERIC = 'A';
        public static final char LOWERCASE = 'l';
        public static final char UPPERCASE = 'U';
        public static final char ANY = '*';
    }

    private String mask;

    public MaskFormatter() {
        super();
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    /**
     * Format a string without non-mask character to
     * masked string
     *
     * @param newText String without non-mask characters
     * @return Masked string
     */
    public String format(String newText) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < mask.length(); i++) {
            char maskChar = mask.charAt(i);
            MaskChar maskObject = MaskFormatter.maskObject(maskChar);
            if (maskObject != null) {
                // Find the next valid character inside newText
                int nextIndex = indexOfValidChar(maskObject, newText);

                if (nextIndex >= 0 && nextIndex < newText.length()) {
                    // Get the valid character
                    char nextChar = newText.charAt(nextIndex);
                    // Removes everything else before nextChar
                    newText = newText.substring(nextIndex + 1);

                    result.append(nextChar);

                    if (newText.length() == 0)
                        return result.toString();
                }
            } else
                result.append(maskChar);
        }

        return result.toString();
    }

    protected int indexOfValidChar(MaskChar maskObject, String text) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (maskObject.isValid(c))
                return i;
        }
        return -1;
    }

    protected static MaskChar maskObject(char c) {
        switch (c) {
            case MaskCharType.NUMBER: {
                return new NumberMaskChar();
            }
            case MaskCharType.LETTER: {
                return new LetterMaskChar();
            }
            case MaskCharType.ALPHANUMERIC: {
                return new AlphaNumMaskChar();
            }
            case MaskCharType.LOWERCASE: {
                return new LowerCaseMaskChar();
            }
            case MaskCharType.UPPERCASE: {
                return new UpperCaseMaskChar();
            }
            case MaskCharType.ANY: {
                return new MaskChar();
            }
        }
        return null;
    }
}
