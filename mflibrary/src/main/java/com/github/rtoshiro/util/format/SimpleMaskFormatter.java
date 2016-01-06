package com.github.rtoshiro.util.format;

import com.github.rtoshiro.util.format.pattern.MaskPattern;

/**
 * Created by Tox on 12/8/15.
 */
public class SimpleMaskFormatter extends MaskFormatter {

    public static class SimpleMaskCharacter {
        public static final String NUMBER = "N";
        public static final String LETTER = "L";
        public static final String ALPHANUMERIC = "A";
        public static final String LOWERCASE = "l";
        public static final String UPPERCASE = "U";
    }

    public SimpleMaskFormatter(String mask) {
        super(mask);

        registerPattern(SimpleMaskCharacter.NUMBER, new MaskPattern("\\p{Digit}"));
        registerPattern(SimpleMaskCharacter.LETTER, new MaskPattern("\\p{Alpha}"));
        registerPattern(SimpleMaskCharacter.ALPHANUMERIC, new MaskPattern("\\p{Alnum}"));
        registerPattern(SimpleMaskCharacter.LOWERCASE, new MaskPattern("\\p{Lower}"));
        registerPattern(SimpleMaskCharacter.UPPERCASE, new MaskPattern("\\p{Upper}"));
    }
}
