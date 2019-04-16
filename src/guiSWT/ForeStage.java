package guiSWT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import cluster.BackStage;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;

/*
 * 前台类，包含main方法，持有后台对象，以及各个组件的引用，获取信息传入后台，持有线程池对象引用
 */
public class ForeStage extends Shell {
	private BackStage backStage;

	private StackLayout stackLayout;

	private MethodComposite methodComposite;
	private ParameterComposite parameterComposite;
	private TestComposite testComposite;
	private ResultComposite resultComposite;

	private Button MethodNextButton;
	private Button ParameterNextButton;
	private Button TestNextButton;
	private Button SaveButton;

	private ExecutorService backStageExecutorService;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ForeStage shell = new ForeStage(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public ForeStage(Display display) {
		// 设置顶级容器信息，栈布局
		super(display, SWT.CLOSE | SWT.TITLE);
		this.backStage = new BackStage();
		stackLayout = new StackLayout();
		setLayout(stackLayout);
		// 创建面板，以及获取触发翻页条件的按钮引用
		methodComposite = new MethodComposite(this, SWT.NONE);
		MethodNextButton = methodComposite.getNextButton();
		parameterComposite = new ParameterComposite(this, SWT.NONE);
		ParameterNextButton = parameterComposite.getNextButton();
		testComposite = new TestComposite(this, SWT.NONE);
		TestNextButton = testComposite.getNextButton();
		resultComposite = new ResultComposite(this, SWT.NONE);
		SaveButton = resultComposite.getSaveButton();
		// 设置首个面板
		stackLayout.topControl = methodComposite;
		// 创建线程池对象，用于管理线程
		backStageExecutorService = Executors.newCachedThreadPool();
		// 创建
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("mnist手写数字聚类识别程序");
		setSize(450, 300);
		// 添加三个翻页监视器，以及关闭监视器
		MethodNextButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				if (!methodComposite.getJsonText().getText().equals("")) {
					backStageExecutorService.execute(new Runnable() {

						@Override
						public void run() {
							// TODO 自动生成的方法存根
							Display.getDefault().asyncExec(new Runnable() {

								@Override
								public void run() {
									// TODO 自动生成的方法存根
									try {
										backStage.creatIdentifier(methodComposite.getJsonText().getText());
									} catch (FileNotFoundException | UnsupportedEncodingException e) {
										// TODO 自动生成的 catch 块
										Display.getDefault().asyncExec(new Runnable() {
											
											@Override
											public void run() {
												// TODO 自动生成的方法存根
												MessageDialog.openInformation(new Shell(), "error", e.toString());
										Display.getDefault().dispose();
											}
										});
									}
								}
							});
						}
					});
					stackLayout.topControl = testComposite;
					newLayout();
				} else if (!methodComposite.getIdx1Text().getText().equals("")
						&& !methodComposite.getIdx3Text().getText().equals("")) {
					backStage.traningParameter.setIdx1Path(methodComposite.getIdx1Text().getText());
					backStage.traningParameter.setIdx3Path(methodComposite.getIdx3Text().getText());
					stackLayout.topControl = parameterComposite;
					newLayout();
				} else {

				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根

			}
		});
		ParameterNextButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				backStageExecutorService.execute(new Runnable() {

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						Display.getDefault().asyncExec(new Runnable() {

							@Override
							public void run() {
								// TODO 自动生成的方法存根
								int[] combo = { 1, 2, 4, 7, 14 };
								backStage.traningParameter
										.setStepSize(combo[parameterComposite.getStepSizeCombo().getSelectionIndex()]);
								backStage.traningParameter
										.setK(Integer.valueOf(parameterComposite.getkText().getText()));
								backStage.traningParameter
										.setCycles(Integer.valueOf(parameterComposite.getCyclesText().getText()));
								backStage.traningParameter.setImageDataDimension(Integer
										.valueOf(parameterComposite.getDimensionText().getText().replace("%", "")));
							}
						});
						try {
							backStage.creatIdentifier();
						} catch (IOException | InterruptedException e) {
							// TODO 自动生成的 catch 块
							Display.getDefault().asyncExec(new Runnable() {
								
								@Override
								public void run() {
									// TODO 自动生成的方法存根
									MessageDialog.openInformation(new Shell(), "error", e.toString());
							Display.getDefault().dispose();
								}
							});
						}
						Display.getDefault().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								// TODO 自动生成的方法存根
								stackLayout.topControl = testComposite;
						newLayout();
							}
						});
					}
				});
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根

			}
		});
		TestNextButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				if (!testComposite.getIdx1Text().getText().equals("")
						&& !testComposite.getIdx3Text().getText().equals("")) {
					backStage.setTestIdx1Path(testComposite.getIdx1Text().getText());
					backStage.setTestIdx3Path(testComposite.getIdx3Text().getText());
					backStage.setK(Integer.valueOf(testComposite.getKText().getText()));
					backStage.setTestDimension(
							Integer.valueOf(testComposite.getDimensionText().getText().replace("%", "")));
					backStageExecutorService.execute(new Runnable() {

						@Override
						public void run() {
							// TODO 自动生成的方法存根
							try {
								backStage.setTestData();
							} catch (InterruptedException e) {
								// TODO 自动生成的 catch 块
								Display.getDefault().asyncExec(new Runnable() {
									
									@Override
									public void run() {
										// TODO 自动生成的方法存根
										MessageDialog.openInformation(new Shell(), "error", e.toString());
								Display.getDefault().dispose();
									}
								});
								
							}
							Display.getDefault().asyncExec(new Runnable() {

								@Override
								public void run() {
									// TODO 自动生成的方法存根
									resultComposite.getCorrentText().setText(backStage.getCorrent());
									resultComposite.getDimensionText().setText(backStage.getCount());
									String[] StringColumn = new String[] { "数字", "总数", "正确率" };
									for (int i = 0; i < StringColumn.length; i++) {
										TableColumn column = new TableColumn(resultComposite.getTable(), SWT.NONE);
										column.setText(StringColumn[i]);
									}
									for (int i = 0; i < backStage.getTableData().length; i++) {
										TableItem item = new TableItem(resultComposite.getTable(), SWT.NONE);
										item.setText(backStage.getTableData()[i]);
									}
									for (int i = 0; i < StringColumn.length; i++) {
										resultComposite.getTable().getColumn(i).pack();
									}
									stackLayout.topControl = resultComposite;
									newLayout();
								}
							});

						}
					});
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根

			}
		});
		SaveButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dialog = new FileDialog(new Shell(), SWT.OPEN);
				dialog.setFilterPath(".");
				dialog.setText("选择储存位置");
				//dialog.setFilterNames(new String[] { "*.idx1-ubyte" });
				dialog.setFilterExtensions(new String[] { "*.json" });
				String path = dialog.open();
				backStageExecutorService.execute(new Runnable() {
					
					@Override
					public void run() {
						// TODO 自动生成的方法存根
						try {
							backStage.save(path);
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							Display.getDefault().asyncExec(new Runnable() {
								
								@Override
								public void run() {
									// TODO 自动生成的方法存根
									MessageDialog.openInformation(new Shell(), "error", e.toString());
							Display.getDefault().dispose();
								}
							});
						}
					}
				});
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
		});
		addShellListener(new ShellListener() {

			@Override
			public void shellDeiconified(ShellEvent arg0) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void shellDeactivated(ShellEvent arg0) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void shellClosed(ShellEvent arg0) {
				// TODO 自动生成的方法存根
				Display.getDefault().dispose();
				backStageExecutorService.shutdown();

			}

			@Override
			public void shellActivated(ShellEvent arg0) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void shellIconified(ShellEvent arg0) {
				// TODO 自动生成的方法存根

			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public BackStage getBackStage() {
		return backStage;
	}

	private void newLayout() {
		this.layout();
	}

}
