package account_manager;

public class EntryPoint {

	public static void main(String[] args) {
		Account ayy = new Account("Firefox");
		ayy.generatePassword(11);
		System.out.println(ayy.toString());

	}

}
