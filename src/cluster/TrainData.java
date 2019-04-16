package cluster;

import Jama.Matrix;

public class TrainData {
	//行表示大类，列表示聚类中心数量
	public Matrix[][] centerList;
	public int smaplingSize;

	public TrainData(Matrix[][] centerList,int smaplingSize) {
		this.centerList = centerList;
		this.smaplingSize = smaplingSize;
	}
}
