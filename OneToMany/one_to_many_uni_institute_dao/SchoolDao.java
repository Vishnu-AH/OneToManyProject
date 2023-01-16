package one_to_many_uni_institute_dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import one_to_many_uni_institute_dto.School;
import one_to_many_uni_institute_dto.Students;
import one_to_many_uni_institute_dto.Teachers;

public class SchoolDao {
	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vinod");
		return entityManagerFactory.createEntityManager();
	}
	public void insertSchool(School school) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		List<Students> list = school.getStudents();
		List<Teachers> list1 = school.getTeachers();
		entityTransaction.begin();
		for (Students students : list) {
			entityManager.persist(students);
		}
		for (Teachers teachers : list1) {
			entityManager.persist(teachers);
		}
		entityManager.persist(school);
		entityTransaction.commit();
		System.out.println("Insertion successfull...!!");
	}
	public void updateSchool(int id , School school) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		School recievedSchool = entityManager.find(School.class, id);
		if(recievedSchool!=null) {
			entityTransaction.begin();
			school.setId(id);
			school.setStudents(school.getStudents());
			school.setTeachers(school.getTeachers());
			entityManager.merge(school);
			entityTransaction.commit();
		}
		else {
			System.out.println("No school in given id");
		}
	}
	public void deleteSchool(int id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		School recievedSchool = entityManager.find(School.class, id);
		if(recievedSchool!=null) {
			entityTransaction.begin();
			entityManager.remove(recievedSchool);
			entityTransaction.commit();
		}
		else {
			System.out.println("School not present for given id ");
		}
	}
	public void fetchSchool(int id) {
		EntityManager entityManager = getEntityManager();
		School recievedSchool = entityManager.find(School.class, id);
		System.out.println(recievedSchool);
	}
	public void fetchAllSchools() {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createQuery("select s from School s");
		List <School> list = query.getResultList();
		System.out.println(list);
	}
}
