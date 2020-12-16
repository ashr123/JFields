package il.co.anglesCalculator;

import javax.swing.*;
import javax.swing.text.*;
import java.util.regex.Pattern;

class JDoubleField extends JTextField
{
	private static final DocumentFilter doubleFilter = new DocumentFilter()
	{
		private final Pattern pattern = Pattern.compile("[+-]?\\d*(\\.\\d*)?((?<=\\d\\.?)[eE][+-]?\\d*)?");

//		@Override
//		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
//		{
//			System.out.println("insertString!!!!!!!!!!!");
//			replace(fb, offset, 0, string, attr);
//		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
		{
			final Document document = fb.getDocument();
			final StringBuilder stringBuilder = new StringBuilder(document.getText(0, document.getLength()));
			stringBuilder.replace(offset, offset + length, text);
			if (pattern.matcher(stringBuilder).matches())
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