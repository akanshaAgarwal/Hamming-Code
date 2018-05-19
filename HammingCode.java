package hammingCode;

import java.util.Scanner;

public class HammingCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sn=new Scanner(System.in);
		System.out.println("Enter your data");
		String binaryData=sn.nextLine();
		System.out.println("Choose the parity");
		System.out.println("1. even \n2. odd");
		int ch=sn.nextInt();
		int i;
		for(i=0;i+4<=binaryData.length();i=i+4) {
			String fourBit=binaryData.substring(i,i+4);
			System.out.println("\nThe four bits are "+fourBit);
			senderCode(fourBit,ch,sn);
		}
		if(binaryData.length()%4!=0) {
			String bits=binaryData.substring(i,binaryData.length());
			for(int j=bits.length();j<4;j++) {
				bits="0"+bits;
			}
			System.out.println("\nThe four bits are "+bits);
			senderCode(bits, ch, sn);
		}
		
	}

	private static void senderCode(String fourBit, int ch, Scanner sn) {
		// TODO Auto-generated method stub
		
		int sevenBits[]=new int[8];
		sevenBits[3]=(int)fourBit.charAt(0)-48;
		sevenBits[5]=(int)fourBit.charAt(1)-48;
		sevenBits[6]=(int)fourBit.charAt(2)-48;
		sevenBits[7]=(int)fourBit.charAt(3)-48;
		if(ch==1) {
			if((sevenBits[3]+sevenBits[5]+sevenBits[7])%2==0) {
				sevenBits[1]=0;
			}
			else {
				sevenBits[1]=1;
			}
			if((sevenBits[3]+sevenBits[6]+sevenBits[7])%2==0) {
				sevenBits[2]=0;
			}
			else {
				sevenBits[2]=1;
			}
			if((sevenBits[5]+sevenBits[6]+sevenBits[7])%2==0) {
				sevenBits[4]=0;
			}
			else {
				sevenBits[4]=1;
			}
		}
		else {
			if((sevenBits[3]+sevenBits[5]+sevenBits[7])%2==0) {
				sevenBits[1]=1;
			}
			else {
				sevenBits[1]=0;
			}
			if((sevenBits[3]+sevenBits[6]+sevenBits[7])%2==0) {
				sevenBits[2]=1;
			}
			else {
				sevenBits[2]=0;
			}
			if((sevenBits[5]+sevenBits[6]+sevenBits[7])%2==0) {
				sevenBits[4]=1;
			}
			else {
				sevenBits[4]=0;
			}
		}
		System.out.print("The sent data is ");
		for(int i=1;i<=7;i++) {
			System.out.print(sevenBits[i]);
		}System.out.println();
		System.out.println("Enter the probability of an error to occur");
		double p=sn.nextFloat();
		// random value for probability
		double randProb=Math.random();
		// random bit position
		int randBitPos=0;
		while(randBitPos<1 || randBitPos>7) {
			randBitPos=(int)Math.ceil(Math.random()*10);
			//System.out.println(randBitPos);
		}
		//System.out.println("random prob = "+randProb);
		//System.out.println("random bit pos = "+randBitPos);
		if (randProb<=p) {
			if(sevenBits[randBitPos]==1) {
				sevenBits[randBitPos]=0;
			}
			else {
				sevenBits[randBitPos]=1;
			}
		}
		System.out.print("The recieved data is ");
		for(int i=1;i<=7;i++) {
			System.out.print(sevenBits[i]);
		}System.out.println();
		
		//check for error
		String parityCode="";
		if(ch==1) {
			if((sevenBits[1]+sevenBits[3]+sevenBits[5]+sevenBits[7])%2==0) {
				parityCode+="0";
			}else {
				parityCode+="1";
			}
			if((sevenBits[2]+sevenBits[3]+sevenBits[6]+sevenBits[7])%2==0) {
				parityCode="0"+parityCode;
			}else {
				parityCode="1"+parityCode;
			}
			if((sevenBits[4]+sevenBits[5]+sevenBits[6]+sevenBits[7])%2==0) {
				parityCode="0"+parityCode;
			}else {
				parityCode="1"+parityCode;
			}
		}
		else {
			if((sevenBits[1]+sevenBits[3]+sevenBits[5]+sevenBits[7])%2==0) {
				parityCode+="1";
			}else {
				parityCode+="0";
			}
			if((sevenBits[2]+sevenBits[3]+sevenBits[6]+sevenBits[7])%2==0) {
				parityCode="1"+parityCode;
			}else {
				parityCode="0"+parityCode;
			}
			if((sevenBits[4]+sevenBits[5]+sevenBits[6]+sevenBits[7])%2==0) {
				parityCode="1"+parityCode;
			}else {
				parityCode="0"+parityCode;
			}
		}
		int decimalValue = Integer.parseInt(parityCode, 2);
		if(decimalValue==0) {
			System.out.println("No error found");
		}
		else {
			System.out.println("The error is found at position "+decimalValue);
			if(sevenBits[decimalValue]==1) {
				sevenBits[decimalValue]=0;
			}
			else {
				sevenBits[decimalValue]=1;
			}
			System.out.print("The corrected codeword is ");
			for(int i=1;i<=7;i++) {
				System.out.print(sevenBits[i]);
			}System.out.println();
		}
		
	}

}
