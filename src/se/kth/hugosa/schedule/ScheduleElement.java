package se.kth.hugosa.schedule;

public class ScheduleElement {
	public String teacher;
	public String course;
	public String program;
	public int numStudents;
	
	public ScheduleElement(String teacher, String course, String program, int numStudents) {
		this.teacher = teacher;
		this.course = course;
		this.program = program;
		this.numStudents = numStudents;
	}
	
}
