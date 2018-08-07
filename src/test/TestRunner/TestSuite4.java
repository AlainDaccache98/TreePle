package ca.mcgill.ecse321.TreePle.TestRunner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ca.mcgill.ecse321.TreePle.service.TestAddTree1;
import ca.mcgill.ecse321.TreePle.service.TestRemoveTree1;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	TestRemoveTree1.class,
	TestAddTree1.class
})


public class TestSuite4 {

}
