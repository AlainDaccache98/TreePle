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

public class TestAddTree2 {

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
	
	// testing for null location and negative lon and lat
 	@Test
 	public void testLocation() throws Exception {
 		
 		Location testLocation1 = new Location((float)-35.0, (float)-90.0);
 		Location testLocation2 = null;
 		String testMunicipality1 = "montreal-la";
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
 		Tree testTree1, testTree2;
 		try {
 		 testTree1 = trs.plantTree(testHtTree1, LandTypeEnum.values()[0], testHtDiameter1, testLocation1,  testMunicipality1, dt1created, dt1planted, SpeciesEnum.values()[2], testResident1);
 	     testTree2 = trs.plantTree(testHtTree1, LandTypeEnum.values()[1], testHtDiameter1, testLocation2,  testMunicipality2, dt1created, dt1planted, SpeciesEnum.values()[1], testResident1);
 		}
 		catch(InvalidInputException e ) {
 			  //assertEquals(0, tp.getTrees().size());
 			assertEquals("location cant be null", e.getMessage());
 		}
 		}
 	// testing for null municipality 
 
 		@Test
 		public void testMunicipality() throws Exception {
 			
 			Location testLocation1 = new Location((float)35.0, (float)90.0);
 			Location testLocation2 = new Location((float)30.0, (float)92.0);
 			String testMunicipality1 = "";
 			String testMunicipality2 = "";
 			
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
 			Tree testTree1, testTree2;
 			try {
 			 testTree1 = trs.plantTree(testHtTree1, LandTypeEnum.values()[0], testHtDiameter1, testLocation1,  testMunicipality1, dt1created, dt1planted, SpeciesEnum.values()[2], testResident1);
 		     testTree2 = trs.plantTree(testHtTree1, LandTypeEnum.values()[1], testHtDiameter1, testLocation2,  testMunicipality2, dt1created, dt1planted, SpeciesEnum.values()[1], testResident1);
 			}
 			catch(InvalidInputException e ) {
 				  assertEquals(0, tp.getTrees().size());
 
 			}
 
 		  // check file contents
 		  
 
 
 	}
 		//test for cases with invlalid height such as aHeight == 0 or aHeight < 0
 		@Test
 		public void testHeight() throws Exception {
 			
 			Location testLocation1 = new Location((float)35.0, (float)90.0);
 			Location testLocation2 = new Location((float)30.0, (float)92.0);
 			String testMunicipality1 = "saint laurent";
 			String testMunicipality2 = "saint gogo";
 			
 			float testHtTree1 = 0;
 			float testHtTree2 = -2; 
 			float testHtDiameter1 = (float) 20.0;
 			
 
 			Calendar c = Calendar.getInstance();
 		    c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
 		    Date dt1created = new Date(c.getTimeInMillis());
 			
 		    c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
 		    Date dt1planted = new Date(c.getTimeInMillis());
 		    
 		    Property testProperty1 = new Property((float)25.0, (float)35.0, testLocation1);
 		    
 		    LocalResident testResident1 = new LocalResident("Hisham", "hhawara97@gmail.com", "260723456", tp, testProperty1);
 			
 			TreePleService trs = new TreePleService(tp);
 			Tree testTree1, testTree2;
 			try {
 			 testTree1 = trs.plantTree(testHtTree1, LandTypeEnum.values()[0], testHtDiameter1, testLocation1,  testMunicipality1, dt1created, dt1planted, SpeciesEnum.values()[2], testResident1);
 		     testTree2 = trs.plantTree(testHtTree2, LandTypeEnum.values()[1], testHtDiameter1, testLocation2,  testMunicipality2, dt1created, dt1planted, SpeciesEnum.values()[1], testResident1);
 			}
 			catch(InvalidInputException e ) {
 				  assertEquals(0, tp.getTrees().size());
 
 			}
 
 	}
 		//test for cases with invlalid height such as aHeight == 0 or aHeight < 0	
 		@Test
 		public void testDiameter() throws Exception {
 			
 			Location testLocation1 = new Location((float)35.0, (float)90.0);
 			Location testLocation2 = new Location((float)30.0, (float)92.0);
 			String testMunicipality1 = "saint laurent";
 			String testMunicipality2 = "saint gogo";
 			
 			float testHtTree1 = 3; 
 			float testHtDiameter1 = (float) -20.0;
 			float testHtDiameter2 = (float) 0.0;
 			
 
 			Calendar c = Calendar.getInstance();
 		    c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
 		    Date dt1created = new Date(c.getTimeInMillis());
 			
 		    c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
 		    Date dt1planted = new Date(c.getTimeInMillis());
 		    
 		    Property testProperty1 = new Property((float)25.0, (float)35.0, testLocation1);
 		    
 		    LocalResident testResident1 = new LocalResident("Hisham", "hhawara97@gmail.com", "260723456", tp, testProperty1);
 			
 			TreePleService trs = new TreePleService(tp);
 			Tree testTree1, testTree2;
 			try {
 			 testTree1 = trs.plantTree(testHtTree1, LandTypeEnum.values()[0], testHtDiameter1, testLocation1,  testMunicipality1, dt1created, dt1planted, SpeciesEnum.values()[2], testResident1);
 		     testTree2 = trs.plantTree(testHtTree1, LandTypeEnum.values()[1], testHtDiameter2, testLocation2,  testMunicipality2, dt1created, dt1planted, SpeciesEnum.values()[1], testResident1);
 			}
 			catch(InvalidInputException e ) {
 				  assertEquals(0, tp.getTrees().size());
 
 			}
 
 	}	
 		// check if date of tree planted comes before tree created
 		@Test
 		public void testDateCreated() throws Exception {
 			
 			Location testLocation1 = new Location((float)35.0, (float)90.0);
 			Location testLocation2 = new Location((float)30.0, (float)92.0);
 			String testMunicipality1 = "saint laurent";
 			String testMunicipality2 = "saint gogo";
 			
 			float testHtTree1 = 3; 
 			float testHtDiameter1 = (float) 20.0;
 			float testHtDiameter2 = (float) 10.0;
 			
 
 			Calendar c = Calendar.getInstance();
 		    c.set(2015,Calendar.SEPTEMBER,21,8,30,0);
 		    Date dt1created = new Date(c.getTimeInMillis());
 			
 		    c.set(2015,Calendar.SEPTEMBER,19,10,0,0);
 		    Date dt1planted = new Date(c.getTimeInMillis());
 		    
 		    Property testProperty1 = new Property((float)25.0, (float)35.0, testLocation1);
 		    
 		    LocalResident testResident1 = new LocalResident("Hisham", "hhawara97@gmail.com", "260723456", tp, testProperty1);
 			
 			TreePleService trs = new TreePleService(tp);
 			Tree testTree1, testTree2;
 			try {
 			 testTree1 = trs.plantTree(testHtTree1, LandTypeEnum.values()[0], testHtDiameter1, testLocation1,  testMunicipality1, dt1created, dt1planted, SpeciesEnum.values()[2], testResident1);
 		     testTree2 = trs.plantTree(testHtTree1, LandTypeEnum.values()[1], testHtDiameter2, testLocation2,  testMunicipality2, dt1created, dt1planted, SpeciesEnum.values()[1], testResident1);
 			}
 			catch(InvalidInputException e ) {
 				  assertEquals(0, tp.getTrees().size());
 
 			}
 	}
 		
public void testLocationCheck() throws Exception {
 			
 			Location testLocation1 = new Location((float)35.0, (float)90.0);
 			Location testLocation2 = new Location((float)35.0, (float)90.0);
 			String testMunicipality1 = "saint laurent";
 			String testMunicipality2 = "saint gogo";
 			
 			float testHtTree1 = 3; 
 			float testHtDiameter1 = (float) 20.0;
 			float testHtDiameter2 = (float) 10.0;
 			
 
 			Calendar c = Calendar.getInstance();
 		    c.set(2015,Calendar.SEPTEMBER,21,8,30,0);
 		    Date dt1created = new Date(c.getTimeInMillis());
 			
 		    c.set(2015,Calendar.SEPTEMBER,19,10,0,0);
 		    Date dt1planted = new Date(c.getTimeInMillis());
 		    
 		    Property testProperty1 = new Property((float)25.0, (float)35.0, testLocation1);
 		    
 		    LocalResident testResident1 = new LocalResident("Hisham", "hhawara97@gmail.com", "260723456", tp, testProperty1);
 			
 			TreePleService trs = new TreePleService(tp);
 			Tree testTree1, testTree2;
 			String error = null;
 			try {
 			 testTree1 = trs.plantTree(testHtTree1, LandTypeEnum.values()[0], testHtDiameter1, testLocation1,  testMunicipality1, dt1created, dt1planted, SpeciesEnum.values()[2], testResident1);
 		     testTree2 = trs.plantTree(testHtTree1, LandTypeEnum.values()[1], testHtDiameter2, testLocation2,  testMunicipality2, dt1created, dt1planted, SpeciesEnum.values()[1], testResident1);
 			}
 			catch(InvalidInputException e ) {
 				  error = e.getMessage();
 			}
 			assertEquals("There is a tree at that location.", error);
 	}

}
