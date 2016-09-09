//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class NumberGenerator {
//	int count=0;
//	//String[] firstName=new String[100];
//	ArrayList<String> firstName=new ArrayList<String>();
//	ArrayList<String> lastName=new ArrayList<String>();
//	String fileFirstName="names.txt";
//	String fileLastName="lastnames.txt";
//	Scanner file1=new Scanner(new File(fileFirstName));
//	Scanner file2=new Scanner(new File(fileLastName));
//	while(file1.hasNextLine()) {
//		String line = file1.nextLine();
//		firstName.add(line);
//		
//	}
//	while(file2.hasNextLine()){
//		String line=file2.nextLine();
//		lastName.add(line);
//	}
///*	for(int i=0; i<100; i++){
//		String name=firstName.get(i);
//		System.out.println(name);
//	}*/
//	for(int i=0; i<1000; i++){
//		String name=lastName.get(i);
//		System.out.println(name);
//		count++;
//		
//	}
//}
//}
