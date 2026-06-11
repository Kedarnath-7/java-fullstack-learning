package day02.streams.dao;

import day02.streams.entity.Student;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentDaoImpl implements StudentDao {
    private final List<Student> students = new LinkedList<>();
    private double getMarks(Student s, String sub){
        return switch (sub.toLowerCase()){
            case "phys"->s.getPhys();
            case "chem"->s.getChem();
            case "math"-> s.getMath();
            case "hist"->s.getHist();
            case "geog"->s.getGeog();
            default->throw new  IllegalArgumentException("Invalid subject");

        };
    }
    private static String[] subjects = {"phys","chem","math","hist","geog"};
    @Override
    public void maxMarksPerSub() {
        for (String sub : subjects) {
            System.out.println("Max marks per sub " + sub + ":");
            //System.out.println(this.students.stream().max(Comparator.comparingDouble(s -> getMarks(s, sub))));
            System.out.println(this.students.stream().mapToDouble(s->getMarks(s, sub)).max().orElse(0.0));
        }
    }

    @Override
    public void minMarksPerSub() {
        for (String sub : subjects) {
            System.out.println("Min marks per sub " + sub + ":");
            //System.out.println(this.students.stream().min(Comparator.comparingDouble(s -> getMarks(s, sub))));
            System.out.println(this.students.stream().mapToDouble(s->getMarks(s, sub)).min().orElse(0.0));
        }
    }

    @Override
    public void topperPerSub() {
        for (String sub : subjects) {
            System.out.println("Topper marks per sub " + sub + ":");
            System.out.println(this.students.stream().max(Comparator.comparingDouble(s -> getMarks(s, sub))));
        }
    }

    @Override
    public void totalTopper() {

    }

    @Override
    public void avgMarksPerSub() {
        for(String sub : subjects) {
            System.out.println("Average marks per sub " + sub + ":");
            System.out.println(this.students.stream().mapToDouble(s->getMarks(s, sub)).average().orElse(0.0));
        }
    }

    @Override
    public List<Student> aboveAvgPhys() {
        return List.of();
    }

    @Override
    public void saveStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public void deleteAll() {
        this.students.clear();
    }

    @Override
    public List<Student> getStudents() {
        return this.students;
    }
}
