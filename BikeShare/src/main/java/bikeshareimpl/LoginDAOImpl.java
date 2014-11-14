package bikeshareimpl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import bikeshareinterfaces.BikeShareDaoInterface;
import resources.Login;
import resources.User;
import util.MongodbConnection;

public class LoginDAOImpl implements BikeShareDaoInterface<Login>{

	private MongoTemplate mongoTemplate = MongodbConnection.getConnection();
	
	@Override
	public void saveObject(Login login) {
		mongoTemplate.insert(login);
	}

	@Override
	public Login getObject(String id) {
		return mongoTemplate.findOne(new Query((Criteria.where("_id").is(Integer.parseInt(id)))), Login.class);
	}

	@Override
	public List<Login> getAllObjects() {
		 return mongoTemplate.findAll(Login.class);
	}

	@Override
	public void removeObject(String id) {
		mongoTemplate.remove(new Query((Criteria.where("_id").is(id))), User.class);
		
	}

	@Override
	public void updateObject(Login login) {
		Query query = new Query((Criteria.where("_id").is(login.getUser_id())));
		mongoTemplate.updateFirst(query, Update.update("password", login.getPassword()),"Login");
	}

	
}
