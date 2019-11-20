package main;

public class Transaction {

	public String date;
	public double transaction;
	public double solde;
	
	public Transaction(String date, double transaction, double solde) {
		super();
		this.date = date;
		this.transaction = transaction;
		this.solde = solde;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getTransaction() {
		return transaction;
	}

	public void setTransaction(double transaction) {
		this.transaction = transaction;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
	

}
