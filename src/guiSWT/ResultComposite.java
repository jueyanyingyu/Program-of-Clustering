package guiSWT;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;

public class ResultComposite extends Composite {
	private Text DimensionText;
	private Text CorrentText;
	private Table table;
	private Button saveButton;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ResultComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(null);
		
		Label DimensionLabel = new Label(this, SWT.NONE);
		DimensionLabel.setBounds(270, 10, 76, 20);
		DimensionLabel.setText("总数：");
		
		DimensionText = new Text(this, SWT.BORDER);
		DimensionText.setBounds(352, 7, 73, 26);
		
		Label CorrentLabel = new Label(this, SWT.NONE);
		CorrentLabel.setBounds(270, 42, 76, 20);
		CorrentLabel.setText("总正确率：");
		
		CorrentText = new Text(this, SWT.BORDER);
		CorrentText.setBounds(352, 39, 73, 26);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 254, 233);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		saveButton = new Button(this, SWT.NONE);
		saveButton.setBounds(297, 177, 98, 30);
		saveButton.setText("保存训练数据");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public Text getDimensionText() {
		return DimensionText;
	}

	public Text getCorrentText() {
		return CorrentText;
	}

	public Table getTable() {
		return table;
	}
	
	public Button getSaveButton() {
		return saveButton;
	}
}
