package Sysint2016.Rueckwaertsauktion;


import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;

public class InitDB {
	
	private DynamoDBMapper mapper;
 
    private AmazonDynamoDBClient dynamoDB = new AmazonDynamoDBClient();
    
    public InitDB(){
    	try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		mapper = new DynamoDBMapper(dynamoDB);
        CreateTableRequest tableRequestProdukt = mapper.generateCreateTableRequest(Produkt.class);
        tableRequestProdukt.setProvisionedThroughput( new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(dynamoDB, tableRequestProdukt);

        CreateTableRequest tableRequestNutzer = mapper.generateCreateTableRequest(Nutzer.class);
        tableRequestNutzer.setProvisionedThroughput( new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(dynamoDB, tableRequestNutzer);
    }
    
	private void init() throws Exception {
        /*
         * The ProfileCredentialsProvider will return your [Herval]
         * credential profile by reading from the credentials file located at
         * (/home/herval/.aws/credentials).
         */
        AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider("Mentalist").getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (/home/herval/.aws/credentials), and is in valid format.",
                    e);
        }
        dynamoDB = new AmazonDynamoDBClient(credentials);
        Region usWest2 = Region.getRegion(Regions.US_WEST_2);
        dynamoDB.setRegion(usWest2);
    }

	public DynamoDBMapper getMapper() {
		return mapper;
	}

	public void setMapper(DynamoDBMapper mapper) {
		this.mapper = mapper;
	}

	public AmazonDynamoDBClient getDynamoDB() {
		return dynamoDB;
	}

	public void setDynamoDB(AmazonDynamoDBClient dynamoDB) {
		this.dynamoDB = dynamoDB;
	}
}
