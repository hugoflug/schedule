package se.kth.hugosa.schedule;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    public Constraints loadConstraints(String jsonFile) throws IOException, ParseException {
        List<ScheduleElement> scheduleElements = new ArrayList<ScheduleElement>();
        List<Classroom> roomList = new ArrayList<Classroom>();

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(jsonFile);

        JSONObject o = (JSONObject)parser.parse(reader);
        JSONArray programs = (JSONArray)o.get("programs");

        for (Object a : programs) {
            JSONObject program = (JSONObject)a;
            String programName = (String)program.get("program");

            JSONArray courses = (JSONArray)program.get("courses");

            for (Object c : courses) {
                JSONObject course = (JSONObject)c;
                String courseName = (String)course.get("name");
                JSONArray lessons = (JSONArray)course.get("lessons");

                for (Object l : lessons) {
                    JSONObject lesson = (JSONObject)l;
                    String teacherName = (String)lesson.get("teacher");
                    int capacity = ((Long)lesson.get("capacity")).intValue();

                    scheduleElements.add(new ScheduleElement(teacherName, courseName, programName, capacity));
                }
            }
        }
        
        JSONArray classrooms = (JSONArray)o.get("classrooms");
        for (Object r : classrooms) {
        	JSONObject classroom = (JSONObject)r;
        	String name = (String)classroom.get("name");
        	int capacity = ((Long)classroom.get("capacity")).intValue();
        	roomList.add(new Classroom(name, capacity));
        }

        Constraints constraints = new Constraints(scheduleElements, roomList);
        return constraints;
    }
}
