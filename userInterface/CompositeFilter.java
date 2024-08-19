package userInterface;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CompositeFilter extends DocumentFilter {

    private DocumentFilter lengthFilter;
    private DocumentFilter contentFilter;

    public CompositeFilter(DocumentFilter contentFilter, DocumentFilter lengthFilter) {
        this.lengthFilter = lengthFilter;
        this.contentFilter = contentFilter;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        // D'abord, vérifier la longueur
        if (string != null && (fb.getDocument().getLength() + string.length()) <= ((LengthFilter) lengthFilter).getMaxCharacters()) {
            // Ensuite, appliquer le filtre de contenu
            contentFilter.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        // D'abord, vérifier la longueur
        if (text != null && (fb.getDocument().getLength() - length + text.length()) <= ((LengthFilter) lengthFilter).getMaxCharacters()) {
            // Ensuite, appliquer le filtre de contenu
            contentFilter.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        contentFilter.remove(fb, offset, length);
    }
}
