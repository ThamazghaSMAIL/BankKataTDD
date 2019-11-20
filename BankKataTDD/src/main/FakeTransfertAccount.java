package main;

public class FakeTransfertAccount implements TransfertAccount{

	@Override
	public int transferPost(String ibanFrom, String ibanTo, Integer amount) {
		if (amount != null && ibanFrom != null && ibanTo != null)
			if(amount >= 0)
				return 202;

		return 400;
	}
}
