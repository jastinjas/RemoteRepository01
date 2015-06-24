package model;

import java.sql.Date;
import java.util.Arrays;
import model.dao.CustomerDAOJdbc;

public class CustomerService {
	private CustomerDAO cdao=new CustomerDAOJdbc();
//-------------------------------------------------------------------------------	
	public CustomerBean login(String username,String password){
		CustomerBean bean=cdao.select(username);
		if(bean!=null){
			if(password!=null&&password.length()!=0){
				if(Arrays.equals(bean.getPassword(), password.getBytes())){
					return bean;
				}
			}
		}
		return null;
	}
//-------------------------------------------------------------------------------
	public boolean changpassword(String username,String oldpass,String newpass){
		CustomerBean bean=this.login(username, oldpass);
		boolean result=cdao.update(newpass.getBytes(), bean.getEmail(), 
									bean.getBirth(), username);
		
		return result;
	}
//-------------------------------------------------------------------------------		
	public boolean create(String username,String password,String email,String birth){
		boolean result=false;
		if(cdao.select(username)==null){
			result=cdao.insert(username, password.getBytes(), 
						email,Date.valueOf(birth));
		}else{
			System.out.println("UserName ­«½Æ");
		}	
		return result;
	}
//-------------------------------------------------------------------------------
	public boolean delete(String username){
		boolean result=false;
		result=cdao.delete(username);
		return result;
	}
//-------------------------------------------------------------------------------
}
