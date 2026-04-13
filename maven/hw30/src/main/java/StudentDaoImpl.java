import entities.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class StudentDaoImpl implements GenericDao<Student, Long> {
    private EntityManagerFactory emf;


    @Override
    public void save(Student entity) {
        Objects.requireNonNull(entity, "Parameter [entity] must not be null!");
        EntityManager entityManager = this.emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new RuntimeException("Cannot save student %s".formatted(entity), ex);
        }
    }

    ;

    @Override
    public Student findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("from Student s where s.id = :id", Student.class)
                    .setParameter("id", id).getSingleResult();
        }
    }


    ;

    @Override
    public Student findByEmail(String email) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("from Student s where s.email = :email", Student.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }
    }

    ;

    @Override
    public List<Student> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("from Student s", Student.class)
                    .getResultList();
        }
    }

    ;


    @Override
    public Student update(Student entity) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Student updated = em.merge(entity);
            tx.commit();
            return updated;
        } catch (Exception ex) {
            tx.rollback();
            throw new RuntimeException("Cannot update student %s".formatted(entity), ex);
        } finally {
            em.close();
        }
    }

    ;

    @Override
    public boolean deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Student student = em.find(Student.class, id);
            if (student == null) return false;
//            int deleted = em.createQuery("delete from Student s where s.id = :id")
//                    .setParameter("id", id)
//                    .executeUpdate();
            em.remove(student);
            tx.commit();
            return true;
        } catch (Exception ex) {
            tx.rollback();
            throw new RuntimeException("Cannot delete student with id %d".formatted(id), ex);
        } finally {
            em.close();
        }
    }

    ;
}
