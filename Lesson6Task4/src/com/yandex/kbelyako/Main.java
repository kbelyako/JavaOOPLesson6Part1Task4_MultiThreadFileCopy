//4) Реализуйте многопоточное копирование каталога, содержащего несколько файлов.

package com.yandex.kbelyako;

import java.io.File;
import java.io.IOException;
public class Main {
public static void main(String[] args) {
	
MyFileFilter mFF = new MyFileFilter("docx", "pdf");

File root = new File(".");
File copySource = new File("CopySource");
File copyTarget = new File("CopyTarget");
//File testFile = new File(copySource,"Test.txt");

//try {
//	testFile.createNewFile();
//} catch (IOException e1) {
//	// TODO Auto-generated catch block
//	e1.printStackTrace();
//}

FileOperation.list(root); 

//copySource.mkdirs();
//copyTarget.mkdirs();

FileOperation.list(root);

System.out.println("Before copy target:");
FileOperation.list(copyTarget);

//try {
//	FileOperation.copyFilesExt(copySource, copyTarget, "txt");
//} catch (IOException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//	
//}

//SingleThreadCopy testCopy = new SingleThreadCopy (copySource, copyTarget);
//SingleThreadCopy testCopy = new SingleThreadCopy (copySource, copyTarget);

copyFilesMultiThread(copySource, copyTarget, 4);


System.out.println("After copy, target:");
FileOperation.list(copyTarget);


//copyedTestFile.delete();

}

public static void copyFilesMultiThread(File inPath, File outPath, int threadNumber) {
	File[] fileList = inPath.listFiles();
	if (fileList.length!=0) {
		
		if (fileList.length<=threadNumber) threadNumber=fileList.length;
		
			System.out.println("Copy in progress");
			SingleThreadCopy[] threadarray = new SingleThreadCopy[threadNumber];		
			for (int i = 0; i < threadarray.length; i++) {
				int size = fileList.length / threadNumber;
				int begin = size * i;
				int end = ((i + 1) * size);
				if ((fileList.length - end) < size) {
					end = fileList.length;
				}
				threadarray[i] = new SingleThreadCopy(inPath, outPath, begin, end);

			}
			
			System.out.println("Copy finished");
				
	}
	else
	
	
	{System.out.println("Directory is eampty - Nothing to copy!!!");
	
	}
	
}
}