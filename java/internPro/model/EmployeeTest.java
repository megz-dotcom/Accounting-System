package internPro.model;

public class EmployeeTest {
	private int id;
	private String name;
	private String payment_date;
	private String bank;
	private String ifsc;
	private String account;
	private String amount;
	private String status;

	public EmployeeTest() {
	}
	
	public EmployeeTest(String name, String payment_date, String bank, String ifsc, String account, 
			String amount, String status) {
		super();
		this.name = name;
		this.payment_date = payment_date;
		this.bank = bank;
		this.ifsc = ifsc;
		this.account = account;
		this.amount = amount;
		this.status = status;
	}

	public EmployeeTest(int id, String name, String payment_date, String bank, String ifsc, 
			String account, String amount, String status) {
		super();
		this.id = id;
		this.name = name;
		this.payment_date = payment_date;
		this.bank = bank;
		this.ifsc = ifsc;
		this.account = account;
		this.amount = amount;
		this.status = status;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}


	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}


