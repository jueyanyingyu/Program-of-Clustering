package cluster;

public class TraningParameter {
	private String idx1Path;
	private String idx3Path;
	private int StepSize;
	private int k;
	private int cycles;
	private int imageDataDimension;

	public String getIdx1Path() {
		return idx1Path;
	}

	public void setIdx1Path(String idx1Path) {
		this.idx1Path = idx1Path;
	}

	public String getIdx3Path() {
		return idx3Path;
	}

	public void setIdx3Path(String idx3Path) {
		this.idx3Path = idx3Path;
	}


	public int getStepSize() {
		return StepSize;
	}

	public void setStepSize(int stepSize) {
		StepSize = stepSize;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getCycles() {
		return cycles;
	}

	public void setCycles(int cycles) {
		this.cycles = cycles;
	}

	public int getImageDataDimension() {
		return imageDataDimension;
	}

	public void setImageDataDimension(int imageDataDimension) {
		this.imageDataDimension = imageDataDimension;
	}

}
