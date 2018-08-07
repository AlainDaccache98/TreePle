package ca.mcgill.ecse321.TreePle.TestRunner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ca.mcgill.ecse321.TreePle.persistence.TestLoadXMLFromXStream;
import ca.mcgill.ecse321.TreePle.persistence.TestSaveXMLToXStream;


@RunWith(Suite.class)

@Suite.SuiteClasses({
	TestLoadXMLFromXStream.class,
	TestSaveXMLToXStream.class
})

public class TestSuite3 {
}