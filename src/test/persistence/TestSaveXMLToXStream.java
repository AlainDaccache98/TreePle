package ca.mcgill.ecse321.TreePle.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.TreePle.model.Location;
import ca.mcgill.ecse321.TreePle.model.Tree;
import ca.mcgill.ecse321.TreePle.model.TreePLE;
import ca.mcgill.ecse321.TreePle.model.Tree.LandTypeEnum;
import ca.mcgill.ecse321.TreePle.model.Tree.SpeciesEnum;

public class TestSaveXMLToXStream {

	TreePLE tp;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		  PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
	}
	
	@Before
	public void setUp() throws Exception {
		
		tp = new TreePLE();
		
	    Calendar c = Calendar.getInstance();
	    c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
	    Date dt1created = new Date(c.getTimeInMillis());
		
	    c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
	    Date dt1planted = new Date(c.getTimeInMillis());
			
		Location l1 = new Location((float)34.0, (float)45.0);
		Location l2 = new Location((float)40.0, (float)60.0);
		
	    // create tree instances for testing here
	    Tree t1 = new Tree("mn", LandTypeEnum.values()[1], 1, 1, 0, dt1created, dt1planted, SpeciesEnum.values()[1], tp, l1);
	    Tree t2 = new Tree("ar", LandTypeEnum.values()[2], 2, 3, 1, dt1created, dt1planted, SpeciesEnum.values()[2], tp, l2);

	    tp.addTree(t1);
	    tp.addTree(t2);
	    
	    PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
	    if (!PersistenceXStream.saveToXMLwithXStream(tp))
	        fail("Could not save file.");
	    tp.delete();
	}
	
	@After
	public void tearDown() {
		tp.delete();
	}
	
	//adding a new tree in PersistenceXStream and making sure the new tree count is as expected
	
		@Test
		public void testNewTreeCount() {
			
		    TreePLE tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();

			Calendar c = Calendar.getInstance();
		    c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
		    Date dt3created = new Date(c.getTimeInMillis());
			
		    c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
		    Date dt3planted = new Date(c.getTimeInMillis());
				
			Location l3 = new Location((float)20.0, (float)15.0);
			
		    // create tree instances for testing here
		    Tree t3 = new Tree("ks", LandTypeEnum.values()[1], 1, 1, 0, dt3created, dt3planted, SpeciesEnum.values()[2], tp, l3);
		    
		    tp.addTree(t3);
		    //System.out.println("starting planting of 3rd tree");
		    System.out.println(tp.getTrees());
		    PersistenceXStream.saveToXMLwithXStream(tp);
		    assertEquals(3, tp.getTrees().size());

		}
		
		//testing invalid input such as negative latitude or longitude
		//example of guideline-testing where we are forcing an input error to make sure our system is working correctly
		
		@Test
		public void testInvalidInput() {
			
		    tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();

			Calendar c = Calendar.getInstance();
		    c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
		    Date dt4created = new Date(c.getTimeInMillis());
			
		    c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
		    Date dt4planted = new Date(c.getTimeInMillis());
				
			Location l4 = new Location((float)-70.0, (float)80.0);
			
			// create tree instances for testing here
		    Tree t4 = new Tree("sl", LandTypeEnum.values()[1], 1, 1, 0, dt4created, dt4planted, SpeciesEnum.values()[0], tp, l4);
		 
		    tp.addTree(t4);
		    
		    assertEquals(3, tp.getTrees().size());
		
		}
		
		//testing empty list of trees in tp
		@Test
		public void testEmptyCountTrees() {
			
			tp.delete();
			assertEquals(0, tp.numberOfTrees());
		}
}
