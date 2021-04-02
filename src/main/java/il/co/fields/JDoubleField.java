package il.co.fields;

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
			if (text == null || pattern.matcher(new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()))
					.replace(offset, offset + length, text))
					.matches())
				super.replace(fb, offset, length, text, attrs);
		}
	};
	private double num;

	public JDoubleField()
	{
		this(0);
	}

	public JDoubleField(double num)
	{
		this(num, 0);
	}

	public JDoubleField(double num, int columns)
	{
		this(null, num, columns);
	}

	public JDoubleField(Document doc, double num, int columns)
	{
		this(doc, String.valueOf(num), columns, false);
		this.num = num;
	}

	public JDoubleField(int columns)
	{
		this(null, columns);
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
		this(doc, text, columns, true);
	}

	protected JDoubleField(Document doc, String text, int columns, boolean isCallToOverriddenSetText)
	{
		super(doc, null, columns);
		((AbstractDocument) getDocument()).setDocumentFilter(doubleFilter);
		if (text != null)
			if (isCallToOverriddenSetText)
				setText(text);
			else
				super.setText(text);
	}

	public double getDouble()
	{
		return num;
	}

	public void setDouble(double num)
	{
		setText(String.valueOf(this.num = num));
	}

	@Override
	public void setText(String t)
	{
		try
		{
			num = Double.parseDouble(t);
		} catch (NumberFormatException ignored)
		{
			num = Double.NaN;
		}
		super.setText(t);
	}
}