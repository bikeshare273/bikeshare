package bikeshareimpl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import resources.User;
import util.MongodbConnection;
import bikeshareinterfaces.BikeShareDaoInterface;


public class BikeShareDaoImpl implements BikeShareDaoInterface<User>{

	private MongoTemplate mongoTemplate = MongodbConnection.getConnection();
	
	@Override
	public void saveObject(User user) {
		mongoTemplate.insert(user);
	}

	@Override
	public User getObject(String id) {
		return mongoTemplate.findOne(new Query((Criteria.where("_id").is(id))), User.class);
	}

	@Override
	public List<User> getAllObjects() {
		 return mongoTemplate.findAll(User.class);
	}

	@Override
	public void removeObject(String id) {
		mongoTemplate.remove(new Query((Criteria.where("_id").is(id))), User.class);
		
	}

	@Override
	public void updateObject(User t) {
		// TODO Auto-generated method stub
		
	}

}
