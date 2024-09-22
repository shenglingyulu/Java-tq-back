import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int k=2;
        while(n>=k) {
            if(n==k) {
                System.out.println(k);
                break;
            }else if (n%k==0) {
                System.out.println(k);
                n=n/k;
            }else {
                k++;
            }
        }
    }
}