package student;

public class Student {
	private String name;
	private double average;
	
	public Student(String name, double average) {
		this.name = name;
		
		if (average > 0.0) {
			if (average <= 100.0) {
				this.average = average;
			}
		}
	}
	public String getLetterGrade() {
		String letterGrade ="";
		
		if (average >= 90)
			letterGrade = "A";
		return letterGrade;
	}

}
