package ca.mcgill.ecse321.TreePle.TestRunner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ca.mcgill.ecse321.TreePle.service.TestFindTreeById;
import ca.mcgill.ecse321.TreePle.service.TestUpdateTree;


@RunWith(Suite.class)

@Suite.SuiteClasses({
	TestFindTreeById.class,
	TestUpdateTree.class
})

public class TestSuite2 {
}
