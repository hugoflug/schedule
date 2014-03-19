package se.kth.hugosa.schedule;

import java.util.ArrayList;
import java.util.Map;

public class Schedule {
	private String program;
    //should perhaps be HashMap<Date, Day>
    public ArrayList<Day> days;

    public Schedule() {}

    public Schedule(String program, int weeks) {
    	this.program = program;
    	this.days = new ArrayList<Day>();
    	for (int i = 0; i < weeks*5; i++) {
    		days.add(new Day());
    	}
    }

    public Schedule copy() {
        Schedule copy = new Schedule();
        copy.program = program;
        copy.days = new ArrayList<Day>();
        for (int i = 0; i < days.size(); i++) {
            Day day = new Day();
            for (int j = 0; j < day.timeSlots.size(); j++) {
                day.timeSlots.set(j, days.get(i).timeSlots.get(j).copy());
            }
            copy.days.add(day);
        }
        return copy;
    }
    
    public String getProgram(){
    	return program;
    }

    public static String schedulesToString(ArrayList<Schedule> schedules) {
        StringBuilder sb = new StringBuilder();
        for (Schedule schedule : schedules){
            sb.append("Program: " + schedule.getProgram() + "\n");
            sb.append("-----------------------\n");
            for (int days = 0; days < schedule.days.size(); days++){
                sb.append("Day " + days + "\n");
                Day day = schedule.days.get(days);
                sb.append("-----------------------\n");
                for (int slots = 0; slots < 4; slots++){
                    boolean slotEmpty = false;
                    sb.append("Slot " + slots + ": ");
                    if (day != null){
                        TimeSlot slot = day.timeSlots.get(slots);
                        if (slot.elementsMap.size()>0) {
                            for(Map.Entry<Classroom, ScheduleElement> e : slot.elementsMap.entrySet()){
                                String classroomName = "";
                                Classroom classroom = e.getKey();
                                if (classroom == null) {
                                    classroomName = "unassigned";
                                } else {
                                    classroomName = classroom.name;
                                }
                                ScheduleElement element = e.getValue();
                                if (element == null) {
                                    slotEmpty = true;
                                } else {
                                    sb.append(element.getCourse() + " with " + element.getTeacher() +" in " + classroomName + ".\n");
                                }
                            }
                        }
                        else{
                            slotEmpty = true;
                        }
                    }
                    else{
                        slotEmpty = true;
                    }

                    if(slotEmpty){
                        sb.append("empty\n");
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public static void printSchedule(ArrayList<Schedule> schedules){
        System.out.println(schedulesToString(schedules));
    }
}
