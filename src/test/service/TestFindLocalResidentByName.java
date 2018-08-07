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
import ca.mcgill.ecse321.TreePle.model.User;
import ca.mcgill.ecse321.TreePle.persistence.PersistenceXStream;

public class TestFindLocalResidentByName {

	TreePLE tp = new TreePLE();
	
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
		TreePleService ts = new TreePleService(tp);
		
		Location l1 = new Location((float)50.0, (float)70.0);
		Property p1 = new Property((float)25.0, (float)35.0, l1);
		LocalResident lr1 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p1);
		LocalResident lr2 = new LocalResident("lr2", "alain.daccache@mail.mcgill.ca", "678965",tp, p1);
		LocalResident result = ts.findLocalResidentByName("lr2");

		assertEquals(lr2, result);
	}

}
