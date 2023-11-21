import java.util.*;
class LRU
{
 public static void main(String args[])
 {
 Scanner sc=new Scanner(System.in);
 System.out.println("ENTER NUMBER OF FRAMES");
 int nf=sc.nextInt();
 System.out.println("ENTER NUMBER OF REFERENCES");
 int nr=sc.nextInt();
 int page[]=new int[nr];
 int frame[]=new int[nr];
 System.out.println("ENTER REFERENCE");
 for(int i=0;i<nr;i++)
 page[i]=sc.nextInt();
 for(int i=0;i<nf;i++)
 frame[i]=-1;
 for(int k=0;k<nf;k++)
 System.out.print(" "+frame[k]);
 System.out.println();
 int flag=0,hit=0,miss=0,front=0,rear=-1;
 int age[]=new int[nf];
 for(int i=0;i<nf;i++)
 age[i]=0;
 for(int j=0,i=0;j<nf && i<nr;j=j%nf,i++)
 {
 flag=0;

 for(int y=0;y<nf;y++)
 {
 if(frame[y]==page[i])
 {
 flag=1;
 hit++;
 age[y]=0;
 }
 }
 if(flag==0)
 if(frame[j]==-1)
 {
 frame[j]=page[i];
 miss++;
 rear++;
 j++;
 }
 else

 {
 int max=age[0],loc=0;
 miss++;
 for(int b=0;b<nf;b++)
 if(age[b]>max)
 {
 max=age[b];loc=b;
 }
 frame[loc]=page[i];
 age[loc]=0;
 j++;
 }
 for(int k=0;k<nf;k++)
 System.out.print(" "+frame[k]);
 System.out.println();
 for(int a=0;a<nf;a++)
 {
 if(frame[a]==-1)
 age[a]=0;
 else
 age[a]++;
 }

 /*for(int k=0;k<nf;k++)
 System.out.print(" "+age[k]);
 System.out.println();*/
 }
 float hr=(float)hit/((float)hit+(float)miss);
 System.out.println("Hit="+hit+" miss="+miss);
 System.out.println("page replacement ratio="+hr);
 }
}