package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public void insert(Department obj) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("INSERT INTO department (`Name`) VALUES(?);", 
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, obj.getName());
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					obj.setId(rs.getInt(1));
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows afftected");
			}
		}
		catch(SQLException ex) {
			throw new DbException(ex.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Department obj) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("UPDATE department SET `Name` = ? WHERE Id = ?;");
			
			ps.setString(1, obj.getName());
			ps.setInt(2, obj.getId());
			
			ps.executeUpdate();
		}
		catch(SQLException ex) {
			throw new DbException(ex.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("DELETE FROM department WHERE Id = ?;");
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
		}
		catch(Exception ex) {
			throw new DbException(ex.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("SELECT Id, `Name` FROM department WHERE Id = ?;");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				Department department = instantiateDepartment(rs);
				return department;
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

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("Id"));
		department.setName(rs.getString("Name"));
		return department;
	}
}
