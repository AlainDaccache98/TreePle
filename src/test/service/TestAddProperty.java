package ca.mcgill.ecse321.TreePle.service;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.TreePle.model.LocalResident;
import ca.mcgill.ecse321.TreePle.model.Property;
import ca.mcgill.ecse321.TreePle.model.TreePLE;
import ca.mcgill.ecse321.TreePle.persistence.PersistenceXStream;

public class TestAddProperty {

private TreePLE tp;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {
		tp = new TreePLE();

	}

	@After
	public void tearDown() throws Exception {
		tp.delete();

	}
	
	@Test
	public void test() {
		String name = "lr1";
		String email = "123@123";
		String id = "123";
		float length =10f;
		float width =10f;
		float latitude =10f;
		float longitude = 10f;
		
		float length2 =20f;
		float width2 =20f;
		float latitude2 =40f;
		float longitude2 = 50f;
		
		TreePleService ts = new TreePleService(tp);
		//create local resident
		LocalResident lr = null;
		try {
			lr = ts.createLocalResident(name, email, id, length, width, latitude, longitude);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
		
		Property p = null;
		p = ts.addProperty(name, length2, width2, latitude2, longitude2);
		
		assertEquals(length2, lr.getProperty(1).getLength(),0);
		assertEquals(width2, lr.getProperty(1).getWidth(),0);
		assertEquals(longitude2, lr.getProperty(1).getLocation().getLongitude(),0);
		assertEquals(latitude2, lr.getProperty(1).getLocation().getLatitude(),0);
		
	}
	
	

}
