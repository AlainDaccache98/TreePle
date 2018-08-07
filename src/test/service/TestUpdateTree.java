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
import ca.mcgill.ecse321.TreePle.model.Scientist;
import ca.mcgill.ecse321.TreePle.model.Survey;
import ca.mcgill.ecse321.TreePle.model.Survey.StatusEnum;
import ca.mcgill.ecse321.TreePle.model.Tree;
import ca.mcgill.ecse321.TreePle.model.Tree.LandTypeEnum;
import ca.mcgill.ecse321.TreePle.model.Tree.SpeciesEnum;
import ca.mcgill.ecse321.TreePle.model.TreePLE;
import ca.mcgill.ecse321.TreePle.persistence.PersistenceXStream;
public class TestUpdateTree {
	
	private TreePLE tp;
	
	private void checkResultSurvey(String status, String landType, float latitude, float longitude, 
 			float diamOfCanopy, float height, Scientist sci, Tree tree, int surveyIndex) {
 		assertEquals(StatusEnum.valueOf(status), tp.getSurvey(surveyIndex).getStatus());
 		assertEquals(height, tp.getSurvey(surveyIndex).getTrees().getHeight(), 0);
 		assertEquals(LandTypeEnum.valueOf(landType), tp.getSurvey(surveyIndex).getTrees().getLandTypes());
 		assertEquals(latitude, tp.getSurvey(surveyIndex).getTrees().getLocation().getLatitude(), 0);
 		assertEquals(longitude, tp.getSurvey(surveyIndex).getTrees().getLocation().getLongitude(), 0);
 		assertEquals(diamOfCanopy, tp.getSurvey(surveyIndex).getTrees().getDiamOfCanopy(), 0);
 		assertEquals(sci.getName(), tp.getSurvey(surveyIndex).getUser().getName());
 		assertEquals(sci.getEmail(), tp.getSurvey(surveyIndex).getUser().getEmail());
 		assertEquals(sci.getUserID(), tp.getSurvey(surveyIndex).getUser().getUserID());
 	}
	
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
			  assertEquals(1, tp.getTrees().size());
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
		
		@Test
		 	public void updateLatitudeOutofRange2() {
		 		TreePleService tps =  new TreePleService(tp);
		 		float id = 1; 
		 		String status = "Diseased";
		 		String landType = "Residential";
		 		float height = 10;
		 		float latitude = 95;
		 		float longitude = 0;
		 		float diamOfCanopy = 10;
		 		Scientist sci = new Scientist("A", "123@123", "ID", tp);
		 		
		 		Calendar c1 = Calendar.getInstance();
		 		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		 		Date aDatePlanted = new Date(c1.getTimeInMillis());
		 		Location aLocation = new Location(60, 83);
		 		LandTypeEnum aLandType = LandTypeEnum.values()[0]; 
		 		String aMunicipality = "m";
		 		float aDiamOfCanopy = 1.0f;
		 		SpeciesEnum aSpecies = SpeciesEnum.values()[0];
		 		String municipality = "Montreal";
		 		float aHeight = 2.0f;
		 		Tree tree = new Tree(aMunicipality, aLandType, id, aHeight, aDiamOfCanopy, aDatePlanted, aDatePlanted, aSpecies, tp, aLocation);
		 		tp.addTree(tree);
		 		
		 		String error = null;
		 		try {
		 			tps.updateTreeData(id, status, height, landType, latitude, longitude, municipality, diamOfCanopy, sci);
		 		} catch (InvalidInputException e) {
		 			// TODO Auto-generated catch block
		 			error = e.getMessage();
		 		}
		 		
		 		assertEquals("Latitude should be in the range (-90,90)", error);
		 	}
		 	
