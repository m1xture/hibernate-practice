import entities.Homework;
import entities.Student;
import jakarta.persistence.*;

void main() {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hillel-persistence-unit");

    Homework hw = new Homework();
    hw.setDescription("Create simple Spring Boot application for airlines");
    hw.setDeadline(LocalDate.of(2026, Month.APRIL, 26));


    Student ivan = new Student();
    ivan.setFirstName("Ivan");
    ivan.setLastName("Petrov");
    ivan.setEmail("ivanpertov89@mail.com");
    ivan.addHomework(hw);

    var studentDao = new StudentDaoImpl(emf);
    studentDao.save(ivan);
    System.out.println("Ivan's ID after save: " + ivan.getId());
    System.out.println("Homework ID: " + hw.getId());


    System.out.println("Here is the student found by id: " + studentDao.findById(ivan.getId()).toString());
    System.out.println("Here are all the students: " + studentDao.findAll().toString());

    ivan.setLastName("Smith");
    studentDao.update(ivan);


    Student ivanFoundById = studentDao.findByEmail("ivanpertov89@mail.com");
    System.out.println(ivanFoundById.toString());

    boolean isDeleted = studentDao.deleteById(ivan.getId());
    System.out.printf("\nWas Ivan(id=%d) deleted? " + (isDeleted ? "yes" : "no"), ivan.getId());
}