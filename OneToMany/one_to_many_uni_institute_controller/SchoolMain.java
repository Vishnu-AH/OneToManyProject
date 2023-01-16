package one_to_many_uni_institute_controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import one_to_many_uni_institute_dao.SchoolDao;
import one_to_many_uni_institute_dto.School;
import one_to_many_uni_institute_dto.Students;
import one_to_many_uni_institute_dto.Teachers;

public class SchoolMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vinod");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		Scanner scanner = new Scanner(System.in);
		SchoolDao schoolDao = new SchoolDao();
		Students students = new Students();
		Teachers teachers = new Teachers();
		School school = new School();
		boolean condition =true;
		boolean condition1 = true;
		do {
			System.out.println("1 : Add school");
			System.out.println("2 : Update school");
			System.out.println("3 : Delete school");
			System.out.println("4 : Fetch single school");
			System.out.println("5 : Fetch all school");
			System.out.print("Enter your choice : ");
			int choice = scanner.nextInt();
			switch(choice) {
			case 1 : {
				System.out.print("Enter school name : ");
				String schoolName = scanner.next();
				System.out.print("Enter school address : ");
				String schoolAddress = scanner.next();
				System.out.print("Enter school phone number : ");
				long schoolPhone = scanner.nextLong();
				
				school.setName(schoolName);
				school.setAddress(schoolAddress);
				school.setPhone(schoolPhone);

				do {
					System.out.println("1 : Enter student details");
					System.out.println("2 : Enter teacher details");
					System.out.println("3 : Exit");
					System.out.print("Enter your choice : ");
					int choice1 = scanner.nextInt();
					switch(choice1) {
					case 1 : {
						System.out.print("Enter student name : ");
						String studentName = scanner.next();
						System.out.print("Enter student address : ");
						String studentAddress = scanner.next();
						System.out.print("Enter student phone number : ");
						long studentPhone = scanner.nextLong();
						
						students.setName(studentName);
						students.setAddress(studentAddress);
						students.setPhone(studentPhone);
						List <Students> list = new ArrayList<Students>();
						list.add(students);
						
						school.setStudents(list);
						
					}
					break;
					case 2 : {
						System.out.print("Enter teacher name : ");
						String teacherName = scanner.next();
						System.out.print("Enter teacher subject : ");
						String teacherSubject = scanner.next();
						System.out.print("Enter teacher phone number : ");
						long teacherPhone = scanner.nextLong();
						
						teachers.setName(teacherName);
						teachers.setSubject(teacherSubject);
						teachers.setPhone(teacherPhone);
						List <Teachers> list1 = new ArrayList<Teachers>();
						list1.add(teachers);
						
						school.setTeachers(list1);
					}
					case 3 : {
						condition1 = false;
					}
					break;
					}
					
				}while(condition1);
				schoolDao.insertSchool(school);
			}
			break;
			case 2 : {
				
			}
			}
		}while(condition);
		
	}

}
