package ca.mcgill.ecse321.TreePle.service;

import static org.junit.Assert.*;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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
import ca.mcgill.ecse321.TreePle.model.Tree.LandTypeEnum;
import ca.mcgill.ecse321.TreePle.model.Tree.SpeciesEnum;
import ca.mcgill.ecse321.TreePle.model.TreePLE;
import ca.mcgill.ecse321.TreePle.persistence.PersistenceXStream;

public class TestTreePleService {
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
	// test if it works in genenral 
	@Test
	public void testPlantTree() throws Exception {
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
	    //Property testProperty2 = new Property((float)27.0, (float)33.0, testLocation2);
	    
	    LocalResident testResident1 = new LocalResident("Hisham", "hhawara97@gmail.com", "260723456", tp, testProperty1);
		
		TreePleService trs = new TreePleService(tp);
		Tree testTree1, testTree2;
		
		testTree1 = trs.plantTree(testHtTree1, LandTypeEnum.values()[0], testHtDiameter1, testLocation1,  testMunicipality1, dt1created, dt1planted, SpeciesEnum.values()[2], testResident1);
		testTree2 = trs.plantTree(testHtTree1, LandTypeEnum.values()[1], testHtDiameter1, testLocation2,  testMunicipality2, dt1created, dt1planted, SpeciesEnum.values()[1], testResident1);
		//System.out.println("2nd tree planted");
		//check model in memory
		  //assertEquals(2, tp.getTrees().size());
		  //assertEquals("St. Laurent", tp.getTree(0).getMunicipality());
		  
		  tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();

		  // check file contents
		  assertEquals(2, tp.getTrees().size());
		  assertEquals("St. Laurent", tp.getTree(0).getMunicipality());
		  assertEquals("Montreal-Nord", tp.getTree(1).getMunicipality());

		  
		  trs.removeTree(testTree1, testResident1);
		  
		  //assertEquals(1, tp.getTrees().size());
		  //assertEquals(1, tp.getTrees().size());
		  tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();			  
		  assertEquals(1, tp.getTrees().size());
		  


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
			  
		}
		assertEquals(1, tp.getTrees().size());
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
			 testTree1 = trs.plantTree(testHtTree1, LandTypeEnum.values()[0], testHtDiameter1, testLocation1, testMunicipality1, dt1created, dt1planted, SpeciesEnum.values()[2], testResident1);
		     testTree2 = trs.plantTree(testHtTree1, LandTypeEnum.values()[1], testHtDiameter1, testLocation2, testMunicipality2, dt1created, dt1planted, SpeciesEnum.values()[1], testResident1);
			}
			catch(InvalidInputException e ) {
				  

			}
			assertEquals(0, tp.getTrees().size());

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

	
	@Test
	public void testlistTreesByMunicipality() {
		assertEquals(0, tp.getTrees().size());

		Calendar c1 = Calendar.getInstance();
		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		Date dateCreated1 = new Date(c1.getTimeInMillis());

		Calendar c2 = Calendar.getInstance();
		c2.set(2017, Calendar.APRIL, 16, 10, 30, 0);
		Date dateCreated2 = new Date(c2.getTimeInMillis());

		Calendar c3 = Calendar.getInstance();
		c3.set(2017, Calendar.JUNE, 17, 10, 30, 0);
		Date dateModified1 = new Date(c3.getTimeInMillis());

		Calendar c4 = Calendar.getInstance();
		c4.set(2017, Calendar.JULY, 18, 10, 30, 0);
		Date dateModified2 = new Date(c4.getTimeInMillis());

		Location l1 = new Location(34, 45);
		Location l2 = new Location(40, 60);
		Location l3 = new Location(60, 83);
		Property p1 = new Property(1, 2, l1);
		Property p2 = new Property(3, 4, l2);
		Property p3 = new Property(5, 6, l3);
		Property[] allProperties = new Property[3];
		allProperties[0] = p1;
		allProperties[1] = p2;  
		allProperties[2] = p3;


		LocalResident lr1 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p1);
		LocalResident lr2 = new LocalResident("Sandra", "sandra.deng@mail.mcgill.ca", "4784598", tp, p2, p3);

		float[] heights = { 1, 2, 4 };
		String[] municipalities = {"Montreal", "Montreal", "Toronto"};
		LandTypeEnum[] landTypes = {LandTypeEnum.values()[1], LandTypeEnum.values()[2], LandTypeEnum.values()[2]};
		SpeciesEnum[] species = {SpeciesEnum.values()[1], SpeciesEnum.values()[2],SpeciesEnum.values()[2]};
		float[] diamsOfCanopy = {2.4f, 3.2f, 1.1f};
		Date[] datesCreated = {dateCreated1, dateCreated2, dateCreated2};
		Date[] datesModified = {dateModified1, dateModified2, dateCreated2};
		Location[] locations = {l1, l2, l3};
		LocalResident[] localResidents = {lr1, lr2, lr1};
		
		TreePleService tps = new TreePleService(tp);
		for(int i=0; i<3; i++) {
			try {
			tps.plantTree(heights[i], landTypes[i], diamsOfCanopy[i], locations[i], municipalities[i], datesCreated[i], datesModified[i], species[i], localResidents[i]);
			}catch(InvalidInputException e) {
				e.getMessage();
			}
		}
		
		List<Tree> registeredTrees = new ArrayList<Tree>();
		try {
			registeredTrees = tps.listTreesByMunicipality("Montreal");
		} catch (InvalidInputException e) {
			fail();
		}
		assertEquals(2, registeredTrees.size());

		assertEquals(heights[0], registeredTrees.get(0).getHeight(),0);
		assertEquals(municipalities[0], registeredTrees.get(0).getMunicipality());
		assertEquals(landTypes[0], registeredTrees.get(0).getLandTypes());
		assertEquals(species[0], registeredTrees.get(0).getSpecies());
		assertEquals(diamsOfCanopy[0], registeredTrees.get(0).getDiamOfCanopy(),0);
		assertEquals(datesCreated[0], registeredTrees.get(0).getDatePlanted());
		assertEquals(datesModified[0], registeredTrees.get(0).getDateModified());
		assertEquals(locations[0], registeredTrees.get(0).getLocation());
		
		assertEquals(heights[1], registeredTrees.get(1).getHeight(),0);
		assertEquals(municipalities[1], registeredTrees.get(1).getMunicipality());
		assertEquals(landTypes[1], registeredTrees.get(1).getLandTypes());
		assertEquals(species[1], registeredTrees.get(1).getSpecies());
		assertEquals(diamsOfCanopy[1], registeredTrees.get(1).getDiamOfCanopy(),0);
		assertEquals(datesCreated[1], registeredTrees.get(1).getDatePlanted());
		assertEquals(datesModified[1], registeredTrees.get(1).getDateModified());
		assertEquals(locations[1], registeredTrees.get(1).getLocation());
	}
	
	@Test
	public void testlistTreesBySpecies() {
		assertEquals(0, tp.getTrees().size());

		Calendar c1 = Calendar.getInstance();
		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		Date dateCreated1 = new Date(c1.getTimeInMillis());

		Calendar c2 = Calendar.getInstance();
		c2.set(2017, Calendar.APRIL, 16, 10, 30, 0);
		Date dateCreated2 = new Date(c2.getTimeInMillis());

		Calendar c3 = Calendar.getInstance();
		c3.set(2017, Calendar.JUNE, 17, 10, 30, 0);
		Date dateModified1 = new Date(c3.getTimeInMillis());

		Calendar c4 = Calendar.getInstance();
		c4.set(2017, Calendar.JULY, 18, 10, 30, 0);
		Date dateModified2 = new Date(c4.getTimeInMillis());

		Location l1 = new Location(34, 45);
		Location l2 = new Location(40, 60);
		Location l3 = new Location(60, 83);
		Property p1 = new Property(1, 2, l1);
		Property p2 = new Property(3, 4, l2);
		Property p3 = new Property(5, 6, l3);
		Property[] allProperties = new Property[3];
		allProperties[0] = p1;
		allProperties[1] = p2;  
		allProperties[2] = p3;


		LocalResident lr1 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p1);
		LocalResident lr2 = new LocalResident("Sandra", "sandra.deng@mail.mcgill.ca", "4784598", tp, p2, p3);

		float[] heights = { 1, 2, 4 };
		String[] municipalities = {"Montreal", "Montreal", "Toronto"};
		LandTypeEnum[] landTypes = {LandTypeEnum.values()[1], LandTypeEnum.values()[2], LandTypeEnum.values()[2]};
		SpeciesEnum[] species = {SpeciesEnum.values()[1], SpeciesEnum.values()[2],SpeciesEnum.values()[2]};
		float[] diamsOfCanopy = {2.4f, 3.2f, 1.1f};
		Date[] datesCreated = {dateCreated1, dateCreated2, dateCreated2};
		Date[] datesModified = {dateModified1, dateModified2, dateCreated2};
		Location[] locations = {l1, l2, l3};
		LocalResident[] localResidents = {lr1, lr2, lr2};
		
		TreePleService tps = new TreePleService(tp);
		for(int i=0; i<3; i++) {
			try {
			tps.plantTree(heights[i], landTypes[i], diamsOfCanopy[i], locations[i],  municipalities[i], datesCreated[i], datesModified[i], species[i], localResidents[i]);
			}catch(InvalidInputException e) {
				fail();
			}
		}
		
		List<Tree> registeredTrees = new ArrayList<Tree>();
		try {
			registeredTrees = tps.listTreesBySpecies((SpeciesEnum.values()[2]).name());
		} catch (InvalidInputException e) {
			fail();
		}
		assertEquals(2, registeredTrees.size());

		assertEquals(heights[1], registeredTrees.get(0).getHeight(),0);
		assertEquals(municipalities[1], registeredTrees.get(0).getMunicipality());
		assertEquals(landTypes[1], registeredTrees.get(0).getLandTypes());
		assertEquals(species[1], registeredTrees.get(0).getSpecies());
		assertEquals(diamsOfCanopy[1], registeredTrees.get(0).getDiamOfCanopy(),0);
		assertEquals(datesCreated[1], registeredTrees.get(0).getDatePlanted());
		assertEquals(datesModified[1], registeredTrees.get(0).getDateModified());
		assertEquals(locations[1], registeredTrees.get(0).getLocation());
		
		assertEquals(heights[2], registeredTrees.get(1).getHeight(),0);
		assertEquals(municipalities[2], registeredTrees.get(1).getMunicipality());
		assertEquals(landTypes[2], registeredTrees.get(1).getLandTypes());
		assertEquals(species[2], registeredTrees.get(1).getSpecies());
		assertEquals(diamsOfCanopy[2], registeredTrees.get(1).getDiamOfCanopy(),0);
		assertEquals(datesCreated[2], registeredTrees.get(1).getDatePlanted());
		assertEquals(datesModified[2], registeredTrees.get(1).getDateModified());
		assertEquals(locations[2], registeredTrees.get(1).getLocation());
	}
	
	@Test
	public void testNullSpecies() {
		  assertEquals(0, tp.getTrees().size());
		  String error =null;
			Calendar c1 = Calendar.getInstance();
			c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
			Date dateCreated1 = new Date(c1.getTimeInMillis());
			Calendar c2 = Calendar.getInstance();
			c2.set(2017, Calendar.APRIL, 16, 10, 30, 0);
			Date dateCreated2 = new Date(c2.getTimeInMillis());
			Location l1 = new Location(34, 45);
			Property p1 = new Property(1, 2, l1);
			LocalResident lr1 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p1);
			float height = 1;
			String municipality ="Montreal";
			LandTypeEnum landTypes = LandTypeEnum.values()[1];
			SpeciesEnum species = SpeciesEnum.values()[1];
			float diamsOfCanopy = 2.4f;
			
			TreePleService tps = new TreePleService(tp);
			try {
				tps.plantTree(height, landTypes, diamsOfCanopy , l1 ,  municipality , dateCreated1 , dateCreated2 , species , lr1 );
				}catch(InvalidInputException e) {
					fail();
				}
			
			String s = null;
			List<Tree> registeredTrees = new ArrayList<Tree>();
			try {
				registeredTrees = tps.listTreesBySpecies(s);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			
			assertEquals("Species cannot be empty!", error);
			assertEquals(0, registeredTrees.size());
	}
	
	@Test
	public void testNullMunicipality() {
		  assertEquals(0, tp.getTrees().size());
		  String error =null;
			Calendar c1 = Calendar.getInstance();
			c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
			Date dateCreated1 = new Date(c1.getTimeInMillis());
			Calendar c2 = Calendar.getInstance();
			c2.set(2017, Calendar.APRIL, 16, 10, 30, 0);
			Date dateCreated2 = new Date(c2.getTimeInMillis());
			Location l1 = new Location(34, 45);
			Property p1 = new Property(1, 2, l1);
			LocalResident lr1 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p1);
			float height = 1;
			String municipality ="Montreal";
			LandTypeEnum landTypes = LandTypeEnum.values()[1];
			SpeciesEnum species = SpeciesEnum.values()[1];
			float diamsOfCanopy = 2.4f;
			
			TreePleService tps = new TreePleService(tp);
			try {
				tps.plantTree(height, landTypes, diamsOfCanopy , l1 , municipality , dateCreated1 , dateCreated2 , species , lr1 );
				}catch(InvalidInputException e) {
					fail();
				}
			
			String s = null;
			List<Tree> registeredTrees = new ArrayList<Tree>();
			try {
				registeredTrees = tps.listTreesByMunicipality(s);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			
			assertEquals("Municipality cannot be empty!", error);
			assertEquals(0, registeredTrees.size());
	}
}