		 	@Test
		 	public void updateLongitudeOutofRange2() {
		 		TreePleService tps =  new TreePleService(tp);
		 		float id = 1; 
		 		String status = "Diseased";
		 		String landType = "Residential";
		 		float height = 10;
		 		float latitude = 25;
		 		float longitude = 190;
		 		float diamOfCanopy = 10;
		 		Scientist sci = new Scientist("A", "123@123", "ID", tp);
		 		
		 		Calendar c1 = Calendar.getInstance();
		 		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		 		Date aDatePlanted = new Date(c1.getTimeInMillis());
		 		Location aLocation = new Location(60, 83);
		 		LandTypeEnum aLandType = LandTypeEnum.values()[0]; 
		 		String aMunicipality = "m";
		 		float aDiamOfCanopy = 1.0f;
		 		SpeciesEnum aSpecies = SpeciesEnum.values()[0];
		 		float aHeight = 2.0f;
		 		Tree tree = new Tree(aMunicipality, aLandType, id, aHeight, aDiamOfCanopy, aDatePlanted, aDatePlanted, aSpecies, tp, aLocation);
		 		tp.addTree(tree);
		 		
		 		String error = null;
		 		try {
		 			tps.updateTreeData(id, status, height, landType, latitude, longitude, aMunicipality, diamOfCanopy, sci);
		 		} catch (InvalidInputException e) {
		 			// TODO Auto-generated catch block
		 			error = e.getMessage();
		 		}
		 		
		 		assertEquals("Longitude should be in the range (-180,180)", error);
		 	}
		 	
		 	@Test
		 	public void updateEmptyLandType() {
		 		TreePleService tps =  new TreePleService(tp);
		 		float id = 1; 
		 		String status = "Diseased";
		 		String landType = null;
		 		float height = 10;
		 		float latitude = 25;
		 		float longitude = 90;
		 		float diamOfCanopy = 10;
		 		Scientist sci = new Scientist("A", "123@123", "ID", tp);
		 		
		 		Calendar c1 = Calendar.getInstance();
		 		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		 		Date aDatePlanted = new Date(c1.getTimeInMillis());
		 		Location aLocation = new Location(60, 83);
		 		LandTypeEnum aLandType = LandTypeEnum.values()[0]; 
		 		String aMunicipality = "m";
		 		float aDiamOfCanopy = 1.0f;
		 		SpeciesEnum aSpecies = SpeciesEnum.values()[0];
		 		float aHeight = 2.0f;
		 		Tree tree = new Tree(aMunicipality, aLandType, id, aHeight, aDiamOfCanopy, aDatePlanted, aDatePlanted, aSpecies, tp, aLocation);
		 		tp.addTree(tree);
		 		
		 		String error = null;
		 		try {
		 			tps.updateTreeData(id, status, height, landType, latitude, longitude,aMunicipality, diamOfCanopy, sci);
		 		} catch (InvalidInputException e) {
		 			// TODO Auto-generated catch block
		 			error = e.getMessage();
		 		}
		 		
		 		assertEquals("Land type cannot be empty.", error);
		 	}
		 	
		 	@Test
		 	public void updateEmptyStatus() {
		 		TreePleService tps =  new TreePleService(tp);
		 		float id = 1; 
		 		String status = null;
		 		String landType = "Residential";
		 		float height = 10;
		 		float latitude = 25;
		 		float longitude = 90;
		 		float diamOfCanopy = 10;
		 		Scientist sci = new Scientist("A", "123@123", "ID", tp);
		 		
		 		Calendar c1 = Calendar.getInstance();
		 		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		 		Date aDatePlanted = new Date(c1.getTimeInMillis());
		 		Location aLocation = new Location(60, 83);
		 		LandTypeEnum aLandType = LandTypeEnum.values()[0]; 
		 		String aMunicipality = "m";
		 		float aDiamOfCanopy = 1.0f;
		 		SpeciesEnum aSpecies = SpeciesEnum.values()[0];
		 		float aHeight = 2.0f;
		 		Tree tree = new Tree(aMunicipality, aLandType, id, aHeight, aDiamOfCanopy, aDatePlanted, aDatePlanted, aSpecies, tp, aLocation);
		 		tp.addTree(tree);
		 		
		 		String error = null;
		 		try {
		 			tps.updateTreeData(id, status, height, landType, latitude, longitude, aMunicipality, diamOfCanopy, sci);
		 		} catch (InvalidInputException e) {
		 			// TODO Auto-generated catch block
		 			error = e.getMessage();
		 		}
		 		
		 		assertEquals("Status cannot be empty.", error);
		 	}
		 	
