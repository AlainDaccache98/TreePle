package ca.mcgill.ecse321.TreePle.TestRunner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ca.mcgill.ecse321.TreePle.service.TestListTree;
import ca.mcgill.ecse321.TreePle.service.TestListTreeByMunicipality;
import ca.mcgill.ecse321.TreePle.service.TestListTreeBySpecies;


@RunWith(Suite.class)

@Suite.SuiteClasses({
	TestListTreeByMunicipality.class,
	TestListTreeBySpecies.class,
	TestListTree.class
})

public class TestSuite1 {

}
