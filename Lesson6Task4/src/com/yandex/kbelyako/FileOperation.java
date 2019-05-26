package com.yandex.kbelyako;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOperation {

	public static void copyFile(File in, File out) throws IOException {
		byte[] bufer = new byte[1024 * 1024 * 8];
		int byteread = 0;
		try (FileInputStream fis = new FileInputStream(in);
				FileOutputStream fos = new FileOutputStream(out)) {
			for (; (byteread = fis.read(bufer)) > 0;) {
				fos.write(bufer, 0, byteread);
			}
		} catch (IOException e) {
			throw e;
		}
	}

	public static void copyFilesExt(File inPath, File outPath, String fileExt)
			throws IOException {
		String fileName;
		MyFileFilter mFF = new MyFileFilter(fileExt);
		String inPathStr = inPath.getAbsolutePath();
		if (inPathStr.charAt(inPathStr.length() - 1) != '/') {
			inPathStr = inPathStr + "/";
		}
		File[] fileList = inPath.listFiles(mFF);
		String outPathStr = outPath.getAbsolutePath();
		if (outPathStr.charAt(outPathStr.length() - 1) != '/') {
			outPathStr = outPathStr + "/";
		}
		for (File file : fileList) {
			fileName = file.getName();
			File outFile = new File(outPathStr + fileName);
			copyFile(file, outFile);			
		}

	}

	public static void list(File f) {
		
		File[] fileArray = f.listFiles();
		int fileCounter=0;
		int folderCounter=0;
		for (File fileone : fileArray) {
			long fileSize = fileone.length();
			if (fileone.isFile()) {
				fileCounter=fileCounter+1;
			} else {
				folderCounter=folderCounter+1;
			}
			
			String type = (fileone.isFile()) ? "File" : "Folder";
			System.out.println(fileone.getName() + "\t" + type + "\t"
					+ fileSize);
		}
		
		System.out.println("Total " +fileCounter+" files");
		System.out.println("Total " +folderCounter+" folders");
		
	}

}