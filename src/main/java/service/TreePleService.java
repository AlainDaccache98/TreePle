package ca.mcgill.ecse321.TreePle.service;


import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.TreePle.model.Forecast;
import ca.mcgill.ecse321.TreePle.model.LocalResident;
import ca.mcgill.ecse321.TreePle.model.Location;
import ca.mcgill.ecse321.TreePle.model.Property;
import ca.mcgill.ecse321.TreePle.model.Scientist;
import ca.mcgill.ecse321.TreePle.model.Survey;
import ca.mcgill.ecse321.TreePle.model.Survey.StatusEnum;
import ca.mcgill.ecse321.TreePle.model.Tree;
import ca.mcgill.ecse321.TreePle.model.Tree.LandTypeEnum;
import ca.mcgill.ecse321.TreePle.model.Tree.SpeciesEnum;
import ca.mcgill.ecse321.TreePle.model.TreePLE;
import ca.mcgill.ecse321.TreePle.model.User;
import ca.mcgill.ecse321.TreePle.persistence.PersistenceXStream;

@Service
public class TreePleService {
	private TreePLE tp;

	private Tree privateGetTreeByLocation(Location location) {
		//need to throw exception if null!!!
		// TODO Auto-generated method stub
		for(Tree tree : tp.getTrees())
		{
			if(tree.getLocation().getLatitude()==location.getLatitude()&&tree.getLocation().getLongitude() == location.getLongitude())
				return tree;
		}
		return null;
	}

	public Tree findTreeById(float id) throws InvalidInputException{
		Tree tree = null;
		List<Tree> trees = tp.getTrees();
		for(Tree t : trees) {
			if(t.getId() == id)
			{
				tree = t;
			}
		}
		if(tree == null)
		{
			throw new InvalidInputException("Tree does not exist.");
		}
		return tree;
	}


	public TreePleService(TreePLE tp)
	{
		this.tp = tp;
	}

	public Tree plantTree(float aHeight,LandTypeEnum aLandType, float aDiamOfCanopy, Location aLocation, String aMunicipality,Date aDatePlanted,Date aDateModified, SpeciesEnum aSpecies, LocalResident localResident) throws InvalidInputException 
	{
		//checking if the input is valid

		if(aDatePlanted.after(aDateModified) )
		{
			throw new InvalidInputException("date planned cant be after modfied");
		}
		if(aMunicipality.equals("")) {
			throw new InvalidInputException("municipality cant be null");

		}
		if(aLandType == null ) {
			throw new InvalidInputException("landtype cant be null");

		}
		if(aHeight == 0f) {
			throw new InvalidInputException("height cant be zero");

		}
		if(aHeight < 0f) {
			throw new InvalidInputException("height cant be negative");

		}
		if( aDiamOfCanopy == 0 ) {
			throw new InvalidInputException("diameter cant be zero");
		}
		if( aDiamOfCanopy < 0 ) {
			throw new InvalidInputException("diameter cant be negative");
		}
		if(aLocation == null) {
			throw new InvalidInputException("location cant be null");

		}

		if(aSpecies == null ) {
			throw new InvalidInputException("species can't be null");

		}		
		
		if(privateGetTreeByLocation(aLocation)!= null) {
			throw new InvalidInputException("There is a tree at that location.");
		}

		Random rand = new Random();
		float id = (float)rand.nextInt(1000000) +1;	//generate id for tree

		//checking if the location of the added tree is in the
		//property of the local resident who is planting it. 
		//authentication is to be implemented in future iterations.
		Tree tree = null;
		for(Property property : localResident.getProperties())
		{
			if((property.getLocation().getLatitude()+ property.getLength() >= aLocation.getLatitude() && 
					property.getLocation().getLatitude() <= aLocation.getLatitude())&& 
					(property.getLocation().getLongitude()+ property.getWidth() >= aLocation.getLongitude() &&
					property.getLocation().getLongitude() <= aLocation.getLongitude()))
			{
				tree = new Tree(aMunicipality, aLandType, id, aHeight, aDiamOfCanopy, aDatePlanted, aDateModified,aSpecies, tp, aLocation);
				if(findScientistByName("-") == null)
				{
					Scientist sci = new Scientist("-","-","-",tp);
					tp.addUser(sci);
				}
				//create survey for the tree, so the status is planted
				Survey survey = new Survey(aDateModified,StatusEnum.values()[3],tree,findScientistByName("-"),tp);
				tree.addSurvey(survey);
				tp.addTree(tree);
				tp.addSurvey(survey);
				PersistenceXStream.saveToXMLwithXStream(tp);
			}
		}
		if(tree == null){
			throw new InvalidInputException("The tree doesn't belong to you.");
		}
		return tree;
	}


