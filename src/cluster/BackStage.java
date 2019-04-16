package cluster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mnist.MnistReader;
/*
 * 后台类，用于持有识别器引用，参数类对象引用，方法在前台对象中以另一个线程调用
 */
public class BackStage {
	private Identifier identifier;
	public TraningParameter traningParameter;
	private TrainData trainData;
	
	private String testIdx1Path;
	private String testIdx3Path;
	private int K;
	private int testDimension;
	private int testDimensionPlus;
	
	private String Corrent;
	private String Count;
	private String[][] tableData;
	
	public BackStage() {
		// TODO 自动生成的构造函数存根
		this.traningParameter = new TraningParameter();
	}

	public void creatIdentifier() throws IOException, InterruptedException {
			List<int[][]> trainImageData = MnistReader.getImages(traningParameter.getIdx3Path());
			List<int[][]> trainImageList = trainImageData.subList(0,trainImageData.size()*traningParameter.getImageDataDimension()/100);
			int[] trainLabelList = new int[trainImageList.size()];
			int[] trainLabelData = MnistReader.getLabels(traningParameter.getIdx1Path());
			for (int i = 0; i < trainImageList.size(); i++) {
				trainLabelList[i] = trainLabelData[i];
			}
			this.identifier = new Identifier(trainImageList, trainLabelList, traningParameter.getStepSize(),
					traningParameter.getK(), traningParameter.getCycles());
	}

	public void creatIdentifier(String trainDataPath) throws FileNotFoundException, UnsupportedEncodingException {
		FileInputStream fileStream;
			fileStream = new FileInputStream(trainDataPath);
			Reader reader = new InputStreamReader(fileStream, "UTF-8");
			Gson gson = new GsonBuilder().setLenient().create();
			trainData = gson.fromJson(reader, TrainData.class);
			this.identifier = new Identifier(trainData);
	}

	private int[] getIdentify() throws InterruptedException {
		List<int[][]> testImageData = MnistReader.getImages(testIdx3Path);
		testDimensionPlus = testImageData.size()*testDimension/100;
		List<int[][]> testImageList = testImageData.subList(0, testDimensionPlus);
		int[] result = identifier.Identify(testImageList, K);
		return result;
	}
	
	public void setTestData() throws InterruptedException {
		int[] identify = getIdentify();
		int[] idx1Label = MnistReader.getLabels(testIdx1Path);
		int[] label = new int[testDimensionPlus];
		for (int i = 0 ;i<testDimensionPlus;i++) {
			label[i] = idx1Label[i];
		}
		
		int[][] numList = new int[10][3];
		for (int i = 0; i < 10; i++) {
			numList[i][0] = i;
		}
		int count = 0;
		for (int i = 0; i < identify.length; i++) {
			numList[label[i]][1]++;
			if (identify[i] == label[i]) {
				numList[label[i]][2]++;
				count++;
			}
		}
		
		Count = Integer.toString(testDimensionPlus);
		
		DecimalFormat d = new DecimalFormat("#.00");
		Corrent = d.format((double)count/label.length*100)+"%";
		
		tableData = new String[10][3];
		for (int i = 0;i<10;i++) {
			for (int j = 0;j<2;j++) {
				tableData[i][j] = Integer.toString(numList[i][j]);
			}
			DecimalFormat df = new DecimalFormat("#.00");
			tableData[i][2] = df.format((double)numList[i][2]/(double)numList[i][1]*100.0)+"%";
		}
	}
	
	public void save(String path) throws IOException {
		// TODO 自动生成的方法存根
		Gson TD = new Gson();
		String jsonTD = TD.toJson(trainData);
		File file = new File(path);
		Writer out = new FileWriter(file);
		out.write(jsonTD);
		out.close();
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public String getCorrent() {
		return Corrent;
	}

	public String getCount() {
		return Count;
	}

	public String[][] getTableData() {
		return tableData;
	}

	public void setTestIdx1Path(String testIdx1Path) {
		this.testIdx1Path = testIdx1Path;
	}

	public void setTestIdx3Path(String testIdx3Path) {
		this.testIdx3Path = testIdx3Path;
	}

	public void setK(int k) {
		K = k;
	}

	public void setTestDimension(int testDimension) {
		this.testDimension = testDimension;
	}

}
