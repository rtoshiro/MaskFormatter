package com.github.rtoshiro.util.format;

import com.github.rtoshiro.util.format.pattern.MaskPattern;

import java.util.HashMap;

/**
 * Created by Tox on 12/8/15.
 */
public class MaskFormatter {

    /**
     * String pattern that represents the mask used by {@link #format(String)} method
     * All mask characters should have been registered with registerPattern
     */
    protected String mask;

    /**
     * Used to register all available patterns
     * The Key is used to map all available characters mask
     */
    protected HashMap<String, MaskPattern> patternMap;

    /**
     * Constructor
     */
    public MaskFormatter(String mask) {
        super();
        this.patternMap = new HashMap<>();
        this.mask = mask;
    }

    /**
     * @return String representing the current mask.
     */
    public String getMask() {
        return mask;
    }

    /**
     * All mask characters should have been registered with {@link #registerPattern(MaskPattern)}
     * or {@link #registerPattern(String, MaskPattern)}
     *
     * @param mask String pattern that represents the mask used by {@link #format(String)} method
     */
    public void setMask(String mask) {
        this.mask = mask;
    }

    /**
     * Register a {@link MaskPattern}
     *
     * @param pattern {@link MaskPattern} that will be used to validate the mask.
     */
    public void registerPattern(MaskPattern pattern) {
        registerPattern(pattern.getPattern(), pattern);
    }

    /**
     * Register a {@link MaskPattern} using specified mask key
     *
     * @param key     String key that represents the mask
     * @param pattern {@link MaskPattern} that will be used to validate the mask
     */
    public void registerPattern(String key, MaskPattern pattern) {
        patternMap.put(key, pattern);
    }

    /**
     * Format the string according to the mask pattern
     * Removes every character after the last valid pattern mask
     *
     * @param newText String to format
     * @return Formatted string
     */
    public String format(String newText) {
        if (newText == null || "".equals(newText))
            return "";

        int newTextOffset = 0;
        StringBuilder result = new StringBuilder();

        // Count all characters after a valid pattern
        // that is not a Pattern to remove from the final result
        int numOfLiterals = 0;

        // Traverse mask length looking for a match pattern
        for (int l = 0; l < mask.length(); l++) {

            // Traverse the mask pattern from l concatenating each
            // character to build the pattern and look for match
            MaskPattern patternFound = null;
            StringBuilder buffer = new StringBuilder();
            for (int i = l; i < mask.length(); i++) {
                buffer = buffer.append(mask.charAt(i));

                String pattern = buffer.toString();
                if (patternMap.containsKey(pattern)) {
                    patternFound = patternMap.get(pattern);

                    buffer = new StringBuilder();

                    // Looks for the index of a char inside newText that matches the current
                    // patternFound pattern.
                    int nextIndex = indexOfValidChar(patternFound, newText, newTextOffset);
                    if (nextIndex >= 0 && nextIndex < newText.length()) {
                        // Adds 1 to the current position offset
                        newTextOffset = nextIndex + 1;
                        // Get the valid character
                        char nextChar = newText.charAt(nextIndex);

                        result.append(patternFound.transform(nextChar));
                        numOfLiterals = 0;

                        // If there aren't any character left
                        if (newTextOffset >= newText.length())
                            return result.toString();
                    }
                    // If newText reaches the end
                    else {
                        return (numOfLiterals > 0) ? result.substring(0, result.length() - numOfLiterals) : result.toString();
                    }

                    // Adds the search offset as this pattern
                    // has already been used
                    l += (pattern.length() - 1);

                    break;
                }
            }

            // If no valid char was found, it is a literal character.
            if (patternFound == null) {
                result.append(mask.charAt(l));
                numOfLiterals++;
            }
        }

        return (numOfLiterals > 0) ? result.substring(0, result.length() - numOfLiterals) : result.toString();
    }

    /**
     * Look for the first valid index inside @text that matches the patternMaskChar
     *
     * @param patternMaskChar {@link MaskPattern} that will be used to validate text
     * @param text            String used to validate
     * @param offset          Offset number for the next valid index
     * @return Index of the first valid match
     */
    protected int indexOfValidChar(MaskPattern patternMaskChar, String text, int offset) {
        for (int i = offset; i < text.length(); i++) {
            char c = text.charAt(i);
            if (patternMaskChar.isValid(c))
                return i;
        }
        return -1;
    }
}
