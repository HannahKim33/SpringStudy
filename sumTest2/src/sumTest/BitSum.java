package sumTest;

public class BitSum {
	public static int getSum(int n) {
		int tot=0;
		for(int i=1;i<n;i++) {
			tot+=i;
		}
		return tot;
	}
}
