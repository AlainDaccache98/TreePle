package ca.mcgill.ecse321.TreePle.service;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
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

public class TestListTree {

	private TreePLE tp;
	
	private void checkResultListTree(int indexArray, int indexTree, float[] heights, String[] municipalities, 
			LandTypeEnum[] landTypes, SpeciesEnum[] species, float[] diamsOfCanopy, Date[] datesCreated, 
			Date[] datesModified, Location[] locations, List<Tree> registeredTrees) {
		assertEquals(heights[indexArray], registeredTrees.get(indexTree).getHeight(),0);
		assertEquals(municipalities[indexArray], registeredTrees.get(indexTree).getMunicipality());
		assertEquals(landTypes[indexArray], registeredTrees.get(indexTree).getLandTypes());
		assertEquals(species[indexArray], registeredTrees.get(indexTree).getSpecies());
		assertEquals(diamsOfCanopy[indexArray], registeredTrees.get(indexTree).getDiamOfCanopy(),0);
		assertEquals(datesCreated[indexArray], registeredTrees.get(indexTree).getDatePlanted());
		assertEquals(datesModified[indexArray], registeredTrees.get(indexTree).getDateModified());
		assertEquals(locations[indexArray], registeredTrees.get(indexTree).getLocation());
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
	
	@Test
	public void test() {
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
		Location l3 = new Location(35, 46);
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
				tps.plantTree(heights[i], landTypes[i], diamsOfCanopy[i], locations[i],  municipalities[i], datesCreated[i], datesModified[i], species[i], localResidents[i]);
			}catch(InvalidInputException e) {
				fail();
			}
		}
		
		List<Tree> trees = tps.findAllTrees();
		
		
		checkResultListTree(0, 0, heights, municipalities, landTypes, species, diamsOfCanopy, datesCreated, datesModified, locations, trees);
		checkResultListTree(1, 1, heights, municipalities, landTypes, species, diamsOfCanopy, datesCreated, datesModified, locations, trees);
		checkResultListTree(2, 2, heights, municipalities, landTypes, species, diamsOfCanopy, datesCreated, datesModified, locations, trees);
	}

}
