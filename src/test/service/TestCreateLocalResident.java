package ca.mcgill.ecse321.TreePle.service;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.TreePle.model.LocalResident;
import ca.mcgill.ecse321.TreePle.model.Location;
import ca.mcgill.ecse321.TreePle.model.Property;
import ca.mcgill.ecse321.TreePle.model.TreePLE;
import ca.mcgill.ecse321.TreePle.persistence.PersistenceXStream;

public class TestCreateLocalResident {

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
		
		LocalResident lr = null;
		TreePleService ts = new TreePleService(tp);
		try {
			lr = ts.createLocalResident(name, email, id, length, width, latitude, longitude);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
		
		assertEquals(name, lr.getName());
		assertEquals(email, lr.getEmail());
		assertEquals(id, lr.getUserID());
		assertEquals(length, lr.getProperty(0).getLength(), 0);
		assertEquals(width, lr.getProperty(0).getWidth(), 0);
		assertEquals(latitude, lr.getProperty(0).getLocation().getLatitude(), 0);
		assertEquals(longitude, lr.getProperty(0).getLocation().getLongitude(), 0);
		
	}
	
	@Test
	public void testLocalResidentExist() {
		String name = "lr1";
		String email = "123@123";
		String id = "123";
		float length =10f;
		float width =10f;
		float latitude =10f;
		float longitude = 10f;
		
		LocalResident lr = null;
		LocalResident lr2 = null;
		TreePleService ts = new TreePleService(tp);
		try {
			lr = ts.createLocalResident(name, email, id, length, width, latitude, longitude);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
		
		String error =null;
		try {
			lr2 = ts.createLocalResident(name, email, id, length, width, latitude, longitude);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			error = e.getMessage();
		}
		
		assertEquals("Local resident exits.", error);
		
	}

}
