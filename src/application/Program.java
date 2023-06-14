package application;

import java.util.ArrayList;
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
		Department depTest = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(depTest);
		for(Seller obj : list) {
			System.out.println(obj);
		}
	}
}