		 	@Test
		 	public void updateEmptyScientist() {
		 		TreePleService tps =  new TreePleService(tp);
		 		float id = 1; 
		 		String status = "Diseased";
		 		String landType = "Residential";
		 		float height = 10;
		 		float latitude = 25;
		 		float longitude = 90;
		 		float diamOfCanopy = 10;
		 		Scientist sci = null;
		 		
		 		//data for add tree
		 		Calendar c1 = Calendar.getInstance();
		 		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		 		Date aDatePlanted = new Date(c1.getTimeInMillis());
		 		Location aLocation = new Location(60, 83);
		 		LandTypeEnum aLandType = LandTypeEnum.values()[0]; 
		 		String aMunicipality = "m";
		 		float aDiamOfCanopy = 1.0f;
		 		SpeciesEnum aSpecies = SpeciesEnum.values()[0];
		 		float aHeight = 2.0f;
		 		Tree tree = new Tree(aMunicipality, aLandType, id, aHeight, aDiamOfCanopy, aDatePlanted, aDatePlanted, aSpecies, tp, aLocation);
		 		tp.addTree(tree);
		 		
		 		Survey survey = null;
		 		String error = null;
		 		try {
		 			survey = tps.updateTreeData(id, status, height, landType, latitude, longitude, aMunicipality, diamOfCanopy, sci);
		 		} catch (InvalidInputException e) {
		 			// TODO Auto-generated catch block
		 			error = e.getMessage();
		 		}
		 		
		 		assertEquals("Scientist cannot be empty.", error);
		 	}
		 	
		 	@Test
		 	public void updateUnchangeDiamOfCanopy() {
		 		TreePleService tps =  new TreePleService(tp);
		 		float id = 1; 
		 		String status = "Diseased";
		 		String landType = "Residential";
		 		float height = 10;
		 		float latitude = 25;
		 		float longitude = 90;
		 		float diamOfCanopy = 0;
		 		Scientist sci = new Scientist("A", "123@123", "ID", tp);
		 		tp.addUser(sci);
		 		
		 		//data for add tree
		 		Calendar c1 = Calendar.getInstance();
		 		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		 		Date aDatePlanted = new Date(c1.getTimeInMillis());
		 		Location aLocation = new Location(60, 83);
		 		LandTypeEnum aLandType = LandTypeEnum.values()[0]; 
		 		String aMunicipality = "m";
		 		float aDiamOfCanopy = 1.0f;
		 		SpeciesEnum aSpecies = SpeciesEnum.values()[0];
		 		float aHeight = 2.0f;
		 		Tree tree = new Tree(aMunicipality, aLandType, id, aHeight, aDiamOfCanopy, aDatePlanted, aDatePlanted, aSpecies, tp, aLocation);
		 		tp.addTree(tree);	
		 		
		 		Survey survey = null;
		 		
		 		//check change in tp
		 		String error = null;
		 		try {
		 			survey = tps.updateTreeData(id, status, height, landType, latitude, longitude, aMunicipality, diamOfCanopy, sci);
		 		} catch (InvalidInputException e) {
		 			// TODO Auto-generated catch block
		 			error = e.getMessage();
		 		}
		 		
		 		checkResultSurvey(status, landType, latitude, longitude, tree.getDiamOfCanopy(),height, sci, tree, 0);
		 		
		 		//check change in persistence
		 		tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();
		 		checkResultSurvey(status, landType, latitude, longitude, tree.getDiamOfCanopy(),height, sci, tree, 0);
		 
		 	}
		 	
		 	@Test
		 	public void updateUnchangeHeight() {
		 		TreePleService tps =  new TreePleService(tp);
		 		float id = 1; 
		 		String status = "Diseased";
		 		String landType = "Residential";
		 		float height = 0;
		 		float latitude = 25;
		 		float longitude = 90;
		 		float diamOfCanopy = 10;
		 		Scientist sci = new Scientist("A", "123@123", "ID", tp);
		 		tp.addUser(sci);
		 		
		 		//data for add tree
		 		Calendar c1 = Calendar.getInstance();
		 		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		 		Date aDatePlanted = new Date(c1.getTimeInMillis());
		 		Location aLocation = new Location(60, 83);
		 		LandTypeEnum aLandType = LandTypeEnum.values()[0]; 
		 		String aMunicipality = "m";
		 		float aDiamOfCanopy = 1.0f;
		 		SpeciesEnum aSpecies = SpeciesEnum.values()[0];
		 		float aHeight = 2.0f;
		 		Tree tree = new Tree(aMunicipality, aLandType, id, aHeight, aDiamOfCanopy, aDatePlanted, aDatePlanted, aSpecies, tp, aLocation);
		 		tp.addTree(tree);	
		 		
		 		Survey survey = null;
		 		
		 		//check change in tp
		 		String error = null;
		 		try {
		 			survey = tps.updateTreeData(id, status, height, landType, latitude, longitude, aMunicipality, diamOfCanopy, sci);
		 		} catch (InvalidInputException e) {
		 			// TODO Auto-generated catch block
		 			error = e.getMessage();
		 		}
		 		
		 		checkResultSurvey(status, landType, latitude, longitude, diamOfCanopy,tree.getHeight(), sci, tree, 0);
		 		
		 		//check change in persistence
		 		tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();
		 		checkResultSurvey(status, landType, latitude, longitude, diamOfCanopy,tree.getHeight(), sci, tree, 0);
		 
		 	}
		 	
