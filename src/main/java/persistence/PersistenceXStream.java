package ca.mcgill.ecse321.TreePle.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;

import ca.mcgill.ecse321.TreePle.model.Forecast;
import ca.mcgill.ecse321.TreePle.model.LocalResident;
import ca.mcgill.ecse321.TreePle.model.Location;
import ca.mcgill.ecse321.TreePle.model.Property;
import ca.mcgill.ecse321.TreePle.model.Scientist;
import ca.mcgill.ecse321.TreePle.model.Survey;
import ca.mcgill.ecse321.TreePle.model.SustainabilityAttribute;
import ca.mcgill.ecse321.TreePle.model.Tree;
import ca.mcgill.ecse321.TreePle.model.TreePLE;
import ca.mcgill.ecse321.TreePle.model.User;

// The first type parameter is the domain type for wich we are creating the repository.
// The second is the key type that is used to look it up. This example will not use it.
@Repository
public class PersistenceXStream {

	private static XStream xstream = new XStream();
	private static String filename = "data.xml";

	// TODO create the RegistrationManager instance here (replace the void return value as well)

	public static boolean saveToXMLwithXStream(Object obj) {
	  xstream.setMode(XStream.ID_REFERENCES);
      String xml = xstream.toXML(obj); // save our xml file

      try {
          FileWriter writer = new FileWriter(filename);
          writer.write(xml);
          writer.close();
          return true;
      } catch (IOException e) {
          e.printStackTrace();
          return false;
      }
	}
	
	/*public static void removeFromXMLwithXStream(Object obj, String givenMunicipality) throws Exception, IOException{
		  xstream.setMode(XStream.ID_REFERENCES);
		  String xml = xstream.toXML(obj);
		  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder builder = factory.newDocumentBuilder();
		  Document doc = builder.parse(xml);
		  
		  try{
			  
			  FileReader writer = new FileReader(filename);
			  NodeList nodes = doc.getElementsByTagName("tree");
			  
			  for(int i=0;i<nodes.getLength();i++){
			      Element tree = (Element)nodes.item(i);
			      Element municipality = (Element)tree.getElementsByTagName("municipality").item(0);
			      String municipalityStr = municipality.getTextContent();
			      if (municipalityStr.equals(givenMunicipality)) {
			         tree.getParentNode().removeChild(tree);
			      }

			  }
			  
		  }catch(Exception e){
			  
		  }
		  
	}*/

	public static Object loadFromXMLwithXStream() {
	  xstream.setMode(XStream.ID_REFERENCES);
      try {
          FileReader fileReader = new FileReader(filename); // load our xml file
          return xstream.fromXML(fileReader);
      }
      catch (IOException e) {
          e.printStackTrace();
          return null;
      }
	}

	public static void setAlias(String xmlTagName, Class<?> className) {
		xstream.alias(xmlTagName, className);
	}

	public static void setFilename(String fn) {
		filename = fn;
	}

	public static String getFilename() {
		return filename;
	}
	
	public static TreePLE initializeModelManager(String fileName) {
      // Initialization for persistence
      TreePLE tm;
      setFilename(fileName);
      setAlias("forecast", Forecast.class);
      setAlias("localResident", LocalResident.class);
      setAlias("location", Location.class);
      setAlias("property", Property.class);
      setAlias("scientist", Scientist.class);
      setAlias("survey", Survey.class);
      setAlias("sustainabilityAttribute", SustainabilityAttribute.class);
      setAlias("tree", Tree.class);
      setAlias("user", User.class);

      // load model if exists, create otherwise
      File file = new File(fileName);
      if (file.exists()) {
          tm = (TreePLE) loadFromXMLwithXStream();
      } else {
          try {
              file.createNewFile();
          } catch (IOException e) {
              e.printStackTrace();
              System.exit(1);
          }
          tm = new TreePLE();
          saveToXMLwithXStream(tm);
      }
      return tm;
  }

	public static void clearData() {
		File myFoo = new File(filename);
		FileWriter fooWriter;
		try {
			fooWriter = new FileWriter(myFoo, false);
			fooWriter.write("");
			fooWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
