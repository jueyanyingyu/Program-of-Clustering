package guiSWT;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ProgressBar;

public class ParameterComposite extends Composite {
	private Combo StepSizeCombo;
	private Text kText;
	private Text cyclesText;
	private Text dimensionText;
	private Slider dimensionSlider;
	private ProgressBar progressBar;
	
	private Button NextButton;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ParameterComposite(Composite parent, int style) {
		super(parent, style);
		
		Label StepSizeLabel = new Label(this, SWT.NONE);
		StepSizeLabel.setBounds(10, 13, 76, 20);
		StepSizeLabel.setText("处理步长：");
		
		StepSizeCombo = new Combo(this, SWT.READ_ONLY);
		StepSizeCombo.setItems(new String[] {"1", "2", "4", "7", "14"});
		StepSizeCombo.setBounds(92, 10, 330, 28);
		StepSizeCombo.select(2);
		
		Label kLabel = new Label(this, SWT.NONE);
		kLabel.setBounds(10, 47, 76, 20);
		kLabel.setText("k：");
		
		kText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		kText.setText("10");
		kText.setBounds(92, 44, 73, 26);
		
		Slider kSlider = new Slider(this, SWT.NONE);
		kSlider.setThumb(1);
		kSlider.setMaximum(101);
		kSlider.setMinimum(1);
		kSlider.setSelection(10);
		kSlider.setBounds(171, 46, 251, 21);
		
		Label cyclesLabel = new Label(this, SWT.NONE);
		cyclesLabel.setBounds(10, 79, 76, 20);
		cyclesLabel.setText("循环次数：");
		
		cyclesText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		cyclesText.setText("2");
		cyclesText.setBounds(92, 76, 73, 26);
		
		Slider cyclesSlider = new Slider(this, SWT.NONE);
		cyclesSlider.setThumb(1);
		cyclesSlider.setMaximum(11);
		cyclesSlider.setMinimum(1);
		cyclesSlider.setSelection(2);
		cyclesSlider.setBounds(171, 78, 251, 21);
		
		Label dimensionLabel = new Label(this, SWT.NONE);
		dimensionLabel.setBounds(10, 111, 76, 20);
		dimensionLabel.setText("处理张数：");
		
		dimensionText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		dimensionText.setText("10%");
		dimensionText.setBounds(92, 108, 73, 26);
		
		dimensionSlider = new Slider(this, SWT.NONE);
		dimensionSlider.setThumb(1);
		dimensionSlider.setMaximum(101);
		dimensionSlider.setMinimum(1);
		dimensionSlider.setSelection(10);
		dimensionSlider.setBounds(171, 110, 251, 21);
		
		NextButton = new Button(this, SWT.NONE);
		NextButton.setBounds(167, 187, 98, 30);
		NextButton.setText("开始识别");
		
		progressBar = new ProgressBar(this, SWT.INDETERMINATE);
		progressBar.setBounds(10, 160, 412, 21);
		progressBar.setVisible(false);
		
		kSlider.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				kText.setText(Integer.toString(kSlider.getSelection()));
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
		});

		cyclesSlider.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				cyclesText.setText(Integer.toString(cyclesSlider.getSelection()));
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
		});
		
		dimensionSlider.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				dimensionText.setText(Integer.toString(dimensionSlider.getSelection())+"%");
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
				StepSizeCombo.setEnabled(false);
				kSlider.setEnabled(false);
				cyclesSlider.setEnabled(false);
				dimensionSlider.setEnabled(false);
				NextButton.setEnabled(false);
				kText.setEnabled(false);
				cyclesText.setEnabled(false);
				dimensionText.setEnabled(false);
				progressBar.setVisible(true);
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

	public Text getkText() {
		return kText;
	}

	public Text getCyclesText() {
		return cyclesText;
	}

	public Text getDimensionText() {
		return dimensionText;
	}

	public Slider getDimensionSlider() {
		return dimensionSlider;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public Button getNextButton() {
		return NextButton;
	}
	

	public Combo getStepSizeCombo() {
		return StepSizeCombo;
	}
	
	
}
