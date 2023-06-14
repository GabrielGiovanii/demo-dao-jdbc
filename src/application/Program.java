package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		Department depTest = new Department(3, null);
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
	}
}
