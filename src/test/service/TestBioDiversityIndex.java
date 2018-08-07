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
import ca.mcgill.ecse321.TreePle.model.Tree;
import ca.mcgill.ecse321.TreePle.model.TreePLE;
import ca.mcgill.ecse321.TreePle.model.Tree.LandTypeEnum;
import ca.mcgill.ecse321.TreePle.model.Tree.SpeciesEnum;
import ca.mcgill.ecse321.TreePle.persistence.PersistenceXStream;

public class TestBioDiversityIndex {
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
public void testSpecies() throws Exception {
		Tree testTree1;
		Tree testTree2;
		try {
			//belong to resident2
			Location testLocation1 = new Location((float)-35.0, (float)-90.0);
			Location testLocation2 = new Location((float)-35.0, (float)-90.0);
			String testMunicipality1 = "St. Laurent";

			String testMunicipality2 = "montreal-so";
			
			float testHtTree1 = (float) 1.0;
			float testHtDiameter1 = (float) 20.0;
			

			Calendar c = Calendar.getInstance();
		    c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
		    Date dt1created = new Date(c.getTimeInMillis());
			
		    c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
		    Date dt1planted = new Date(c.getTimeInMillis());
		    
		    Property testProperty1 = new Property((float)25.0, (float)35.0, testLocation1);
		    
		    LocalResident testResident1 = new LocalResident("Hisham", "hhawara97@gmail.com", "260723456", tp, testProperty1);
			
			TreePleService trs = new TreePleService(tp);
			 testTree1 = trs.plantTree(testHtTree1, LandTypeEnum.values()[0], testHtDiameter1, testLocation1,  testMunicipality1, dt1created, dt1planted, SpeciesEnum.EnglishOak, testResident1);
			 testTree2 = trs.plantTree(testHtTree1, LandTypeEnum.values()[0], testHtDiameter1, testLocation2,  testMunicipality1, dt1created, dt1planted, SpeciesEnum.Pecan, testResident1);

			double indexTest = trs.bioDiversityIndex(SpeciesEnum.EnglishOak, testMunicipality1);
			assertEquals(0.5, indexTest);

		}catch(InvalidInputException e) {
			//tree should not be added
		}
	}
 		
 		

}
