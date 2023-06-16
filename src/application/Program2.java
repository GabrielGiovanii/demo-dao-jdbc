package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("\n===  TESTE 1: department insert ===");
		Department department = new Department(null, "Manutenção");
		departmentDao.insert(department);
		System.out.println("Inserted! New id = " + department.getId());
		
		System.out.println("\n===  TESTE 2: department update ===");
		Department updateDepartment = new Department(7, "Diretoria");
		departmentDao.update(updateDepartment);
		
		System.out.println("\n===  TESTE 3: department deleteById ===");
		Scanner sc = new Scanner(System.in);
		System.out.printf("Enter id for delete test: ");
		int idForDelete = sc.nextInt();
		departmentDao.deleteById(idForDelete);
		System.out.println("All right!");
		
		sc.next();
	}

}
