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

public class TestSequestration {

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
		assertEquals(0, tp.getTrees().size());
		TreePleService tps = new TreePleService(tp);
		double sequestration = 0;
		try {
			sequestration = tps.sequestration(SpeciesEnum.EnglishOak, 2.0, 6.1, 9.6);
		} catch (Exception e) {
			assertEquals(sequestration, 1.045797049180328, 0);
		}

	}
	@Test
	public void test1() {
		double sequestration1 = 0;
		TreePleService tps = new TreePleService(tp);
		try{ 
			tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();
			sequestration1 = tps.sequestration(SpeciesEnum.EnglishOak, -1.0, 6.1, 9.6);
		} catch (Exception e) {
			assertEquals(sequestration1, 0.0, 0);
		}
	}
	@Test
	public void test2() {
		double sequestration2 = 0;
		TreePleService tps = new TreePleService(tp);
		try{ 
			tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();
			sequestration2 = tps.sequestration(SpeciesEnum.EnglishOak, 1.0, -6.1, 9.6);
		} catch (Exception e) {
			assertEquals(sequestration2, 0.0, 0);
		}
	}
	@Test
	public void test3() {
		TreePleService tps = new TreePleService(tp);
		double sequestration3 = 0;
		try{ 
			tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();
			sequestration3 = tps.sequestration(SpeciesEnum.EnglishOak, 1.0, 6.1, -9.6);
		} catch (Exception e) {
			assertEquals(sequestration3, 0.0, 0);
		}
	}
	
	@Test
	public void test4() {
		TreePleService tps = new TreePleService(tp);
		double sequestration4 = 0;
		try{ 
			tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();
			sequestration4 = tps.sequestration(null, 1.0, 6.1, 9.6);
		} catch (Exception e) {
			assertEquals(sequestration4, 0.0, 0);
		}
	}
	/*
	@Test
	public void test5() {
		TreePleService tps = new TreePleService(tp);
		double sequestration5 = 0;
		try{ 
			tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();
			sequestration5 = tps.sequestration(SpeciesEnum.EnglishOak, null, 6.1, 9.6);
		} catch (Exception e) {
			assertEquals(sequestration5, 0.0, 0);
		}
	}
	
	@Test
	public void test6() {
		TreePleService tps = new TreePleService(tp);
		double sequestration5 = 0;
		try{ 
			tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();
			sequestration5 = tps.sequestration(SpeciesEnum.EnglishOak, 1.0,null, 9.6);
		} catch (Exception e) {
			assertEquals(sequestration5, 0.0, 0);
		}
	}
	
	@Test
	public void test7() {
		TreePleService tps = new TreePleService(tp);
		double sequestration6 = 0;
		try{ 
			tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();
			sequestration6 = tps.sequestration(SpeciesEnum.EnglishOak, 1.0, 2.0, null);
		} catch (Exception e) {
			assertEquals(sequestration6, 0.0, 0);
		}
	}
*/

}


