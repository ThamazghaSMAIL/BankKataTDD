package main;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BankTest {

	Bank bank;
	FakeTransfertAccount transfertAcount ; 
	FakeDateGenerator fakeDateGenerator ; 

	@Before 
	public void setUp() {
		fakeDateGenerator = new FakeDateGenerator();
		transfertAcount = new FakeTransfertAccount();
		bank = new Bank(fakeDateGenerator,transfertAcount);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturn0() {
		assertTrue(bank.getSolde() == 0);
	}

	@Test
	public void shouldReturnValue500() {
		bank.depot(500);
		assertTrue(bank.getSolde() == 500);
	}

	@Test
	public void shouldReturn0AfterDepot() {
		bank.depot(0);
		assertTrue(bank.getSolde() == 0);
	}

	@Test
	public void shouldReturn0EvenIfDepotIsNegatif() {
		bank.depot(-500);
		assertTrue(bank.getSolde() == 0);
	}

	@Test
	public void shouldReturn0AfterDepotRetrait() {
		bank.depot(500);
		bank.retrait(500);
		assertTrue(bank.getSolde() == 0);
	}

	@Test
	public void shouldReturnNeg500AfterRetrait() {
		bank.retrait(500);
		assertTrue(bank.getSolde() == -500);
	}

	@Test
	public void shouldReturn0AfterRetrait0() {
		bank.retrait(0);
		assertTrue(bank.getSolde() == 0);
	}

	@Test
	public void shouldReturn0AfterRetraitNeg500() {
		bank.retrait(-500);
		assertTrue(bank.getSolde() == 0);
	}

	@Test
	public void shouldReturn500AfterDepotRetrait() {
		bank.depot(1000);
		bank.retrait(500);
		assertTrue(bank.getSolde() == 500);
	}

	@Test
	public void shouldReturnNeg500AfterDepotRetrait() {
		bank.depot(500);
		bank.retrait(1000);
		assertTrue(bank.getSolde() == -500);
	}

	@Test
	public void shouldReturnNeg500AfterDepotRetraitFloat() {
		bank.depot(500.50);
		bank.retrait(1000.50);
		assertTrue(bank.getSolde() == -500);
	}

	@Test
	public void shouldReturn1000AfterDepot() {
		bank.depot(1000);
		bank.depot(1000);
		assertTrue(bank.getSolde() == 2000);
	}

	@Test
	public void shouldReturn1000AfterRetrait() {
		bank.retrait(1000);
		bank.retrait(1000);
		assertTrue(bank.getSolde() == -2000);
	}

	@Test
	public void shouldReturnNullList() {
		assertTrue(bank.getTransactions().isEmpty());
	}

	@Test
	public void shouldReturnTransactionListWhenDepot() {
		bank.depot(1000);

		assertTrue(bank.getTransactions().get(0).getDate().equals("19/11/2019 00:00:04"));
		assertTrue(bank.getTransactions().get(0).getTransaction() == 1000);
		assertTrue(bank.getTransactions().get(0).getSolde() == 1000);
	}

	@Test
	public void shouldReturnTransactionListWhenRetrait() {
		bank.retrait(1000);

		assertTrue(bank.getTransactions().get(0).getDate().equals("19/11/2019 00:00:04"));
		assertTrue(bank.getTransactions().get(0).getTransaction() == -1000);
		assertTrue(bank.getTransactions().get(0).getSolde() == -1000);
	}

	@Test
	public void shouldReturnTransactionListWhenDepotTwice() {
		bank.depot(1000);
		bank.depot(2000);


		assertTrue(bank.getTransactions().get(0).getDate().equals("19/11/2019 00:00:04"));
		assertTrue(bank.getTransactions().get(0).getTransaction() == 1000);
		assertTrue(bank.getTransactions().get(0).getSolde() == 1000);

		assertTrue(bank.getTransactions().get(1).getDate().equals("19/11/2019 00:05:04"));
		assertTrue(bank.getTransactions().get(1).getTransaction() == 2000);
		assertTrue(bank.getTransactions().get(1).getSolde() == 3000);
	}

	@Test
	public void shouldReturnTransactionListSizeIs2WhenDepotTwice() {
		bank.depot(1000);
		bank.depot(2020);
		assertTrue(bank.getTransactions().size() == 2);
	}

	@Test
	public void shouldReturnTransactionListWhenRetraitTwice() {
		bank.retrait(1000);
		bank.retrait(2000);


		assertTrue(bank.getTransactions().get(0).getDate().equals("19/11/2019 00:00:04"));
		assertTrue(bank.getTransactions().get(0).getTransaction() == -1000);
		assertTrue(bank.getTransactions().get(0).getSolde() == -1000);

		assertTrue(bank.getTransactions().get(1).getDate().equals("19/11/2019 00:05:04"));
		assertTrue(bank.getTransactions().get(1).getTransaction() == -2000);
		assertTrue(bank.getTransactions().get(1).getSolde() == -3000);

	}

	@Test
	public void shouldReturnTransactionListWhenRetraitTwicee() {
		bank.retrait(1000);
		bank.retrait(2000);		

		assertTrue(bank.getTransactions().get(0).getDate().equals("19/11/2019 00:00:04"));
		assertTrue(bank.getTransactions().get(0).getTransaction() == -1000);
		assertTrue(bank.getTransactions().get(0).getSolde() == -1000);

		assertTrue(bank.getTransactions().get(1).getDate().equals("19/11/2019 00:05:04"));
		assertTrue(bank.getTransactions().get(1).getTransaction() == -2000);
		assertTrue(bank.getTransactions().get(1).getSolde() == -3000);	
	}


	@Test
	public void shouldReturn202WhenTransfert0ToIbanTo() {
		//Given
		int result = bank.transfert("ibanFrom","ibanTo",0);
		
		//When
		
		//Then
		assertTrue(result==202);
	}
	
	@Test
	public void shouldReturn400WhenTransfertNeg202ToIbanTo() {
		//Given
		int result = bank.transfert("ibanFrom","ibanTo",-202);
		
		//When
		
		//Then
		assertTrue(result==400);
	}
	
	@Test
	public void shouldReturn400WhenAmountNotPresent() {
		//Given
		int result = bank.transfert("ibanFrom","ibanTo",null);
		
		//When
		
		//Then
		assertTrue(result==400);
	}
	
	@Test
	public void shouldReturn400WhenNoFields() {
		//Given
		int result = bank.transfert(null,null,null);
		
		//When
		
		//Then
		assertTrue(result==400);
	}
	
	@Test
	public void shouldReturn400WhenNoIbans() {
		//Given
		int result = bank.transfert(null,null,0);
		
		//When
		
		//Then
		assertTrue(result==400);
	}

	@Test
	public void shouldReturn202WhenTestProd() {
		//Given
		TransfertAccount transfertAccount = new TransfertAccountProd();
		Bank bank = new Bank(fakeDateGenerator, transfertAccount);
		//When
		int result = bank.transfert("iban","ibanTo",0);

		//Then
		assertTrue(result==202);
	}
	
	@Test
	public void shouldReturn400WhenTestProd() {
		//Given
		TransfertAccount transfertAccount = new TransfertAccountProd();
		Bank bank = new Bank(fakeDateGenerator, transfertAccount);
		//When
		int result = bank.transfert("iban","ibanTo",-100);

		//Then
		assertTrue(result==400);
	}
}
