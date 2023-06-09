package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("===  TESTE 1: seller findById ===");
		Seller seller = sellerDao.findById(2);
		System.out.println(seller);
		
		System.out.println("\n===  TESTE 2: seller findByDepartment ===");
		Department depTest = new Department(4, null);
		List<Seller> list = sellerDao.findByDepartment(depTest);
		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n===  TESTE 3: seller findAll ===");
		list = sellerDao.findAll();
		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n===  TESTE 4: seller insert ===");
		Seller newSeller = new Seller(null, "Vitor", "vitor@gmail.com", new Date(), 1500.5, depTest);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());
		
		System.out.println("\n===  TESTE 5: seller update ===");
		Seller updateSeller = new Seller(12, "José", "jose@gmail.com", new Date(), 1900.99, depTest);
		sellerDao.update(updateSeller);
		
		System.out.println("\n===  TESTE 6: seller deleteById ===");
		Scanner sc = new Scanner(System.in);
		System.out.printf("Enter id for delete test: ");
		int idForDelete = sc.nextInt();
		sellerDao.deleteById(idForDelete);
		System.out.println("All right!");
		
		sc.next();
	}
}
