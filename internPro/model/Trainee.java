package internPro.model;

public class Trainee {
    private int id;
    private String name;
    private String workshop;
    private String course;
    private String startDate;
    private String endDate;
    private String percent;
    private String amount;
    private String status;

    public Trainee() {
    }
    
    public Trainee(String name, String workshop, String course, String startDate, String endDate, String percent,
			String amount, String status) {
		super();
		this.name = name;
		this.workshop = workshop;
		this.course = course;
		this.startDate = startDate;
		this.endDate = endDate;
		this.percent = percent;
		this.amount = amount;
		this.status = status;
	}
	

    public Trainee(int id, String name, String workshop, String course, String startDate, String endDate,
			String percent, String amount, String status) {
		super();
		this.id = id;
		this.name = name;
		this.workshop = workshop;
		this.course = course;
		this.startDate = startDate;
		this.endDate = endDate;
		this.percent = percent;
		this.amount = amount;
		this.status = status;
	}

    // Getters and Setters

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

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
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