	public List<Tree> findAllTrees() {
		return tp.getTrees();
	}

	public List<Tree> listTreesByMunicipality(String municipality) throws InvalidInputException {
		if(municipality == null) {
			throw new InvalidInputException("Municipality cannot be empty!");
		}
		List <Tree> allTrees = tp.getTrees();
		List <Tree> matchedMunicipality = new ArrayList<Tree>();
		for (Tree tree: allTrees) {
			if(tree.getMunicipality().equals(municipality)) {
				matchedMunicipality.add(tree);
			}
		}
		return matchedMunicipality;

	}
	public List<Tree> listTreesBySpecies(String species) throws InvalidInputException{
		if(species == null) {
			throw new InvalidInputException("Species cannot be empty!");
		}
		List <Tree> allTrees = tp.getTrees();
		List <Tree> matchedSpecies = new ArrayList<Tree>();

		for (Tree tree: allTrees) {
			if(species.equals(tree.getSpecies().name())) {
				matchedSpecies.add(tree);
			}
		}
		return matchedSpecies;

	}

	public List<Tree> listTreesByLandType(String landType)throws InvalidInputException{
		if(landType == null) {
			throw new InvalidInputException("Land Type cannot be empty!");
		}
		List <Tree> allTrees = tp.getTrees();
		List <Tree> matchedLandType = new ArrayList<Tree>();

		for (Tree tree: allTrees) {
			if(landType.equals(tree.getLandTypes().name())) {
				matchedLandType.add(tree);
			}
		}
		return matchedLandType;
	}


	public void removeTree(Tree aTree, LocalResident aLocalResident) throws IOException, Exception
	{
		int i=0;
		for(Tree tree : tp.getTrees())
		{
			if(tree.equals(aTree))
			{
				i++;
			}

		}

		if(i==0)
			throw new InvalidInputException("Tree does not exist");

		for(Property property : aLocalResident.getProperties()){
			if(!((aTree.getLocation().getLatitude() >= property.getLocation().getLatitude()) && (aTree.getLocation().getLatitude() <= (property.getLocation().getLatitude() + property.getLength())) && (aTree.getLocation().getLongitude() >= property.getLocation().getLongitude()) && (aTree.getLocation().getLongitude() <= (property.getLocation().getLongitude() + property.getWidth())))){
				System.out.println("invalid");
				throw new InvalidInputException("Tree does not belong to you");
			}}
		
		System.out.println(tp.getTrees().size());
		aTree.delete();
		boolean t = tp.removeTree(aTree);
		
		System.out.println(tp.getTrees().size());
		System.out.println("b" + t);
		System.out.println(tp.getTrees().size());
		PersistenceXStream.clearData();
		PersistenceXStream.saveToXMLwithXStream(tp);
	}

	public Tree getTreeByLocation(Location location) throws InvalidInputException{
		//need to throw exception if null!!!
		// TODO Auto-generated method stub
		Tree tr = null;
		for(Tree tree : tp.getTrees())
		{
			if(tree.getLocation().getLatitude()==location.getLatitude()&&tree.getLocation().getLongitude() == location.getLongitude())
			{
				tr = tree;
				break;
			}
		}
		if(tr == null)
			throw new InvalidInputException("Tree doesn't exist.");
		return tr;
	}

	public List<String> getListOfMunicipality(){
		List<String> listOfMunicipality = new ArrayList<String>();
		for(Tree tree: tp.getTrees()) {
			boolean exist = false;
			for(String municipality: listOfMunicipality) {
				if(tree.getMunicipality().equals(municipality)) {
					exist = true;
					break;
				}
			}
			if(exist == false) {
				listOfMunicipality.add(tree.getMunicipality());
			}
		}
		return listOfMunicipality;
	}


