package guiSWT;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class TestComposite extends Composite {
	private Text idx1Text;
	private Text idx3Text;
	private Text KText;
	private Text DimensionText;
	private ProgressBar progressBar;
	
	private Button NextButton;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public TestComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(null);

		Label idx1Label = new Label(this, SWT.NONE);
		idx1Label.setBounds(10, 15, 76, 20);
		idx1Label.setText("选择idx1:");

		idx1Text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		idx1Text.setBounds(92, 12, 226, 26);

		Button idx1Button = new Button(this, SWT.NONE);
		idx1Button.setBounds(324, 10, 98, 30);
		idx1Button.setText("...");

		Label idx3Label = new Label(this, SWT.NONE);
		idx3Label.setBounds(10, 51, 76, 20);
		idx3Label.setText("选择idx3：");

		idx3Text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		idx3Text.setBounds(92, 48, 226, 26);

		Button idx3Button = new Button(this, SWT.NONE);
		idx3Button.setBounds(324, 46, 98, 30);
		idx3Button.setText("...");

		Label KLabel = new Label(this, SWT.NONE);
		KLabel.setBounds(10, 87, 76, 20);
		KLabel.setText("K：");

		KText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		KText.setText("1");
		KText.setBounds(92, 84, 73, 26);

		Slider KSlider = new Slider(this, SWT.NONE);
		KSlider.setThumb(1);
		KSlider.setMaximum(11);
		KSlider.setMinimum(1);
		KSlider.setSelection(1);
		KSlider.setBounds(171, 86, 251, 21);

		Label DimensionLabel = new Label(this, SWT.NONE);
		DimensionLabel.setBounds(10, 123, 76, 20);
		DimensionLabel.setText("识别张数：");

		DimensionText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		DimensionText.setText("10%");
		DimensionText.setBounds(92, 120, 73, 26);

		Slider DimensionSlider = new Slider(this, SWT.NONE);
		DimensionSlider.setThumb(1);
		DimensionSlider.setMaximum(101);
		DimensionSlider.setMinimum(1);
		DimensionSlider.setSelection(10);
		DimensionSlider.setBounds(171, 122, 251, 21);

		progressBar = new ProgressBar(this, SWT.INDETERMINATE);
		progressBar.setBounds(10, 152, 412, 21);
		progressBar.setVisible(false);

		NextButton = new Button(this, SWT.NONE);
		NextButton.setBounds(165, 179, 98, 30);
		NextButton.setText("开始识别");

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
		
		KSlider.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				KText.setText(Integer.toString(KSlider.getSelection()));
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
		});
		
		DimensionSlider.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				DimensionText.setText(Integer.toString(DimensionSlider.getSelection())+"%");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
		});
		
		NextButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				if (!idx1Text.getText().equals("")&&!idx3Text.getText().equals("")) {
					idx1Text.setEnabled(false);
					idx3Text.setEnabled(false);
					idx1Button.setEnabled(false);
					idx3Button.setEnabled(false);
					KSlider.setEnabled(false);
					DimensionSlider.setEnabled(false);
					NextButton.setEnabled(false);
					KText.setEnabled(false);
					DimensionText.setEnabled(false);
					progressBar.setVisible(true);
				}
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

	public Text getIdx1Text() {
		return idx1Text;
	}

	public Text getIdx3Text() {
		return idx3Text;
	}

	public Text getKText() {
		return KText;
	}

	public Text getDimensionText() {
		return DimensionText;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public Button getNextButton() {
		return NextButton;
	}
	
	
}
