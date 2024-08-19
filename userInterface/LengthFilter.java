package userInterface;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class LengthFilter extends DocumentFilter {
    private int maxLength;

    public LengthFilter(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMaxCharacters() {
        return maxLength;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string==null || (fb.getDocument().getLength() + string.length()) <= maxLength) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

        if (text==null || (fb.getDocument().getLength() + text.length() - length) <= maxLength) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
