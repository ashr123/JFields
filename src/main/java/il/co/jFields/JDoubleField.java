package il.co.jFields;

import javax.swing.*;
import javax.swing.text.*;
import java.util.regex.Pattern;

public class JDoubleField extends JTextField
{
	private static final DocumentFilter doubleFilter = new DocumentFilter()
	{
		private final Pattern pattern = Pattern.compile("[+-]?(" + Double.NaN + "|" + Double.POSITIVE_INFINITY + "|\\d*(\\.\\d*)?((?<=\\d\\.?)[eE][+-]?\\d*)?)");

		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
		{
			replace(fb, offset, 0, string, attr);
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
		{
			if (text == null || pattern.matcher(new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()))
					.replace(offset, offset + length, text))
					.matches())
				super.replace(fb, offset, length, text, attrs);
		}
	};

	public JDoubleField()
	{
		this(0);
	}

	public JDoubleField(int columns)
	{
		this(null, columns);
	}

	public JDoubleField(double num)
	{
		this(String.valueOf(num));
	}

	public JDoubleField(double num, int columns)
	{
		this(String.valueOf(num), columns);
	}

	public JDoubleField(Document doc, double num, int columns)
	{
		this(doc, String.valueOf(num), columns);
	}

	public JDoubleField(String text)
	{
		this(text, 0);
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

	public double getDouble()
	{
		try
		{
			return Double.parseDouble(getText());
		} catch (NumberFormatException ignored)
		{
			return Double.NaN;
		}
	}

	public void setDouble(double num)
	{
		setText(String.valueOf(num));
	}
}