package bikeshareimpl;

import java.util.List;

import jdk.nashorn.internal.objects.annotations.Where;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import resources.User;
import util.MongodbConnection;
import bikeshareinterfaces.BikeShareDaoInterface;


public class UserDAOImpl implements BikeShareDaoInterface<User>{

	private MongoTemplate mongoTemplate = MongodbConnection.getConnection();
	
	@Override
	public void saveObject(User user) {
		mongoTemplate.insert(user);
	}

	@Override
	public User getObject(String id) {
		return mongoTemplate.findOne(new Query((Criteria.where("_id").is(Integer.parseInt(id)))), User.class);
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
	public void updateObject(User user) {
		Query query = new Query((Criteria.where("_id").is(user.getUser_id())));
		mongoTemplate.updateFirst(query, Update.update("email", user.getEmail()),"User");  
		mongoTemplate.updateFirst(query, Update.update("phone", user.getPhone()),"User");  
		mongoTemplate.updateFirst(query, Update.update("address", user.getAddress()),"User");  
		mongoTemplate.updateFirst(query, Update.update("zipcode", user.getZipcode()),"User");  
		mongoTemplate.updateFirst(query, Update.update("credit_card_no", user.getCredit_card_no()),"User");  
	}

}
