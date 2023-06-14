package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT s.Id, s.`Name`, s.Email, s.BirthDate, s.BaseSalary, s.DepartmentId,");
			sb.append("d.`Name` AS DepartmentName FROM seller AS s ");
			sb.append("INNER JOIN department AS d ");
			sb.append("ON s.DepartmentId = d.Id ");
			sb.append("WHERE s.Id = ?;");
			
			ps = conn.prepareStatement(sb.toString());
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				Department department = instantiateDepartment(rs);
				
				Seller seller = instantiateSeller(rs, department);
				return seller;
			}
			return null;
		}
		catch(SQLException ex) {
			throw new DbException(ex.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
		Seller seller = new Seller();
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(department);
		seller.setEmail(rs.getString("Email"));
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("DepartmentName"));
		return department;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT s.Id, s.`Name`, s.Email, s.BirthDate, s.BaseSalary,");
			sb.append("s.DepartmentId, d.`Name` AS DepartmentName ");
			sb.append("FROM seller AS s ");
			sb.append("INNER JOIN department AS d ");
			sb.append("ON s.DepartmentId = d.Id ");
			sb.append("WHERE s.DepartmentId = ?;"); 
		
			ps = conn.prepareStatement(sb.toString());
			ps.setInt(1, department.getId());
			
			rs = ps.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller seller = instantiateSeller(rs, dep);
				list.add(seller);
			}
			return list;
		}
		catch(Exception ex) {
			throw new DbException(ex.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}

}
