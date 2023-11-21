package JNI;

import java.util.Scanner;
public class JNI {
	public native void JniAdd(int no1,int no2);
	public native void JniSub(int no1,int no2);
	public native void JniMult(int no1,int no2);
	public native void JniDiv(double no1,double no2);
	public native void JniPow(int no1,int no2);
	public native void JniSqrt(int no1);
	public native void JniMod(int no1,int no2);
	static { System.load("C:\\Users\\eclipse-workspace\\JNI\\src\\JNI\\libJNI.dll");}
	public static void main(String[] args)throws Exception {
		int no1,no2;
		 Scanner in=new Scanner(System.in);
		 JNI MJ=new JNI();
		 System.out.println("JNI using C");
		 System.out.print("Enter first number: ");
		 no1=in.nextInt(); 
		 double no1f=no1;
		 System.out.print("Enter second number: ");
		 no2=in.nextInt();
		 MJ.JniAdd(no1,no2);
		 MJ.JniSub(no1,no2);
		 MJ.JniMult(no1,no2);
		 MJ.JniDiv((double)no1,(double)no2);
		 MJ.JniPow(no1,no2);
		 MJ.JniSqrt(no2);
		 MJ.JniMod(no1,no2);

	}

}

// Microsoft Windows [Version 10.0.22621.674]
// (c) Microsoft Corporation. All rights reserved.

// javac file.java
// javah -jni Cal_JNI
// gcc -I <path of jni find using locate jni.h command upto include> -I <path of jni_md.h> cal.c  -o libCal.so -shared cal.c
// java -Djava.library.path=. cal_JNI

// C:\Users\Desktop\Practical\LP1\DLL>javac -h . JNI.java C:\Users\Desktop\Practical\LP1\DLL> gcc -o libJNI.dll -shared -fPIC -I"C:\Program Files\Java\jdk-18.0.1.1\include" -I"C:\Program Files\Java\jdk-18.0.1.1\include\win32"

// JNI using C
// Enter first number: 21
// Enter second number: 15 
// Addition of nos.= 36 
// Subtraction of nos. is= 6
// Multiplication of nos. is= 315
//  Division of nos. is= 1.400
// Power is= 68122318582951682000.000
// Square root 15 is= 3.873
// Remainder is= 6.000
