package gradebook;

public class GradeBook {
	private String courseName;
	private int[][] grades;
	
	public GradeBook(String name, int[][] gradesArray) {
		setCourseName(name);
		setGrades(gradesArray);
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int[][] getGrades() {
		return grades;
	}

	public void setGrades(int[][] grades) {
		this.grades = grades;
	}
	
	public int getMinimum() {
		int lowGrade = grades [0][0];
		
		for(int[] studentGrades : grades) {
			for (int grade : studentGrades) {
					if (grade < lowGrade)
						lowGrade = grade;
					
			}
	}
		return lowGrade;
	}

}
