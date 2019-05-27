//4) Реализуйте многопоточное копирование каталога, содержащего несколько файлов.

package com.yandex.kbelyako;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
public class Main {
public static void main(String[] args) {
	

File root = new File(".");
File copySource = new File("CopySource");
File copyTarget = new File("CopyTarget");


for (int i = 1; i < 35; i++) {
	try (PrintWriter a = new PrintWriter("CopySource/test"+i+".txt")) {
		for (int j=0;j<i*i;j++) {
			a.println("White Rabbit "+i);
		}
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}


FileOperation.list(root); 

//copySource.mkdirs();
//copyTarget.mkdirs();

FileOperation.list(root);

System.out.println("Before copy target:");
FileOperation.list(copyTarget);


copyFilesMultiThread(copySource, copyTarget, 4);


System.out.println("After copy, target:");
FileOperation.list(copyTarget);


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
			for (int i = 0; i < threadarray.length; i++) {
				try {
					threadarray[i].getThr().join();
				} catch (InterruptedException e) {
					System.out.println(e);
				}				
			}
			System.out.println("Copy finished");				
	}
	else	
	{System.out.println("Directory is eampty - Nothing to copy!!!");
	
	}
	
}
}