	public Survey updateTreeData( float id, String aStatus, float aHeight, String aLandType, 
			float aLatitude, float aLongitude, String aMunicipality, float aDiamOfCanopy, 
			Scientist aScientist) throws InvalidInputException
	{
		if(aLatitude >90 || aLatitude <-90) {
			throw new InvalidInputException("Latitude should be in the range (-90,90)");
		}
		if(aLongitude >180 || aLongitude <-180) {
			throw new InvalidInputException("Longitude should be in the range (-180,180)");
		}
		if(aHeight < 0) {
			throw new InvalidInputException("Height cannot be negative");
		}
		if(aDiamOfCanopy < 0) {
			throw new InvalidInputException("Diameter of canopy cannot be negative");
		}
		if(aLandType == null) {
			throw new InvalidInputException("Land type cannot be empty.");
		}
		if(aStatus == null) {
			throw new InvalidInputException("Status cannot be empty.");
		}
		if(aScientist == null) {
			throw new InvalidInputException("Scientist cannot be empty.");
		}

		Location aLocation = new Location(aLatitude, aLongitude);
		if(privateGetTreeByLocation(aLocation)!=null&& !privateGetTreeByLocation(aLocation).equals(findTreeById(id)))
			throw new InvalidInputException("There is a tree at that location.");

		//should prevent user change the location to a location where a tree exists!!!
		StatusEnum status = StatusEnum.valueOf(aStatus);
		LandTypeEnum land = LandTypeEnum.valueOf(aLandType);

		Calendar cal = Calendar.getInstance();
		Date aDateModified = new Date(cal.getTimeInMillis());

		Tree aTree = findTreeById(id);
		aTree.setHeight(aHeight);
		aTree.setLandTypes(land);
		aTree.setLocation(aLocation);
		aTree.setDiamOfCanopy(aDiamOfCanopy);
		aTree.setDateModified(aDateModified);
		aTree.setMunicipality(aMunicipality);
		Survey nSurvey = new Survey(aDateModified, status, aTree, aScientist, tp);
		aTree.addSurvey(nSurvey);
		PersistenceXStream.saveToXMLwithXStream(tp);
		return nSurvey;
	}

	public LocalResident findLocalResidentByName(String name) {
		List<User> list = tp.getUser();
		LocalResident target =null;
		User user = null;
		for(User u : list) {
			if(u.getName().equals(name)) {
				user = u;
				break;
			}
		}
		if(user instanceof LocalResident) {
			target = (LocalResident) user;
		}
		return target;
	}
	public Double bioDiversityIndex(SpeciesEnum aSpecies, String municipality) throws Exception {

		List<Tree> listTrees = tp.getTrees();
		List<Tree> treesOfMunicipality = new ArrayList<Tree>();
		List<Tree> treesOfSpecies = new ArrayList<Tree>();
		double bioIndex = 0;
		for(Tree tree : listTrees) {

			if (tree.getMunicipality().equals(municipality)) {
				treesOfMunicipality.add(tree);
			}
		}
		for(Tree tree : treesOfMunicipality) {

			if (tree.getSpecies().equals(aSpecies)) {
				treesOfSpecies.add(tree);
			}
		}
		if(treesOfMunicipality.size() == 0) {
			throw new Exception("No trees in this municipality");
		}
		bioIndex = ((double) treesOfSpecies.size())/ ((double) treesOfMunicipality.size());
		return bioIndex;
	}
	public Double sequestration(SpeciesEnum aSpecies,  Double diameter, Double growthFactor, Double height) {
		//get weight
		double weight = 0.0;

		if(diameter < 11) {
			weight = 0.25 * diameter * diameter * height;
		}
		else {
			weight = 0.15 * diameter * diameter * height;
		}
		// get age 
		double age = diameter * growthFactor;
		//sequesteredCO2 per year
		double sequesteredCO2 = (weight * 0.725*0.5*3.6663)/age;

		return sequesteredCO2;
	}
	public LocalResident createLocalResident (String name, String email, String id, float length, float width, float latitude, float longitude) throws Exception{
		if(findLocalResidentByName(name)!=null) {
			throw new Exception("Local resident exits.");
		}
		Location l = new Location(latitude, longitude);
		Property p = new Property(length, width, l);
		LocalResident lr = new LocalResident(name, email, id, tp, p);
		tp.addUser(lr);
		PersistenceXStream.saveToXMLwithXStream(tp);
		return lr;
	}

