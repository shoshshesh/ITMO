package info.kgeorgiy.ja.bondarev.student;

import info.kgeorgiy.java.advanced.student.GroupName;
import info.kgeorgiy.java.advanced.student.Student;
import info.kgeorgiy.java.advanced.student.StudentQuery;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentDB implements StudentQuery {

    private <T> Stream<T> getStream(Function<Student, T> f, List<Student> students) {
        return students.stream().map(f);
    }

    private <T> List<T> getList(Stream<T> stream) {
        return stream.collect(Collectors.toList());
    }

    private <T> TreeSet<T> getTreeSet(Stream<T> stream) {
        return stream.collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public List<String> getFirstNames(List<Student> students) {
        return getList(getStream(Student::getFirstName, students));
    }

    @Override
    public List<String> getLastNames(List<Student> students) {
        return getList(getStream(Student::getLastName, students));
    }

    @Override
    public List<GroupName> getGroups(List<Student> students) {
        return getList(getStream(Student::getGroup, students));
    }

    @Override
    public List<String> getFullNames(List<Student> students) {
        return getList(getStream((Student student) -> student.getFirstName() + " " + student.getLastName(), students));
    }

    @Override
    public Set<String> getDistinctFirstNames(List<Student> students) {
        return getTreeSet(getStream(Student::getFirstName, students));
    }

    @Override
    public String getMaxStudentFirstName(List<Student> students) {
        return students.stream().max(Student::compareTo).map(Student::getFirstName).orElse("");
    }

    private List<Student> sortBy(Comparator<? super Student> comparator, Collection<Student> students) {
        return getList(students.stream().sorted(comparator));
    }

    @Override
    public List<Student> sortStudentsById(Collection<Student> students) {
        return sortBy(Comparator.comparing(Student::getId), students);
    }

    @Override
    public List<Student> sortStudentsByName(Collection<Student> students) {
        return sortBy(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName).reversed()
                .thenComparing(Student::compareTo), students);
    }

    private List<Student> findBy(Predicate<Student> predicate, Collection<Student> students) {
        return sortStudentsByName(getList(students.stream().filter(predicate)));
    }

    @Override
    public List<Student> findStudentsByFirstName(Collection<Student> students, String name) {
        return findBy(student -> student.getFirstName().equals(name), students);
    }

    @Override
    public List<Student> findStudentsByLastName(Collection<Student> students, String name) {
        return findBy(student -> student.getLastName().equals(name), students);
    }

    @Override
    public List<Student> findStudentsByGroup(Collection<Student> students, GroupName group) {
        return findBy(student -> student.getGroup().equals(group), students);
    }

    @Override
    public Map<String, String> findStudentNamesByGroup(Collection<Student> students, GroupName group) {
        return findStudentsByGroup(students, group)
                .stream().collect(Collectors.toMap(
                        Student::getLastName,
                        Student::getFirstName,
                        BinaryOperator.minBy(String::compareTo))
                );
    }
}