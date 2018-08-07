package ca.mcgill.ecse321.TreePle.service;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

public class TestForecastSpecies {
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
	public void testForecastSpecies1() throws Exception {


		// first we plant a tree
		Calendar c1 = Calendar.getInstance();
		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		Date dateCreated1 = new Date(c1.getTimeInMillis());
		Calendar c2 = Calendar.getInstance();
		c2.set(2017, Calendar.APRIL, 16, 10, 30, 0);
		Date dateCreated2 = new Date(c2.getTimeInMillis());
		Location l1 = new Location((float)76, (float)12);
		Location l2 = new Location((float)80, (float)20);
		Location l3 = new Location((float)70, (float)12);
		Location l4 = new Location((float)32, (float)12);

		Property p1 = new Property((float)76, (float)12, l1);
		Property p2 = new Property((float)25.0, (float)35.0, l2);
		Property p3 = new Property((float)25.0, (float)35.0, l3);
		Property p4 = new Property((float)25.0, (float)35.0, l4);

		LocalResident lr1 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p1);
		LocalResident lr2 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p2);
		LocalResident lr3 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p3);
		LocalResident lr4 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p4);

		float height = 1;
		String mcgill ="mcgill";
		String moss = "moss";
		String outremont = "Outremont";

		LandTypeEnum landType = LandTypeEnum.values()[1];
		SpeciesEnum walnut = SpeciesEnum.values()[2]; 
		SpeciesEnum englishoak = SpeciesEnum.values()[0];
		SpeciesEnum willow = SpeciesEnum.values()[3];

		float diamsOfCanopy = 2.4f;


		TreePleService tps = new TreePleService(tp);


		//walnut in mcgill
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l1 ,  mcgill , dateCreated1 , dateCreated2 , walnut , lr1 );
		}catch(InvalidInputException e) {
			e.printStackTrace();
		}

		//englishoak in mcgill
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l2 ,  mcgill , dateCreated1 , dateCreated2 , englishoak , lr2 );
		}catch(InvalidInputException e) {
			e.printStackTrace();				}

		//englishoak in moss
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l3 ,  moss , dateCreated1 , dateCreated2 , englishoak , lr3 );
		}catch(InvalidInputException e) {
			e.printStackTrace();
		}

		//willow in moss
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l4 ,  moss , dateCreated1 , dateCreated2 , willow , lr4 );
		}catch(InvalidInputException e) {
			e.printStackTrace();
		}

		List<String> s1 = new ArrayList<String>();
		try {
			s1 = tps.forcastSpecies(englishoak);
		} catch(Exception e) {
			assertEquals(s1.size(), 0);
		}

		List<String> s2 = new ArrayList<String>();
		try {
			s2 = tps.forcastSpecies(null);
		} catch(Exception e) {
			assertEquals(s2.size(), 0);
		}

		List<String> s3 = new ArrayList<String>();
		try {
			s3 = tps.forcastSpecies(walnut);
		} catch(Exception e) {
			assertEquals(s3.size(), 1);
		}

	}

	public void testForecastSpecies() throws Exception {


		// first we plant a tree
		Calendar c1 = Calendar.getInstance();
		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		Date dateCreated1 = new Date(c1.getTimeInMillis());
		Calendar c2 = Calendar.getInstance();
		c2.set(2017, Calendar.APRIL, 16, 10, 30, 0);
		Date dateCreated2 = new Date(c2.getTimeInMillis());
		Location l1 = new Location((float)76, (float)12);
		Location l2 = new Location((float)80, (float)20);
		Location l3 = new Location((float)70, (float)12);
		Location l4 = new Location((float)32, (float)12);

		Property p1 = new Property((float)76, (float)12, l1);
		Property p2 = new Property((float)25.0, (float)35.0, l2);
		Property p3 = new Property((float)25.0, (float)35.0, l3);
		Property p4 = new Property((float)25.0, (float)35.0, l4);

		LocalResident lr1 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p1);
		LocalResident lr2 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p2);
		LocalResident lr3 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p3);
		LocalResident lr4 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p4);

		float height = 1;
		String mcgill ="mcgill";
		String moss = "moss";
		String outremont = "Outremont";

		LandTypeEnum landType = LandTypeEnum.values()[1];
		SpeciesEnum walnut = SpeciesEnum.values()[2]; 
		SpeciesEnum englishoak = SpeciesEnum.values()[0];
		SpeciesEnum willow = SpeciesEnum.values()[3];

		float diamsOfCanopy = 2.4f;


		TreePleService tps = new TreePleService(tp);


		//walnut in mcgill
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l1 ,  mcgill , dateCreated1 , dateCreated2 , walnut , lr1 );
		}catch(InvalidInputException e) {
			e.printStackTrace();
		}

		//englishoak in mcgill
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l2 ,  mcgill , dateCreated1 , dateCreated2 , englishoak , lr2 );
		}catch(InvalidInputException e) {
			e.printStackTrace();				}

		//englishoak in moss
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l3 ,  moss , dateCreated1 , dateCreated2 , englishoak , lr3 );
		}catch(InvalidInputException e) {
			e.printStackTrace();
		}

		//willow in moss
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l4 ,  moss , dateCreated1 , dateCreated2 , willow , lr4 );
		}catch(InvalidInputException e) {
			e.printStackTrace();
		}

		List<String> s1 = new ArrayList<String>();
		try {
			s1 = tps.forcastSpecies(englishoak);
		} catch(Exception e) {
			assertEquals(s1.size(), 0);
		}

	}
	@Test
	public void testForecastSpecies2() throws Exception {


		// first we plant a tree
		Calendar c1 = Calendar.getInstance();
		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		Date dateCreated1 = new Date(c1.getTimeInMillis());
		Calendar c2 = Calendar.getInstance();
		c2.set(2017, Calendar.APRIL, 16, 10, 30, 0);
		Date dateCreated2 = new Date(c2.getTimeInMillis());
		Location l1 = new Location((float)76, (float)12);
		Location l2 = new Location((float)80, (float)20);
		Location l3 = new Location((float)70, (float)12);
		Location l4 = new Location((float)32, (float)12);

		Property p1 = new Property((float)76, (float)12, l1);
		Property p2 = new Property((float)25.0, (float)35.0, l2);
		Property p3 = new Property((float)25.0, (float)35.0, l3);
		Property p4 = new Property((float)25.0, (float)35.0, l4);

		LocalResident lr1 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p1);
		LocalResident lr2 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p2);
		LocalResident lr3 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p3);
		LocalResident lr4 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p4);

		float height = 1;
		String mcgill ="mcgill";
		String moss = "moss";
		String outremont = "Outremont";

		LandTypeEnum landType = LandTypeEnum.values()[1];
		SpeciesEnum walnut = SpeciesEnum.values()[2]; 
		SpeciesEnum englishoak = SpeciesEnum.values()[0];
		SpeciesEnum willow = SpeciesEnum.values()[3];

		float diamsOfCanopy = 2.4f;


		TreePleService tps = new TreePleService(tp);


		//walnut in mcgill
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l1 ,  mcgill , dateCreated1 , dateCreated2 , walnut , lr1 );
		}catch(InvalidInputException e) {
			e.printStackTrace();
		}

		//englishoak in mcgill
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l2 ,  mcgill , dateCreated1 , dateCreated2 , englishoak , lr2 );
		}catch(InvalidInputException e) {
			e.printStackTrace();				}

		//englishoak in moss
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l3 ,  moss , dateCreated1 , dateCreated2 , englishoak , lr3 );
		}catch(InvalidInputException e) {
			e.printStackTrace();
		}

		//willow in moss
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l4 ,  moss , dateCreated1 , dateCreated2 , willow , lr4 );
		}catch(InvalidInputException e) {
			e.printStackTrace();
		}

		List<String> s2 = new ArrayList<String>();
		try {
			s2 = tps.forcastSpecies(null);
		} catch(Exception e) {
			assertEquals(s2.size(), 0);
		}


	}
	@Test
	public void testForecastSpecies3() throws Exception {


		// first we plant a tree
		Calendar c1 = Calendar.getInstance();
		c1.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		Date dateCreated1 = new Date(c1.getTimeInMillis());
		Calendar c2 = Calendar.getInstance();
		c2.set(2017, Calendar.APRIL, 16, 10, 30, 0);
		Date dateCreated2 = new Date(c2.getTimeInMillis());
		Location l1 = new Location((float)76, (float)12);
		Location l2 = new Location((float)80, (float)20);
		Location l3 = new Location((float)70, (float)12);
		Location l4 = new Location((float)32, (float)12);

		Property p1 = new Property((float)76, (float)12, l1);
		Property p2 = new Property((float)25.0, (float)35.0, l2);
		Property p3 = new Property((float)25.0, (float)35.0, l3);
		Property p4 = new Property((float)25.0, (float)35.0, l4);

		LocalResident lr1 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p1);
		LocalResident lr2 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p2);
		LocalResident lr3 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p3);
		LocalResident lr4 = new LocalResident("Alain", "alain.daccache@mail.mcgill.ca", "678965",tp, p4);

		float height = 1;
		String mcgill ="mcgill";
		String moss = "moss";
		String outremont = "Outremont";

		LandTypeEnum landType = LandTypeEnum.values()[1];
		SpeciesEnum walnut = SpeciesEnum.values()[2]; 
		SpeciesEnum englishoak = SpeciesEnum.values()[0];
		SpeciesEnum willow = SpeciesEnum.values()[3];

		float diamsOfCanopy = 2.4f;


		TreePleService tps = new TreePleService(tp);


		//walnut in mcgill
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l1 ,  mcgill , dateCreated1 , dateCreated2 , walnut , lr1 );
		}catch(InvalidInputException e) {
			e.printStackTrace();
		}

		//englishoak in mcgill
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l2 ,  mcgill , dateCreated1 , dateCreated2 , englishoak , lr2 );
		}catch(InvalidInputException e) {
			e.printStackTrace();				}

		//englishoak in moss
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l3 ,  moss , dateCreated1 , dateCreated2 , englishoak , lr3 );
		}catch(InvalidInputException e) {
			e.printStackTrace();
		}

		//willow in moss
		try {
			tps.plantTree(height, landType, diamsOfCanopy , l4 ,  moss , dateCreated1 , dateCreated2 , willow , lr4 );
		}catch(InvalidInputException e) {
			e.printStackTrace();
		}


		List<String> s3 = new ArrayList<String>();
		try {
			s3 = tps.forcastSpecies(walnut);
		} catch(Exception e) {
			assertEquals(s3.size(), 1);
		}

	}


}
