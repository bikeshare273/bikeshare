package bikeshareinterfaces;

import DTO.UserDTO;
import resources.User;

public interface UserOperationInterface {

	public UserDTO createUser(UserDTO userDTO);
	
	public UserDTO updateUser(String user_id, UserDTO userDTO);
	
	public UserDTO getUser(String user_id);
	
}
