package bikeshare;



import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Repository

public class LocationRepository
{


	private MongoTemplate mongoTemplate = mongoTemplate();
	
	
	public MongoTemplate mongoTemplate() 
	{

		try 
		{
	
			String mongoUri = "mongodb://bikeshare:sithu13@ds051170.mongolab.com:51170/bikeshare"; //To connect using driver via URI
			MongoClientURI mongoLabUrl = new MongoClientURI(mongoUri);
			//To authenticate the user.
			MongoCredential mongoCredential = MongoCredential.createMongoCRCredential(mongoLabUrl.getUsername(), mongoLabUrl.getDatabase(), mongoLabUrl.getPassword());
			//To connect to the mongo server.
			MongoClient mongoClient = new MongoClient(new ServerAddress("ds051170.mongolab.com",51170), Arrays.asList(mongoCredential));
			MongoTemplate mongoTemplate = new MongoTemplate(mongoClient,mongoLabUrl.getDatabase());
			return mongoTemplate;
		}
		catch(Exception e)
		{
			return null;
		} 
	}

	String COLLECTION_NAME = "location";
    

	public List<Location> findAll() 
	{
		return mongoTemplate.findAll(Location.class,COLLECTION_NAME);
	}



	public Location findById(String location_id) 
	{
		return mongoTemplate.findById(location_id, Location.class,COLLECTION_NAME);
	}



	public void save(Location loc) 
	{
		mongoTemplate.save(loc,COLLECTION_NAME);
	}
	
	
}

