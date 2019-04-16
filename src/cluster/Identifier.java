package cluster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import Jama.Matrix;

public class Identifier {
	private TrainData trainData;
	private int smaplingSize;

//根据新数据重新训练
	public Identifier(List<int[][]> trainImageData, int[] trainLabelData, int smaplingSize, int k, int cycleIndex)
			throws IOException, InterruptedException {
		ImageOperator imageOperator = new ImageOperator(smaplingSize, trainImageData);
		this.smaplingSize = smaplingSize;
		this.trainData = K_MeansTrainning(imageOperator.getMTX(), trainLabelData, k, cycleIndex);
	}

//读入已训练的数据
	public Identifier(TrainData trainData) {
		this.trainData = trainData;
		this.smaplingSize = trainData.smaplingSize;
	}

//输出测试数据进行测试
	public int[] Identify(List<int[][]> testImageData, int K) throws InterruptedException {
		int[] resultList = new int[testImageData.size()];
		ImageOperator imageOperator = new ImageOperator(smaplingSize, testImageData);
		Matrix imageMTX = imageOperator.getMTX();
		for (int i = 0; i < imageMTX.getColumnDimension(); i++) {
			TreeMap<Double, Integer> rangeMap = new TreeMap<>();
			//KNN聚类算法
			rangeMap = getSortedRangeMap(imageMTX.getMatrix(0, imageMTX.getRowDimension() - 1, i, i), rangeMap);
			int[] count = new int[10];
			for (int ktest = 0; ktest < K; ktest++) {
				count[rangeMap.pollFirstEntry().getValue()]++;
			}
			int maxID = 0;
			int max = Integer.MIN_VALUE;
			for (int j = 0; j < count.length; j++) {
				if (count[j] > max) {
					max = count[j];
					maxID = j;
				}
			}
			resultList[i] = maxID;
				Thread.sleep(0);
		}
		return resultList;

	}

//k-means聚类训练
	private TrainData K_MeansTrainning(Matrix imageMTX, int[] trainLabelData, int k, int cycleIndex) throws InterruptedException {
		Matrix[][] centerMTXList = new Matrix[10][k];
		for (int n = 0; n < 10; n++) {
			ArrayList<Integer> columnList = new ArrayList<>();
			for (int i = 0; i < trainLabelData.length; i++) {
				if (trainLabelData[i] == n) {
					columnList.add(i);
				}
			}
			Integer[] nIDList = columnList.toArray(new Integer[0]);
			Matrix nImageMTX = imageMTX.getMatrix(0, imageMTX.getRowDimension() - 1,
					Arrays.stream(nIDList).mapToInt(Integer::valueOf).toArray());
			double minSSE = Double.MAX_VALUE;
			Matrix[] minSSEcluster = null;
			for (int c = 0; c < cycleIndex; c++) {
				K_MeansCluster cluster = new K_MeansCluster(nImageMTX, k);
				if (cluster.getSSE() < minSSE) {
					minSSEcluster = cluster.centerMTXList.toArray(new Matrix[0]);
				}
			}
			centerMTXList[n] = minSSEcluster;
		}
		TrainData trainData = new TrainData(centerMTXList, smaplingSize);
		return trainData;
	}

//得到有序距离map
	private TreeMap<Double, Integer> getSortedRangeMap(Matrix aColumn, TreeMap<Double, Integer> rangeMap) {
		for (int i = 0; i < trainData.centerList.length; i++) {
			for (int j = 0; j < trainData.centerList[i].length; j++) {
				Matrix temp = trainData.centerList[i][j].minus(aColumn);
				temp = temp.arrayTimes(temp);
				rangeMap.put(temp.norm1(), i);
			}
		}
		return rangeMap;
	}

}
