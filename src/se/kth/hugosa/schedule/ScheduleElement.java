package se.kth.hugosa.schedule;

public class ScheduleElement {
    private String teacher;
	private String course;
	private String program;
	private int numStudents;
	
	public ScheduleElement(String teacher, String course, String program, int numStudents) {
		this.teacher = teacher;
		this.course = course;
		this.program = program;
		this.numStudents = numStudents;
	}

    public String getTeacher() {
        return teacher;
    }

    public String getCourse() {
        return course;
    }

    public String getProgram() {
        return program;
    }

    public int getNumStudents() {
        return numStudents;
    }

    @Override
    public String toString() {
        return program + " " + course + " " + teacher + " " + numStudents;
    }
}
