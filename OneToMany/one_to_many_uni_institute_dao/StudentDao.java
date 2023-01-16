package one_to_many_uni_institute_dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import one_to_many_uni_institute_dto.Students;

public class StudentDao {
	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vinod");
		return entityManagerFactory.createEntityManager();
	}
	public void insertSchool(Students student) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(student);
		entityTransaction.commit();
		System.out.println("Insertion successfull...!!");
	}
	public void updateSchool(int id , Students student) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Students recievedStudent = entityManager.find(Students.class, id);
		if(recievedStudent!=null) {
			entityTransaction.begin();
			student.setId(id);
			entityManager.merge(student);
			entityTransaction.commit();
		}
		else {
			System.out.println("No student in given id");
		}
	}
	public void deleteSchool(int id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Students recievedStudent = entityManager.find(Students.class, id);
		if(recievedStudent!=null) {
			entityTransaction.begin();
			entityManager.remove(recievedStudent);
			entityTransaction.commit();
		}
		else {
			System.out.println("School not present for given id ");
		}
	}
	public void fetchSchool(int id) {
		EntityManager entityManager = getEntityManager();
		Students recievedSchool = entityManager.find(Students.class, id);
		System.out.println(recievedSchool);
	}
	public void fetchAllSchools() {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createQuery("select s from School s");
		List <Students> list = query.getResultList();
		System.out.println(list);
	}
}
