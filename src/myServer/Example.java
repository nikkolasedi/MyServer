package myServer;
import org.neo4j.driver.v1.*;

import static org.neo4j.driver.v1.Values.parameters;

public class Example {
	 // Driver objects are thread-safe and are typically made available application-wide.
    Driver driver;

    public Example(String uri, String user, String password)
    {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public void addPerson(String name)
    {
        // Sessions are lightweight and disposable connection wrappers.
        try (Session session = driver.session())
        {
            // Wrapping Cypher in an explicit transaction provides atomicity
            // and makes handling errors much easier.
            try (Transaction tx = session.beginTransaction())
            {
                tx.run("MERGE (a:Person {name: {x}})", parameters("x", name));
                tx.success();  // Mark this write as successful.
            }
        }
    }
    public void fullCheck(String[] input) {
    	int n = input.length-1;
    	int i = 0;
    	for(i=0;i<=n;i++){
    		if(input[i]==null) {
    			break;
    		}
    	};
    	if(2*i>n) {
    		String[] inputTemp = new String[2*n];
    		inputTemp = input.clone();
    		input = inputTemp;
    	}
    	
    }

    public void printPeople(String initial)
    {
        try (Session session = driver.session())
        {
            // Auto-commit transactions are a quick and easy way to wrap a read.
            StatementResult result = session.run(
                    "MATCH (a:Person) WHERE a.name STARTS WITH {x} RETURN a.name AS name",
                    parameters("x", initial));
            // Each Cypher execution returns a stream of records.
            while (result.hasNext())
            {
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                System.out.println(record.get("name").asString());
                String[] names = new String[5];
                
            }
        }
    }
    public String[] getPeople(String initial)
    {
    	String[] names = new String[5];
        try (Session session = driver.session())
        {
            // Auto-commit transactions are a quick and easy way to wrap a read.
            StatementResult result = session.run(
                    "MATCH (a:Person) WHERE a.name STARTS WITH {x} RETURN a.name AS name",
                    parameters("x", initial));
            // Each Cypher execution returns a stream of records.
            while (result.hasNext())
            {
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                String input = record.get("name").asString();
                fullCheck(names);
                for(int i = 0; i< names.length; i++) {
                	if(names[i]!=null) {
                		
                	}else {
                		names[i]=input;
                		break;
                	}
                
                }
                
            }
        }
        System.out.println("Successfully running");
        return names;
        
    }

    public void close()
    {
        // Closing a driver immediately shuts down all open connections.
        driver.close();
    }

   
}
