package ca.mcgill.ecse321.TreePle.service;

import static org.junit.Assert.assertEquals;

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
import ca.mcgill.ecse321.TreePle.model.Tree;
import ca.mcgill.ecse321.TreePle.model.TreePLE;
import ca.mcgill.ecse321.TreePle.model.Tree.LandTypeEnum;
import ca.mcgill.ecse321.TreePle.model.Tree.SpeciesEnum;
import ca.mcgill.ecse321.TreePle.persistence.PersistenceXStream;

public class TestAddTree1 {

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
	//test planting in other's property
	public void testPlantLocation2() throws Exception {
		assertEquals(0, tp.getTrees().size());

		float testHtTree1 = (float)5.0;
		float testHtDiameter1 = (float) 10.0;
		Location testLocation1 = new Location((float)50.0, (float)70.0);
		Location testLocation2 = new Location((float)60.0, (float)80.0);
		String testMunicipality1 = "St. Laurent";
		String testMunicipality2 = "Montreal-Nord";

		Calendar c = Calendar.getInstance();
		c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
		Date dt1created = new Date(c.getTimeInMillis());

		c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
		Date dt1planted = new Date(c.getTimeInMillis());

		Property testProperty1 = new Property((float)25.0, (float)35.0, testLocation1);
		Property testProperty2 = new Property((float)27.0, (float)33.0, testLocation2);

		LocalResident testResident1 = new LocalResident("Hisham", "hhawara97@gmail.com", "260723456", tp, testProperty1);
		LocalResident testResident2 = new LocalResident("Aakarsh", "ashekhar98@gmail.com", "260720023", tp, testProperty2);


		TreePleService trs = new TreePleService(tp);
		Tree testTree1, testTree2;

		//belongs to resident1
		testTree1 = trs.plantTree(testHtTree1, LandTypeEnum.values()[0], testHtDiameter1, testLocation1, testMunicipality1, dt1created, dt1planted, SpeciesEnum.values()[2], testResident1);

		tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();

		try {
			//belong to resident2
			testTree2 = trs.plantTree(testHtTree1, LandTypeEnum.values()[1], testHtDiameter1, testLocation2, testMunicipality2, dt1created, dt1planted, SpeciesEnum.values()[1], testResident1);
		}catch(InvalidInputException e) {
			//tree should not be added
			assertEquals(1, tp.getTrees().size());
		}
	}

	@Test
	//test planting tree with null location
	public void testPlantTree2() throws Exception {
		assertEquals(0, tp.getTrees().size());

		float testHtTree1 = (float)5.0;
		float testHtDiameter1 = (float) 10.0;
		Location testLocation1 = null;
		String testMunicipality1 = "St. Laurent";

		Calendar c = Calendar.getInstance();
		c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
		Date dt1created = new Date(c.getTimeInMillis());

		c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
		Date dt1planted = new Date(c.getTimeInMillis());

		try {
			Property testProperty1 = new Property((float)25.0, (float)35.0, testLocation1);	    
			LocalResident testResident1 = new LocalResident("Hisham", "hhawara97@gmail.com", "260723456", tp, testProperty1);


			TreePleService trs = new TreePleService(tp);
			Tree testTree1;


			tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();
			testTree1 = trs.plantTree(testHtTree1, LandTypeEnum.values()[1], testHtDiameter1, testLocation1, testMunicipality1, dt1created, dt1planted, SpeciesEnum.values()[1], testResident1);
		}catch(Exception e) {
			//tree should not be added
			assertEquals(0, tp.getTrees().size());
		}
	}
}
