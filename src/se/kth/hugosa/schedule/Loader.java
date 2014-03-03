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
    public Constraints loadConstraints(String jsonFile) throws FileNotFoundException, IOException, ParseException {
        List<ScheduleElement> scheduleElements = new ArrayList<ScheduleElement>();

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(jsonFile);

        JSONArray array = (JSONArray)parser.parse(reader);

        for (Object a : array) {
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
                    int capacity = (Integer)lesson.get("capacity");

                    scheduleElements.add(new ScheduleElement(teacherName, courseName, programName, capacity));
                }
            }
        }

        Constraints constraints = new Constraints(scheduleElements);
        return constraints;
    }
}
