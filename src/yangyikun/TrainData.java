package yangyikun;

import Jama.Matrix;

public class TrainData {
	public Matrix[][] centerList;
	public int smaplingSize;

	public TrainData(Matrix[][] centerList,int smaplingSize) {
		this.centerList = centerList;
		this.smaplingSize = smaplingSize;
	}
}