	public Property addProperty(String name, float length, float width, float latitude, float longitude) {
		Location l = new Location(latitude, longitude);
		Property p = new Property(length, width, l);
		LocalResident lr = findLocalResidentByName(name);
		tp.removeUser(lr);
		lr.addProperty(p);
		tp.addUser(lr);
		//not save to the persistence ... 
		PersistenceXStream.saveToXMLwithXStream(tp);
		return p;
	}

	public Scientist findScientistByName(String name) {
		List<User> list = tp.getUser();
		Scientist target =null;
		User user = null;
		for(User u : list) {
			if(u.getName().equals(name)) {
				user = u;
				break;
			}
		}
		if(user instanceof Scientist) {
			target = (Scientist) user;
		}
		return target;
	}

	public String forecastMunicipality(String aMunicipality) throws Exception {
		List<Tree> allTrees = tp.getTrees();
		SpeciesEnum[] allSpecies = SpeciesEnum.values();
		List<Tree> treesInMunicipality = new ArrayList<Tree>();
		List<Tree> treesNotInMunicipality = new ArrayList<Tree>();
		List<SpeciesEnum> speciesInMunicipality = new ArrayList<SpeciesEnum>();
		List<SpeciesEnum> species = new ArrayList<SpeciesEnum>();
		List<Double> bioIndexList = new ArrayList<Double>();

		for(Tree tree: allTrees) {
			if (tree.getMunicipality().equals(aMunicipality));
			treesInMunicipality.add(tree);
		}

		for (Tree tree: treesInMunicipality) {
			speciesInMunicipality.add(tree.getSpecies());

		}

		for(Tree tree: allTrees) {
			if (!tree.getMunicipality().equals(aMunicipality));
			treesNotInMunicipality.add(tree);
		}

		for (Tree tree: treesNotInMunicipality) {
			species.add(tree.getSpecies());

		}

		for(SpeciesEnum spec: speciesInMunicipality) {
			if (bioDiversityIndex(spec, aMunicipality) < 1/speciesInMunicipality.size()) {
				species.add(spec);
			}

		}

		Set<SpeciesEnum> hs = new HashSet<>();
		hs.addAll(species);
		species.clear();
		species.addAll(hs);

		return species.toString();
		/* for (Tree tree: treeMunicipality) {
			if (!speciesInMunicipality.contains(tree.getSpecies())) {
				speciesInMunicipality.add(tree.getSpecies());
			}
		}
		double benchmark = 1 / speciesInMunicipality.size();
		List<SpeciesEnum> speciesInNeedOfLove = new ArrayList<SpeciesEnum>();

		for (SpeciesEnum species: speciesInMunicipality) {
			if (bioDiversityIndex(species, aMunicipality) < benchmark )
				speciesInNeedOfLove.add(species);
		}
		for (Tree tree: allTrees) {

			if (!speciesInMunicipality.contains(tree.getSpecies())) {
				speciesInNeedOfLove.add(tree.getSpecies());
			}
		}
		 */


		//return speciesInNeedOfLove;

	}

	public List<String> forcastSpecies(SpeciesEnum species) throws Exception{
		List<Tree> allTrees = tp.getTrees();
		List<Tree> speciesTree = new ArrayList<Tree>();
		List<String> municipalities = new ArrayList<String>();
		List<String> allMunicipalities = new ArrayList<String>();
		for (Tree tree: allTrees) {
			allMunicipalities.add(tree.getMunicipality());
		}
		
		Set<String> hs2 = new HashSet<>();
		hs2.addAll(allMunicipalities);
		allMunicipalities.clear();
		allMunicipalities.addAll(hs2);

		List<Double> bioIndex = new ArrayList<Double>();
		List<String> municipalitiesThatNeedLove = new ArrayList<String>();

		for(Tree tree : allTrees) {
			if(tree.getSpecies().equals(species)) {
				speciesTree.add(tree);
			}
		}
		for(Tree tree : speciesTree) {
			municipalities.add(tree.getMunicipality());

		}
		Set<String> hs = new HashSet<>();
		hs.addAll(municipalities);
		municipalities.clear();
		municipalities.addAll(hs);

		int total = 0;
		for(String municipality : municipalities) {
			total += bioDiversityIndex(species, municipality);
		}
		double benchmark = total / municipalities.size();

		for(String municipality : municipalities) {
			if(bioDiversityIndex(species, municipality) < benchmark) {
				municipalitiesThatNeedLove.add(municipality);
			}
		}

		for (String municipality: allMunicipalities) {
			if (!municipalities.contains(municipality)) {
				municipalitiesThatNeedLove.add(municipality);
			}
		}

		Set<String> hs1 = new HashSet<>();
		hs1.addAll(municipalitiesThatNeedLove);
		municipalitiesThatNeedLove.clear();
		municipalitiesThatNeedLove.addAll(hs1);

		return municipalitiesThatNeedLove;
	}


