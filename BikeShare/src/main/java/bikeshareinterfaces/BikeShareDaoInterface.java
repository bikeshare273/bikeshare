package bikeshareinterfaces;

import java.util.List;

public interface BikeShareDaoInterface<T> {

	public void saveObject(T t);

	public T getObject(String id);

	public List<T> getAllObjects();

	public void removeObject(String id);
	
	public void updateObject(T t);

}
