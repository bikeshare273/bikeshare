package bikeshareimpl;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import DTO.UserDTO;
import bikeshareinterfaces.BikeShareDaoInterface;
import bikeshareinterfaces.UserOperationInterface;
import resources.Login;
import resources.User;
import util.BikeShareUtil;

@Component
@EnableAutoConfiguration
public class UserOperationsImpl implements UserOperationInterface{

	
	BikeShareDaoInterface<User> userDaoImpl = new UserDAOImpl();
	BikeShareDaoInterface<Login> loginDaoImpl = new LoginDAOImpl();
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		User userdao = new User();
		Login logindao = new Login();
		try{
		int user_id = BikeShareUtil.getRandomInteger();
		userDTO.setUser_id(user_id);
		BeanUtils.copyProperties(userdao, userDTO);
		BeanUtils.copyProperties(logindao, userDTO);
		userDaoImpl.saveObject(userdao);
		loginDaoImpl.saveObject(logindao);
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(InvocationTargetException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return userDTO;
	}
	
	@Override
	public UserDTO updateUser(String user_id, UserDTO userDTO) {
		// TODO Auto-generated method stub
		User userdao = new User();
		Login logindao = new Login();
		try{
			userDTO.setUser_id(Integer.parseInt(user_id));
			BeanUtils.copyProperties(userdao, userDTO);
			BeanUtils.copyProperties(logindao, userDTO);
			userDaoImpl.updateObject(userdao);
			loginDaoImpl.updateObject(logindao);
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(InvocationTargetException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return userDTO;
	}

	@Override
	public UserDTO getUser(String user_id) {
		UserDTO userDTO = new UserDTO();
		try{
		User userdao = userDaoImpl.getObject(user_id);
		Login logindao = loginDaoImpl.getObject(user_id);
		BeanUtils.copyProperties(userDTO, userdao);
		userDTO.setUsername(logindao.getUsername());
		}catch(Exception e){
			e.printStackTrace();
		}
		return userDTO;
	}
	
}
