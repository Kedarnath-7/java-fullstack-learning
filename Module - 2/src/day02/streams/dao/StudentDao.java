package day02.streams.dao;

import day02.streams.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    public void maxMarksPerSub();
    public void minMarksPerSub();
    public void topperPerSub();
    public void totalTopper();
    public void avgMarksPerSub();
    public List<Student> aboveAvgPhys();
    public void saveStudent(Student student);
    public void deleteAll();

    public List<Student> getStudents();
}
