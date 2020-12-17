import javax.swing.*;
import javax.swing.text.*;
import java.util.regex.Pattern;

public class JDoubleField extends JTextField
{
	private static final DocumentFilter doubleFilter = new DocumentFilter()
	{
		private final Pattern pattern = Pattern.compile("[+-]?(NaN|Infinity|\\d*(\\.\\d*)?((?<=\\d\\.?)[eE][+-]?\\d*)?)");

		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
		{
			replace(fb, offset, 0, string, attr);
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
		{
			if (pattern.matcher(new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()))
					.replace(offset, offset + length, text))
					.matches())
				super.replace(fb, offset, length, text, attrs);
		}
	};

	public JDoubleField()
	{
		this(null, null, 0);
	}

	public JDoubleField(int columns)
	{
		this(null, null, columns);
	}

	public JDoubleField(String text)
	{
		this(null, text, 0);
	}

	public JDoubleField(String text, int columns)
	{
		this(null, text, columns);
	}

	public JDoubleField(Document doc, String text, int columns)
	{
		super(doc, null, columns);
		((AbstractDocument) getDocument()).setDocumentFilter(doubleFilter);
		if (text != null)
			setText(text);
	}
}
