package yangyikun;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PicFileFilter extends FileFilter {
	private String extension0;

	public PicFileFilter(String extension0) {
		this.extension0 = extension0;
	}

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String fileName = f.getName();
		int index = fileName.lastIndexOf('.');
		if (index > 0 && index < fileName.length() - 1) {
			String extension = fileName.substring(index).toLowerCase();
			if (extension.equals(extension0)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		return extension0;
	}

}
