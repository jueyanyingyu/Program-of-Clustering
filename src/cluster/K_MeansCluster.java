package cluster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Jama.Matrix;

public class K_MeansCluster {
	private Matrix imagesMTX;
	public List<Matrix> centerMTXList;
	private Matrix ONEROWMatrix;
	private Matrix ONECOLUMNMatrix;
	private Matrix[] rangeMTXList;
	private int[] cluster;
	public int k;

	public K_MeansCluster(Matrix imagesMTX, int k) throws InterruptedException {
		this.imagesMTX = imagesMTX;
		this.rangeMTXList = new Matrix[k];
		this.k = k;
		this.cluster = new int[imagesMTX.getColumnDimension()];
		this.centerMTXList = null;
		//随机初始化中心
		createCenter();
		//创建行向量，列向量
		double[][] ONEROW = new double[1][imagesMTX.getColumnDimension()];
		double[][] ONECOLUMN = new double[imagesMTX.getRowDimension()][1];
		for (int i = 0; i < imagesMTX.getColumnDimension(); i++) {
			ONEROW[0][i] = 1D;
		}
		for (int i = 0; i < imagesMTX.getRowDimension(); i++) {
			ONECOLUMN[i][0] = 1D;
		}
		this.ONEROWMatrix = new Matrix(ONEROW);
		this.ONECOLUMNMatrix = new Matrix(ONECOLUMN);
		//循环分类使得代价函数最小
		loopClustering();
	}
	//单次分类
	private void Clustering() throws InterruptedException {
		this.rangeMTXList = getRangeMTXList();
		//计算最小距离，更新中心位置
		for (int i = 0; i < imagesMTX.getColumnDimension(); i++) {
			double minRange = Double.MAX_VALUE;
			int minRangeID = -1;
			for (int j = 0; j < rangeMTXList.length; j++) {
				if (rangeMTXList[j].get(0, i) < minRange) {
					minRange = rangeMTXList[j].get(0, i);
					minRangeID = j;
				}
			}
				Thread.sleep(0);
			cluster[i] = minRangeID;
		}
		//根据新中心更新距离表
		for (int i = 0; i < centerMTXList.size(); i++) {
			Matrix sumTemp = centerMTXList.get(i);
			int count = 1;
			for (int j = 0; j < imagesMTX.getColumnDimension(); j++) {
				if (cluster[j] == i) {
					count++;
					sumTemp = sumTemp.plus(imagesMTX.getMatrix(0, imagesMTX.getRowDimension() - 1, j, j));
				}
			}
			sumTemp = sumTemp.times(1d / count);
			centerMTXList.set(i, sumTemp);
		}
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	private void loopClustering() throws InterruptedException {
		//多次循环直到中心收敛不变
		int[] temp = new int[cluster.length];
		do {
			for (int i = 0; i < cluster.length; i++) {
				temp[i] = cluster[i];
			}
			Clustering();
		} while (!Arrays.equals(temp, cluster));
	}

	public double getSSE() {
		//计算代价函数的值
		Matrix[] nowRange = getRangeMTXList();
		double SSE = 0d;
		for (int i = 0; i < imagesMTX.getColumnDimension(); i++) {
			SSE = SSE + nowRange[cluster[i]].get(0, i);
		}
		return SSE;

	}
	//随机选择中心
	private void createCenter() {
		this.centerMTXList = new ArrayList<>();
		for (int j = 0; j < k; j++) {
			int tempRAND = (int) (Math.random() * (imagesMTX.getColumnDimension() - 1));
			Matrix temp = imagesMTX.getMatrix(0, imagesMTX.getRowDimension() - 1, tempRAND, tempRAND);
			centerMTXList.add(temp);
		}
	}
	//计算各点到各中心的距离
	private Matrix[] getRangeMTXList() {
		Matrix[] rangeMTXList = new Matrix[k];
		Matrix temp;
		for (int i = 0; i < k; i++) {
			temp = centerMTXList.get(i).times(ONEROWMatrix);
			temp = temp.minus(imagesMTX);
			temp = temp.arrayTimes(temp);
			temp = temp.transpose().times(ONECOLUMNMatrix).transpose();
			temp = temp.times(1d / ONECOLUMNMatrix.getRowDimension()).times(1d / ONECOLUMNMatrix.getRowDimension());
			rangeMTXList[i] = temp;
		}
		return rangeMTXList;
	}
}
