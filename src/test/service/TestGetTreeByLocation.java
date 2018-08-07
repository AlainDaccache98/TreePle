package ca.mcgill.ecse321.TreePle.service;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.TreePle.model.LocalResident;
import ca.mcgill.ecse321.TreePle.model.Location;
import ca.mcgill.ecse321.TreePle.model.Property;
import ca.mcgill.ecse321.TreePle.model.TreePLE;
import ca.mcgill.ecse321.TreePle.model.Tree.LandTypeEnum;
import ca.mcgill.ecse321.TreePle.model.Tree.SpeciesEnum;
import ca.mcgill.ecse321.TreePle.persistence.PersistenceXStream;

public class TestGetTreeByLocation {

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
		Calendar c1 = Calendar.getInstance();
		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		Date dateCreated1 = new Date(c1.getTimeInMillis());
		Calendar c2 = Calendar.getInstance();
		c2.set(2017, Calendar.APRIL, 16, 10, 30, 0);
		Date dateCreated2 = new Date(c2.getTimeInMillis());
		Location l1 = new Location((float)50.0, (float)70.0);
		Location l2 = new Location((float)60.0, (float)80.0);

		Property p1 = new Property((float)25.0, (float)35.0, l1);
		LocalResident lr1 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p1);
		float height = 1;
		String municipality ="Montreal";
		String municipality2 = "Quebec";
		LandTypeEnum landTypes = LandTypeEnum.values()[1];
		SpeciesEnum species = SpeciesEnum.values()[1];
		float diamsOfCanopy = 2.4f;
		
		TreePleService tps = new TreePleService(tp);
		try {
			tps.plantTree(height, landTypes, diamsOfCanopy , l1 ,  municipality , dateCreated1 , dateCreated2 , species , lr1 );
		}catch(InvalidInputException e) {
			fail();
		}
		
		
	}

}