		 	@Test
		 	public void updateLocationFail() {
		 		TreePleService tps =  new TreePleService(tp);
		 		float id = 1; 
		 		float id2 = 2;
		 		String status = "Diseased";
		 		String landType = "Residential";
		 		float height = 0;
		 		float latitude = 25;
		 		float longitude = 90;
		 		float latitude2 =10;
		 		float longitude2 = 10;
		 		float diamOfCanopy = 10;
		 		Scientist sci = new Scientist("A", "123@123", "ID", tp);
		 		tp.addUser(sci);
		 		
		 		//data for add tree
		 		Calendar c1 = Calendar.getInstance();
		 		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		 		Date aDatePlanted = new Date(c1.getTimeInMillis());
		 		Location aLocation = new Location(60, 83);
		 		Location aLocation2 = new Location(10, 10);
		 		LandTypeEnum aLandType = LandTypeEnum.values()[0]; 
		 		String aMunicipality = "m";
		 		float aDiamOfCanopy = 1.0f;
		 		SpeciesEnum aSpecies = SpeciesEnum.values()[0];
		 		float aHeight = 2.0f;
		 		Tree tree = new Tree(aMunicipality, aLandType, id, aHeight, aDiamOfCanopy, aDatePlanted, aDatePlanted, aSpecies, tp, aLocation);
		 		tp.addTree(tree);	
		 		Tree tree2 = new Tree(aMunicipality, aLandType, id2, aHeight, aDiamOfCanopy, aDatePlanted, aDatePlanted, aSpecies, tp, aLocation2);
		 		tp.addTree(tree2);
		 		
		 		Survey survey = null;
		 		
		 		//check change in tp
		 		String error = null;
		 		try {
		 			survey = tps.updateTreeData(id, status, height, landType, latitude2, longitude2,aMunicipality, diamOfCanopy, sci);
		 		} catch (InvalidInputException e) {
		 			// TODO Auto-generated catch block
		 			error = e.getMessage();
		 		}
		 		
		 		assertEquals("There is a tree at that location.",error);
		 	}
		 	
		 	@Test
		 	public void testAllisFine() {
		 		TreePleService tps =  new TreePleService(tp);
		 		float id = 1; 
		 		String status = "Diseased";
		 		String landType = "Residential";
		 		float height = 0;
		 		float latitude = 0;
		 		float longitude = 0;
		 		float diamOfCanopy = 10;
		 		Scientist sci = new Scientist("A", "123@123", "ID", tp);
		 		tp.addUser(sci);
		 		
		 		//data for add tree
		 		Calendar c1 = Calendar.getInstance();
		 		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		 		Date aDatePlanted = new Date(c1.getTimeInMillis());
		 		Location aLocation = new Location(10, 13);
		 		LandTypeEnum aLandType = LandTypeEnum.values()[0]; 
		 		String aMunicipality = "m";
		 		float aDiamOfCanopy = 1.0f;
		 		SpeciesEnum aSpecies = SpeciesEnum.values()[0];
		 		float aHeight = 2.0f;
		 		Tree tree = new Tree(aMunicipality, aLandType, id, aHeight, aDiamOfCanopy, aDatePlanted, aDatePlanted, aSpecies, tp, aLocation);
		 		tp.addTree(tree);	
		 		
		 		Survey survey = null;
		 		String error = null;
		 		try {
		 			survey = tps.updateTreeData(id, status, height, landType, latitude, longitude, aMunicipality,diamOfCanopy, sci);
		 		} catch (InvalidInputException e) {
		 			// TODO Auto-generated catch block
		 			error = e.getMessage();
		 		}
		 		
		 		tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();
		 		checkResultSurvey(status, landType, latitude, longitude, diamOfCanopy,tree.getHeight(), sci, tree, 0);
		 		
		 	}
}
