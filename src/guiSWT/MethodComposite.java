package guiSWT;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public class MethodComposite extends Composite {
	private Text jsonText;
	private Text idx1Text;
	private Text idx3Text;

	private Button NextButton;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public MethodComposite(Composite parent, int style) {
		super(parent, style);
		parent = this.getParent();
		setLayout(null);

		Label MethodLabel = new Label(this, SWT.NONE);
		MethodLabel.setBounds(10, 13, 76, 20);
		MethodLabel.setText("选择模式：");

		Combo MethodCombo = new Combo(this, SWT.NONE);
		MethodCombo.setItems(new String[] { "选择已训练的json数据", "选择idx1，idx3数据重新训练" });
		MethodCombo.setToolTipText("");
		MethodCombo.setBounds(92, 10, 328, 28);
		MethodCombo.setText("选择模式");

		Label jsonLabel = new Label(this, SWT.NONE);
		jsonLabel.setBounds(10, 49, 76, 20);
		jsonLabel.setText("选择json：");
		jsonLabel.setVisible(false);

		jsonText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		jsonText.setBounds(92, 46, 244, 26);
		jsonText.setVisible(false);

		Button jsonButton = new Button(this, SWT.NONE);
		jsonButton.setBounds(342, 44, 78, 30);
		jsonButton.setText("...");
		jsonButton.setVisible(false);

		Label idx1Label = new Label(this, SWT.NONE);
		idx1Label.setBounds(10, 49, 76, 20);
		idx1Label.setText("选择idx1:");
		idx1Label.setVisible(false);

		idx1Text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		idx1Text.setBounds(92, 46, 244, 26);
		idx1Text.setVisible(false);

		Button idx1Button = new Button(this, SWT.NONE);
		idx1Button.setBounds(342, 44, 78, 30);
		idx1Button.setText("...");
		idx1Button.setVisible(false);

		Label idx3Label = new Label(this, SWT.NONE);
		idx3Label.setBounds(10, 85, 76, 20);
		idx3Label.setText("选择idx3：");
		idx3Label.setVisible(false);

		idx3Text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		idx3Text.setBounds(92, 82, 244, 26);
		idx3Text.setVisible(false);

		Button idx3Button = new Button(this, SWT.NONE);
		idx3Button.setBounds(342, 80, 78, 30);
		idx3Button.setText("...");
		idx3Button.setVisible(false);

		NextButton = new Button(this, SWT.NONE);
		NextButton.setBounds(167, 194, 97, 30);
		NextButton.setText("下一步");

		MethodCombo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				if (MethodCombo.getSelectionIndex() == 0) {
					jsonText.setText("");
					jsonLabel.setVisible(true);
					jsonText.setVisible(true);
					jsonButton.setVisible(true);

					idx1Text.setText("");
					idx1Label.setVisible(false);
					idx1Text.setVisible(false);
					idx1Button.setVisible(false);
					idx3Text.setText("");
					idx3Label.setVisible(false);
					idx3Text.setVisible(false);
					idx3Button.setVisible(false);
				}
				if (MethodCombo.getSelectionIndex() == 1) {
					idx1Label.setVisible(true);
					idx1Text.setVisible(true);
					idx1Button.setVisible(true);
					idx3Label.setVisible(true);
					idx3Text.setVisible(true);
					idx3Button.setVisible(true);

					jsonLabel.setVisible(false);
					jsonText.setVisible(false);
					jsonButton.setVisible(false);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根

			}
		});

		jsonButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				FileDialog dialog = new FileDialog(new Shell(), SWT.SHELL_TRIM) ;
				dialog.setFilterPath(".");
				dialog.setText("选择*.json文件");
				dialog.setFilterNames(new String[] { "*.json" });
				dialog.setFilterExtensions(new String[] { "*.json" });
				jsonText.setText(dialog.open());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根

			}
		});
		idx1Button.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				FileDialog dialog = new FileDialog(new Shell(), SWT.OPEN);
				dialog.setFilterPath(".");
				dialog.setText("选择*.idx1-ubyte文件");
				dialog.setFilterNames(new String[] { "*.idx1-ubyte" });
				dialog.setFilterExtensions(new String[] { "*.idx1-ubyte" });
				idx1Text.setText(dialog.open());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根

			}
		});

		idx3Button.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				FileDialog dialog = new FileDialog(new Shell(), SWT.OPEN);
				dialog.setFilterPath(".");
				dialog.setText("选择*.idx3-ubyte文件");
				dialog.setFilterNames(new String[] { "*.idx3-ubyte" });
				dialog.setFilterExtensions(new String[] { "*.idx3-ubyte" });
				idx3Text.setText(dialog.open());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根

			}
		});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public Text getJsonText() {
		return jsonText;
	}

	public Text getIdx1Text() {
		return idx1Text;
	}

	public Text getIdx3Text() {
		return idx3Text;
	}

	public Button getNextButton() {
		return NextButton;
	}

}
