package cluster;

import java.util.List;

import Jama.Matrix;

public class ImageOperator {
	private Matrix imageMTX;
//处理图片成列向量合成矩阵
	public ImageOperator(int N, List<int[][]> imagesData) {
		double[][] imageArraysTemp = new double[imagesData.get(0).length * imagesData.get(0)[0].length / N
				/ N][imagesData.size()];
		for (int n = 0; n < imagesData.size(); n++) {
			for (int i = 0; i < imagesData.get(0).length / N; i++) {
				for (int j = 0; j < imagesData.get(0).length / N; j++) {
					int blockSum = 0;
					for (int y = 0; y < N; y++) {
						for (int x = 0; x < N; x++) {
							blockSum = blockSum + imagesData.get(n)[N * i + y][N * j + x];
						}
					}
					imageArraysTemp[i * imagesData.get(0)[0].length / N + j][n] = blockSum / N / N / 255D;
				}

			}
		}
		this.imageMTX = new Matrix(imageArraysTemp);
	}
	
	public Matrix getMTX() {
		return this.imageMTX;
	}
}
