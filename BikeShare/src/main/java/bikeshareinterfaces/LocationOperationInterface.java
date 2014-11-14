package bikeshareinterfaces;

import DTO.LocationDTO;

public interface LocationOperationInterface {
	
	public LocationDTO reserveBike(LocationDTO userDTO);
	
	public LocationDTO updateUser(String user_id, LocationDTO userDTO);
	
	public LocationDTO getUser(String user_id);

}
