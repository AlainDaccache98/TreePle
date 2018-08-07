package ca.mcgill.ecse321.TreePle.TestRunner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	   public static void main(String[] args) {
		      Result result = JUnitCore.runClasses(TestSuite1.class,TestSuite4.class, TestSuite2.class, TestSuite3.class, TestSuite5.class);

		      for (Failure failure : result.getFailures()) {
		         System.out.println(failure.toString());
		      }
				
		      System.out.println(result.wasSuccessful());
		   }
}




