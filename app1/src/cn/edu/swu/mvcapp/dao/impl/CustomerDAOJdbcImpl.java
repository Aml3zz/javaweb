 package cn.edu.swu.mvcapp.dao.impl;

import java.util.List;

import cn.edu.swu.mvcapp.dao.CriteriaCustomer;
import cn.edu.swu.mvcapp.dao.CustomerDAO;
import cn.edu.swu.mvcapp.dao.DAO;
import cn.edu.swu.mvcapp.domain.Customer;

public class CustomerDAOJdbcImpl extends DAO<Customer> implements CustomerDAO {

	@Override
	public List<Customer> getAll() {
		String sql = "SELECT id,name,address,phone FROM customers";
		return getForList(sql);
	}

	@Override
	public void save(Customer customer) {
		String sql = "INSERT INTO customers(name,address,phone) VALUES(?,?,?)";
		update(sql, customer.getName(),customer.getAddress(),customer.getPhone());
	}

	
	@Override
	public Customer get(Integer id) {
		String sqlString = "SELECT id,name,address,phone FROM customers WHERE id = ?";
		return get(sqlString, id);
	}

	@Override
	public void delete(Integer id) {
		String sqlString = "DELETE FROM customers WHERE id = ?";
		update(sqlString, id);
		
	}

	@Override
	public long getCountWithName(String name) {
		String sqlString = "SELECT count(id) FROM customers WHERE name = ?";
		return getForValue(sqlString, name);
	}

	@Override
	public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc) {
		String sqlString = "SELECT id,name,address,phone FROM customers WHERE " + "name LIKE ? AND address LIKE ? AND phone LIKE ?";
		return getForList(sqlString, cc.getNameString() ,cc.getAddressString(),cc.getPhoneString());
	}

	@Override
	public void update(Customer customer) {
		String sql = "UPDATE customers SET name = ? , address = ? ,phone = ? " +
						"WHERE id = ?";
		update(sql, customer.getName(),customer.getAddress(),customer.getPhone(),customer.getId());
	}
	
}

