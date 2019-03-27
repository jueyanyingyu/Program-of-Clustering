package yangyikun;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class PACer {
	private Matrix toPAC;
	private Matrix X;

	public PACer(Matrix toPAC) {
		this.toPAC = toPAC;
	}

	private Matrix getV() {
		X = zeroMeans();
		Matrix C = X.times(X.transpose()).times(1D / toPAC.getColumnDimension());
		EigenvalueDecomposition EM = new EigenvalueDecomposition(C);
		double[] E = EM.getRealEigenvalues();
		Matrix V = EM.getV().transpose();
		double[][] DAry = V.getArray();
		for (int i = 0; i < E.length; i++) {
			double MAXTemp = Integer.MIN_VALUE;
			int MAXID = -1;
			for (int j = i; j < E.length; j++) {
				if (E[j] > MAXTemp) {
					MAXTemp = E[j];
					MAXID = j;
				}
			}
			double temp0 = E[i];
			E[i] = E[MAXID];
			E[MAXID] = temp0;
			double[] temp1 = DAry[i];
			DAry[i] = DAry[MAXID];
			DAry[MAXID] = temp1;
		}
		V = new Matrix(DAry);
		// D.print(0, 5);
		return V = V.transpose();
	}
	
	public Matrix PACMethod() {
		Matrix V = getV();
		return V.transpose().times(X);
	}
	public Matrix PACMethod(int k) {
		Matrix V = getV();
		Matrix P = V.getMatrix(0, V.getRowDimension() - 1, 0, k - 1);
		// P.print(0, 5);
		return P.transpose().times(X);
	}
	private Matrix zeroMeans() {
		double[][] toPACAry = toPAC.getArray();
		for (int i = 0; i < toPACAry.length; i++) {
			double mean = 0d;
			for (int j = 0; j < toPACAry[i].length; j++) {
				mean = mean + toPACAry[i][j];
			}
			mean = mean / (double) toPACAry[i].length;
			for (int j = 0; j < toPACAry[i].length; j++) {
				toPACAry[i][j] = toPACAry[i][j] - mean;
			}
		}
		Matrix X = new Matrix(toPACAry);
		return X;
	}
}
