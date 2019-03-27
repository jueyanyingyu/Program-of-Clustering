package yangyikun;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Jama.Matrix;

public class PointFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1530877731875637340L;

	public PointFrame(Matrix imagesMTX, int K, int[] cluster) {
		double[][] Y = imagesMTX.transpose().getArray();
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		for (int i = 0; i < Y.length; i++) {
			for (int j = 0; j < Y[i].length; j++) {
				if (Y[i][j] > max) {
					max = Y[i][j];
				}
				if (Y[i][j] < min) {
					min = Y[i][j];
				}
			}
		}
		for (int i = 0; i < Y.length; i++) {
			for (int j = 0; j < Y[i].length; j++) {
				Y[i][j] = Y[i][j] / (max - min);
			}
		}
		this.setTitle("2维投影");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jpanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			// 重写paint方法
			@Override
			public void paint(Graphics graphics) {
				super.paint(graphics);
				Graphics2D g2d = (Graphics2D) graphics;
				g2d.setStroke(new BasicStroke(3f));
				Color[] colorList = { Color.blue, Color.green, Color.orange, Color.pink, Color.red, Color.yellow,
						Color.white, Color.magenta, Color.cyan, Color.gray };
				for (int j = 0; j < K; j++) {
					for (int i = 0; i < Y.length; i++) {
						if (j == cluster[i]) {
							graphics.drawLine((int) (Y[i][0] * 250) + 250, (int) (Y[i][1] * 250) + 250,
									(int) (Y[i][0] * 250) + 250, (int) (Y[i][1] * 250) + 250);
						}
					}
					graphics.setColor(colorList[j%colorList.length]);
				}

			}
		};
		this.add(jpanel);
		// 设置画框大小（宽度，高度），默认都为0
		this.setSize(500, 500);
		// 将画框展示出来。true设置可见，默认为false隐藏
		this.setVisible(true);
	}

}
