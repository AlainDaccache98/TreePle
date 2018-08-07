package ca.mcgill.ecse321.TreePle.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import ca.mcgill.ecse321.TreePle.model.Location;
import ca.mcgill.ecse321.TreePle.model.Tree;
import ca.mcgill.ecse321.TreePle.model.TreePLE;
import ca.mcgill.ecse321.TreePle.model.Tree.LandTypeEnum;
import ca.mcgill.ecse321.TreePle.model.Tree.SpeciesEnum;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestLoadXMLFromXStream {

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
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		
	}
	
	@Test
	public void testTreeCountBeforeLoad() {  
	    //TODO: check the tests before here 
	    assertEquals(0, tp.getTrees().size());
	   
	}
	
	//testing count of trees after loading the model
	@Test
	public void testTreeCountAfterLoad() {
		
		//load model
	    tp = (TreePLE) PersistenceXStream.loadFromXMLwithXStream();
	    if (tp == null)
	        fail("Could not load file.");
	    
	 // TODO: check tests after loading model
	    assertEquals(2, tp.getTrees().size());
	    
	    assertEquals("mn", tp.getTree(0).getMunicipality());
	    assertEquals("ar", tp.getTree(1).getMunicipality());
	    
	    Calendar c = Calendar.getInstance();
	    c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
	    Date testDatePlanted = new Date(c.getTimeInMillis());
	    
	    c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
	    Date testDateModified = new Date(c.getTimeInMillis());

	    assertEquals(testDatePlanted.toString(), tp.getTree(0).getDatePlanted().toString());  
	    assertEquals(testDateModified.toString(), tp.getTree(0).getDateModified().toString());
		
	}
}
