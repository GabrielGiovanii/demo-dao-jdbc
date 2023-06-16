package application;

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
	}

}