	public HashMap<String, Double> landTypeForecast(LandTypeEnum originalLandType, LandTypeEnum newLandType) {

		HashMap<String, Double> forecast = new HashMap<String, Double>();

		double originalLandTrees = 0;
		double newLandTrees = 0;
		double totalTrees = tp.getTrees().size();

		for(Tree t : tp.getTrees()) {
			if(t.getLandTypes().equals(originalLandType)) {
				originalLandTrees++;
			}
			if(t.getLandTypes().equals(newLandType)) {
				newLandTrees++;
			}
		}

		double initialPercentOriginalLandTypeTrees = originalLandTrees/totalTrees;
		double initialPercentNewLandTypeTrees = newLandTrees/totalTrees;
		forecast.put("initialPercentOriginalLandTypeTrees", initialPercentOriginalLandTypeTrees);
		forecast.put("initialPercentNewLandTypeTrees", initialPercentNewLandTypeTrees);

		//for calculation purposes only
		HashMap<LandTypeEnum, Double> globalPercent = new HashMap<LandTypeEnum, Double>();
		globalPercent.put(LandTypeEnum.Institutional, .1);
		globalPercent.put(LandTypeEnum.Municipal, .05);
		globalPercent.put(LandTypeEnum.Park, .6);
		globalPercent.put(LandTypeEnum.Residential, .25);

		double finalPercentOriginalLandTypeTrees = 0;
		double finalPercentNewLandTypeTrees = (initialPercentOriginalLandTypeTrees*(globalPercent.get(originalLandType))) + initialPercentNewLandTypeTrees;
		forecast.put("finalPercentOriginalLandTypeTrees", finalPercentOriginalLandTypeTrees);
		forecast.put("finalPercentNewLandTypeTrees", finalPercentNewLandTypeTrees);

		//this might be wrong
		double totalChange = originalLandTrees - newLandTrees;
		forecast.put("totalChange", totalChange);

		double oxygenLost = totalChange * 260;  //260 pounds of oxygen produced per tree per year
		forecast.put("oxygenLost", oxygenLost);

		PersistenceXStream.saveToXMLwithXStream(tp);

		return forecast;
	}

	public Scientist createScientist (String name, String email, String id) throws Exception{
		if(findScientistByName(name)!=null) {
			throw new Exception("Scientist exits.");
		}

		Scientist sci = new Scientist(name, email, id, tp);
		tp.addUser(sci);
		PersistenceXStream.saveToXMLwithXStream(tp);
		return sci;
	}

	public Forecast makeForecast(String description) {
		Forecast forecast = new Forecast(description, tp);
		tp.addForecast(forecast);
		PersistenceXStream.saveToXMLwithXStream(tp);
		return forecast;
	}

	/*
	public User addUser(String name, String email, String id) {
		User u = new User(name, email, id, tp);
		tp.addUser(u);
		PersistenceXStream.saveToXMLwithXStream(tp);
		return u;
	}*/

	public List<LocalResident> findAllLocalResidents(){
		List<LocalResident> list = new ArrayList<LocalResident>();
		for(User user: tp.getUser()){
			if(user instanceof LocalResident){
				list.add((LocalResident)user);
			} 
		}

		return list;
	}
	
	public List<Scientist> findAllScientists(){
		List<Scientist> list = new ArrayList<Scientist>();
		for(User user: tp.getUser()){
			if(user instanceof Scientist){
				list.add((Scientist)user);
			} 
		}

		return list;
	}
}
