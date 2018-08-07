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

public class TestRemoveTree1 {

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
	//test ideal scenario for removing
	public void testRemoveTree1() throws Exception {
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

		LocalResident testResident1 = new LocalResident("Hisham", "hhawara97@gmail.com", "260723456", tp, testProperty1);

		TreePleService trs = new TreePleService(tp);
		Tree testTree1, testTree2;

		testTree1 = trs.plantTree(testHtTree1, LandTypeEnum.values()[0], testHtDiameter1, testLocation1,  testMunicipality1, dt1created, dt1planted, SpeciesEnum.values()[2], testResident1);
		testTree2 = trs.plantTree(testHtTree1, LandTypeEnum.values()[1], testHtDiameter1, testLocation2,  testMunicipality2, dt1created, dt1planted, SpeciesEnum.values()[1], testResident1);
		//System.out.println("2nd tree planted");
		//check model in memory
		assertEquals(2, tp.getTrees().size());

//		tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();

		// check number of trees
		assertEquals(2, tp.getTrees().size());

		trs.removeTree(testTree2, testResident1);
		System.out.println(tp.getTrees().size()+"laa");
//		tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();			  
		assertEquals(1, tp.getTrees().size());

	}

	@Test
	//test removing non-existing trees
	public void testRemoveTree2() throws Exception {
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

		TreePleService trs = new TreePleService(tp);
		Tree testTree1, testTree2, testTree3 = null;

		testTree1 = trs.plantTree(testHtTree1, LandTypeEnum.values()[0], testHtDiameter1, testLocation1,  testMunicipality1, dt1created, dt1planted, SpeciesEnum.values()[2], testResident1);
		testTree2 = trs.plantTree(testHtTree1, LandTypeEnum.values()[1], testHtDiameter1, testLocation2,  testMunicipality2, dt1created, dt1planted, SpeciesEnum.values()[1], testResident1);

		//check model in memory
		assertEquals(2, tp.getTrees().size());

		tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();

		try {
			trs.removeTree(testTree3, testResident1);
		}catch(InvalidInputException e) {
			assertEquals(2, tp.getTrees().size());
		}
	}

	@Test
	//test removing trees on other's property, i.e, removing a tree that doesnt belong to you
	public void testRemoveTree3() throws Exception {
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

		//belong to resident2
		testTree2 = trs.plantTree(testHtTree1, LandTypeEnum.values()[1], testHtDiameter1, testLocation2, testMunicipality2, dt1created, dt1planted, SpeciesEnum.values()[1], testResident2);

		//check model in memory
		assertEquals(2, tp.getTrees().size());

		tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();

		try {
			trs.removeTree(testTree2, testResident1);
		}catch(InvalidInputException e) {
			//tree should not be removed
			assertEquals(2, tp.getTrees().size());
		}
	}
	
	@Test
	//test removing trees from null location
	public void testRemoveTree4() throws Exception {
		assertEquals(0, tp.getTrees().size());

		float testHtTree1 = (float)5.0;
		float testHtDiameter1 = (float) 10.0;
		Location testLocation1 = new Location((float)50.0, (float)70.0);
		Location testLocation2 = null;
		String testMunicipality1 = "St. Laurent";
		String testMunicipality2 = "Montreal-Nord";

		Calendar c = Calendar.getInstance();
		c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
		Date dt1created = new Date(c.getTimeInMillis());

		c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
		Date dt1planted = new Date(c.getTimeInMillis());

		try {
			Property testProperty1 = new Property((float)25.0, (float)35.0, testLocation1);
			LocalResident testResident1 = new LocalResident("Hisham", "hhawara97@gmail.com", "260723456", tp, testProperty1);		    
			TreePleService trs = new TreePleService(tp);
			Tree testTree1, testTree2;

			testTree1 = trs.plantTree(testHtTree1, LandTypeEnum.values()[0], testHtDiameter1, testLocation1, testMunicipality1, dt1created, dt1planted, SpeciesEnum.values()[2], testResident1);
			testTree2 = trs.plantTree(testHtTree1, LandTypeEnum.values()[1], testHtDiameter1, testLocation2, testMunicipality2, dt1created, dt1planted, SpeciesEnum.values()[1], testResident1);

			//check model in memory
			assertEquals(2, tp.getTrees().size());

			tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();
			trs.removeTree(testTree2, testResident1);
		}catch(InvalidInputException e) {
			//tree should not be have been added, so removing not possible (error)
			assertEquals(1, tp.getTrees().size());
		}
	}

}
