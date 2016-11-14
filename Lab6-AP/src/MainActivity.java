
public class MainActivity {

	public static void main(String[] args){
		int A[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		int T[][]={{0,3,1,5},{0,4,2,6},{0,2,1,4},{0,3,2,4}};
		int E[]={1,1,1};
		int L[]={2,2,2};
		carAssembly acc = new carAssembly();
		
		System.out.println("------ UNIT TEST----- \n");
		System.out.println("with Dynamic Programming:- ");
		System.out.println("Expected value = 13, calculated value through code: "+acc.carAssembly_DP(A,T,E,L));
		System.out.println("\nwith Recursion:- ");
		System.out.println("Expected value = 13, calculated value through code: "+acc.carAssembly_R(A,T,E,L));
		
	}
}
