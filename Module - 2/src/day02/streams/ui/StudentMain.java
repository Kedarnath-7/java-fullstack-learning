package day02.streams.ui;

import day02.streams.dao.StudentDao;
import day02.streams.dao.StudentDaoImpl;
import day02.streams.entity.Student;

import java.util.function.Consumer;

public class StudentMain {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDaoImpl();
        studentDao.saveStudent(new Student("Tony", 89, 76, 92, 67, 80));
        studentDao.saveStudent(new Student("Peter", 93, 89, 90, 73, 85));
        studentDao.saveStudent(new Student("Steve", 60, 63, 73, 88, 89));
        studentDao.saveStudent(new Student("Jack", 90, 70, 80, 70, 90));

        System.out.println("Max per sub: ");
        studentDao.maxMarksPerSub();
        System.out.println("==============");
        System.out.println("Min per sub");
        studentDao.minMarksPerSub();
        System.out.println("============");
        System.out.println("Topper per sub: ");
        studentDao.topperPerSub();
        System.out.println("==============");
        System.out.println("Avg per sub ");
        studentDao.avgMarksPerSub();
        System.out.println("================");



    }
}
