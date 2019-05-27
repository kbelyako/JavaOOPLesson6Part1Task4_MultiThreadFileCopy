package com.yandex.kbelyako;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

public class SingleThreadCopy implements Runnable {
	private File[] array;
	private int begin;
	private int end;
	private File inPath;
	private File outPath;
	private Thread thr;
	private BigInteger sum;

	public SingleThreadCopy(File inPath,File outPath,int begin, int end) {
		super();
		this.begin = begin;
		this.end = end;
		this.inPath=inPath;
		this.outPath=outPath;
		thr = new Thread(this);
		thr.start();
	}
	
	public SingleThreadCopy(File inPath,File outPath) {
		super();
		this.array = array;
		this.begin = begin;
		this.end = end;
		this.inPath=inPath;
		this.outPath=outPath;
		thr = new Thread(this);
		thr.start();
	}

	public Thread getThr() {
		return thr;
	}


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
	
	private void copyFiles(File inPath, File outPath)
			throws IOException {
		String fileName;
		//MyFileFilter mFF = new MyFileFilter(fileExt);
		String inPathStr = inPath.getAbsolutePath();
		if (inPathStr.charAt(inPathStr.length() - 1) != '/') {
			inPathStr = inPathStr + "/";
		}
		File[] fileList = inPath.listFiles();		
		String outPathStr = outPath.getAbsolutePath();
		if (outPathStr.charAt(outPathStr.length() - 1) != '/') {
			outPathStr = outPathStr + "/";
		}
		for (int i = this.begin; i < this.end; i++) {
			fileName = fileList[i].getName();
			//System.out.println(fileList[i].getName());
			File outFile = new File(outPathStr + fileName);
			copyFile(fileList[i], outFile);
		} 
		

	}

	@Override
	public void run() {
		
		try {
			copyFiles(this.inPath, this.outPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

}