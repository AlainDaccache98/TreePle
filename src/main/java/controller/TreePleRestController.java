package ca.mcgill.ecse321.TreePle.controller;


import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import ca.mcgill.ecse321.TreePle.dto.ForecastDto;
import ca.mcgill.ecse321.TreePle.dto.LocalResidentDto;
import ca.mcgill.ecse321.TreePle.dto.LocationDto;
import ca.mcgill.ecse321.TreePle.dto.PropertyDto;
import ca.mcgill.ecse321.TreePle.dto.ScientistDto;
import ca.mcgill.ecse321.TreePle.dto.SurveyDto;
import ca.mcgill.ecse321.TreePle.dto.SustainabilityAttributeDto;
import ca.mcgill.ecse321.TreePle.dto.TreeDto;
import ca.mcgill.ecse321.TreePle.dto.UserDto;
import ca.mcgill.ecse321.TreePle.model.Forecast;
import ca.mcgill.ecse321.TreePle.model.LocalResident;
import ca.mcgill.ecse321.TreePle.model.Location;
import ca.mcgill.ecse321.TreePle.model.Property;
import ca.mcgill.ecse321.TreePle.model.Scientist;
import ca.mcgill.ecse321.TreePle.model.Survey;
import ca.mcgill.ecse321.TreePle.model.Survey.StatusEnum;
import ca.mcgill.ecse321.TreePle.model.SustainabilityAttribute;
import ca.mcgill.ecse321.TreePle.model.Tree;
import ca.mcgill.ecse321.TreePle.model.TreePLE;
import ca.mcgill.ecse321.TreePle.model.User;
import ca.mcgill.ecse321.TreePle.model.Tree.LandTypeEnum;
import ca.mcgill.ecse321.TreePle.model.Tree.SpeciesEnum;
import ca.mcgill.ecse321.TreePle.service.InvalidInputException;
import ca.mcgill.ecse321.TreePle.service.TreePleService;



@RestController
public class TreePleRestController {
	@Autowired
	private TreePleService service;

	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping("/")
	public String index() {
		return "TreePLE application root. Android frontend is a TODO. Use the REST API to manage planting, removing and listing out all trees.\n";
	}
	
	// Conversion methods (not part of the API)
	private TreeDto convertToDto(Tree t) {
		// In simple cases, the mapper service is convenient
		LocationDto locDto = convertToDto(t.getLocation());
		List<SurveyDto> listSurvey = new ArrayList<SurveyDto>();
		for(Survey s : t.getSurveies()) {
			listSurvey.add(convertToDto(s));
		}
		TreeDto tDto = new TreeDto(t.getMunicipality(),t.getLandTypes(),t.getHeight(),t.getDiamOfCanopy(),t.getDatePlanted(),t.getDateModified(),t.getSpecies(),locDto, t.getId(), listSurvey);
		return tDto;
	}

	private SurveyDto convertToDto(Survey s) {
	//not finish!!!
		float treeId = s.getTrees().getId();
		ScientistDto sciDto = convertToDto(s.getUser());
		/*List<SustainabilityAttributeDto> saList = new ArrayList<SustainabilityAttributeDto> ();
		for(SustainabilityAttribute sa : s.getSustainabilityAtributes()) {
			saList.add(convertToDto(sa));
		}
		List<ForecastDto> fDto = new ArrayList<ForecastDto>();
		for(Forecast f : s.getForecasts()) {
			fDto.add(convertToDto(f));
		}*/
		//
		SurveyDto sDto = new SurveyDto(s.getDate(),s.getStatus(), treeId, sciDto);
		return sDto;
	}
	
	private LocalResidentDto convertToDto(LocalResident l) {
		//LocalResidentDto ldto = new LocalResidentDto(l.getName());
		//no converter found for return value of type LocalResidentDto...
		  //return modelMapper.map(l, LocalResidentDto.class);
		PropertyDto pDto = convertToDto(l.getProperty(0));
		LocalResidentDto lrDto = new LocalResidentDto(l.getName(), l.getEmail(), l.getUserID(), pDto);
		System.out.println("1");
		return lrDto;
		}

	private ScientistDto convertToDto(Scientist s) {
			ScientistDto sdto = new ScientistDto(s.getName(),s.getEmail(),s.getUserID());
		  return sdto;
		}
	
	private UserDto convertToDto(User s) {
		  return modelMapper.map(s, UserDto.class);
		}
		
	private ForecastDto convertToDto(Forecast f) {
		ForecastDto fDto = new ForecastDto(f.getDescription());
		return fDto;
	}
	
	private PropertyDto convertToDto(Property p) {
		LocationDto locDto = convertToDto(p.getLocation());
		
		PropertyDto proDto = new PropertyDto(p.getLength(), p.getWidth(), locDto);
		
		return proDto;
	}
	
	private LocationDto convertToDto(Location loc) {
		LocationDto locDto = new LocationDto(loc.getLatitude(),loc.getLongitude());
		return locDto;
	}
	
	private SustainabilityAttributeDto convertToDto (Double index) {
		SustainabilityAttributeDto SustainablityAttributeDto = new SustainabilityAttributeDto(index);
		return SustainablityAttributeDto;
	}
	
	private Tree convertToDomainObject(TreeDto tDto) {
		// Mapping DTO to the domain object without using the mapper
		List<Tree> allTrees = service.findAllTrees();
		for (Tree tree : allTrees) {
			if (tree.getLocation().equals(tDto.getLocation())) {
				return tree;
			}
		}
		return null;
	}
	
	//plant tree
	//URL format http://localhost:8088/trees?height=10&landType=Residential&diamOfCanopy=10&latitude=10&longitude=10&municipality=Montreal&datePlanted=2018-09-23&dateModified=2018-09-23&species=Species1&localResident=lr1
	@PostMapping(value = { "/trees", "/trees/" })
	public TreeDto plantTree(
			@RequestParam("height") float aHeight,
			@RequestParam("landType") LandTypeEnum aLandType,
			@RequestParam("diamOfCanopy") float aDiamOfCanopy,
			@RequestParam("latitude") float latitude,
			@RequestParam("longitude") float longitude,
			@RequestParam("municipality") String aMunicipality,
			@RequestParam("datePlanted") Date aDatePlanted,
			@RequestParam("dateModified") Date aDateModified,
			@RequestParam("species") SpeciesEnum aSpecies,
			@RequestParam("localResident") String localResident) throws InvalidInputException {
		Location aLocation = new Location(latitude,longitude);
		LocalResident aLocalResident = service.findLocalResidentByName(localResident);
		Tree tree = service.plantTree(aHeight,aLandType, aDiamOfCanopy, aLocation,  
									aMunicipality, aDatePlanted, aDateModified, aSpecies, aLocalResident);
		return convertToDto(tree);
	}
	
	//sustainablity
	//URL format http://localhost:8088/trees?municipality=Montreal&species=Species1
	@PostMapping(value = { "/sustainability", "/sustainability" })
	public SustainabilityAttributeDto biodiversityIndex(
			@RequestParam("municipality") String aMunicipality,
			@RequestParam("species") SpeciesEnum aSpecies) throws Exception {
		Double bioIndex = service.bioDiversityIndex(aSpecies, aMunicipality);
//		System.out.println("giuegaeeeeeefq" + bioIndex);
//		System.out.println("aiuiawbd" + aSpecies);
//		System.out.println("wpadniidnwaip" + aMunicipality);

		return convertToDto(bioIndex);
	}
	//url http://localhost:8088/trees
	@GetMapping(value = {"/sustainability/municipality/species/index"})
	public SustainabilityAttributeDto bioIndex(
			@RequestParam ("index") Double bioIndex,
			@RequestParam ("municipality") String aMunicipality ,
			@RequestParam ("species") SpeciesEnum aSpecies) throws Exception{
		SustainabilityAttributeDto indexDto = new SustainabilityAttributeDto(service.bioDiversityIndex(aSpecies,aMunicipality));
		return indexDto;
	}
	
	//sequestration
	//URL format http://localhost:8088/trees?municipality=Montreal&species=Species1
	@PostMapping(value = { "/sequestration", "/sequestration" })
	public SustainabilityAttributeDto sequestrationDto(
			@RequestParam ("height") Double height,
			@RequestParam ("diameter") Double diameter,
			@RequestParam ("growthFactor") Double growthFactor ,
			@RequestParam ("species") SpeciesEnum aSpecies) throws Exception {
		Double sequestrationIndex = service.sequestration(aSpecies, diameter, growthFactor, height);
		System.out.println("giuegaeeeeeefq" + sequestrationIndex);
		
		return convertToDto(sequestrationIndex);
	}
	
	//url http://localhost:8088/trees
	@GetMapping(value = {"/sequestration/height/species/diameter/growthFactor"})
	public SustainabilityAttributeDto sequestrationIndex(
			@RequestParam ("height") Double height,
			@RequestParam ("diameter") Double diameter,
			@RequestParam ("growthFactor") Double growthFactor ,
			@RequestParam ("species") SpeciesEnum aSpecies) throws Exception{
		SustainabilityAttributeDto indexDto = new SustainabilityAttributeDto(service.sequestration(aSpecies, diameter, growthFactor, height));
		return indexDto;
	}
	
	 //not working yet..
	//http://localhost:8088/treeat?latitude=12.0&longitude=12.0
	@GetMapping(value = { "/treeat", "/treeat/" })
	public TreeDto getTreeByLocation(
			@RequestParam ("latitude") float lat,
			@RequestParam ("longitude") float lon) throws InvalidInputException {
		Location location = new Location(lat,lon);
		Tree t = service.getTreeByLocation(location);
		return convertToDto(t);
	}	
		
	//url http://localhost:8088/trees/1.0?name=lr1
	@PostMapping(value = { "/remove", "/remove/" })
	public void removeTree (
			@RequestParam("id") float id,
			@RequestParam("localResident") String name) throws IOException, Exception {
		LocalResident lr= service.findLocalResidentByName(name);
		Tree tr = service.findTreeById(id);
		service.removeTree(tr, lr);
	}
	
	//url http://localhost:8088/trees
	@GetMapping(value = {"/trees"})
	public List<TreeDto> findAllTrees(){
		List<TreeDto> trees = new ArrayList<TreeDto>();
		for(Tree tree : service.findAllTrees()) {
			trees.add(convertToDto(tree));
		}
		return trees;
	}
	
	//url http://localhost:8088/trees/Montreal
	@GetMapping(value = {"/trees/municipality/{municipality}", "/trees/municipality/{municipality}/"})
	public List<TreeDto> getTreesByMunicipality(
			@PathVariable("municipality") String municipality) throws InvalidInputException{
		List<TreeDto> trees = new ArrayList<TreeDto>();
		for(Tree tree: service.listTreesByMunicipality(municipality)) {
			trees.add(convertToDto(tree));
		}
		return trees;
	}
	
	//url http://localhost:8088/trees/species1
	@GetMapping(value = {"/trees/species/{species}", "/trees/species/{species}/"})
	public List<TreeDto> getTreesBySpecies(
			@PathVariable("species") String species) throws InvalidInputException{
		List<TreeDto> trees = new ArrayList<TreeDto>();
		for(Tree tree: service.listTreesBySpecies(species)) {
			trees.add(convertToDto(tree));
		}
		return trees;
	}
	
	//url http://localhost:8088/species
	@GetMapping(value = {"/species", "/species/"})
	public List<String> getSpeciesList(){
		List<String> speciesList = new ArrayList<String>();
		for(SpeciesEnum se: SpeciesEnum.values()) {
			speciesList.add(se.name());
		}
		return speciesList;
	}
	
	//url http://localhost:8088/municipalities
	@GetMapping(value= {"/municipalities", "municipalities/"})
	public List<String> getMunicipality(){
		return service.getListOfMunicipality();
	}
	
	//url format http://localhost:8088/localresident/lr1?email=123@123&id=a&width=100&length=100&latitude=10&longitude=10
	@PostMapping(value= {"/localresident/{name}","/localresident/{name}/"})
	public LocalResidentDto createLocalResident(
			@PathVariable("name") String name,
			@RequestParam("email") String email,
			@RequestParam("id") String id,
			@RequestParam("width") float width,
			@RequestParam("length") float length,
			@RequestParam("latitude") float latitude,
			@RequestParam("longitude") float longitude) throws Exception {
		LocalResident lr = service.createLocalResident(name, email, id, length, width, latitude, longitude);
		//System.out.println("2");
		return convertToDto(lr);
	}
	
	//url format http://localhost:8088/addproperty/lr1?width=100&length=100&latitude=60&longitude=50
	@PostMapping(value= {"/addproperty/{name}","/localresident/{name}/"})
	public PropertyDto addProperty(
			@PathVariable("name") String name,
			@RequestParam("width") float width,
			@RequestParam("length") float length,
			@RequestParam("latitude") float latitude,
			@RequestParam("longitude") float longitude) {
		Property p = service.addProperty(name, length, width, latitude, longitude);
		return convertToDto(p);
		
	}
	
	//not finish!!!
	//http://localhost:8088/update/2.0?status=Planted&height=12.0&landType=Residential&latitude=10.0&longitude=10.0&municipality=Montreal&diamOfCanopy=12.0&name=sci10
	@PostMapping (value= {"/update/{id}", "/update/{id}/"})
	public SurveyDto updateTree(
			@PathVariable("id") float id,
			@RequestParam("status") String status,
			@RequestParam("height") float height,
			@RequestParam("landType") String landType,
			@RequestParam("latitude") float latitude,
			@RequestParam("longitude") float longitude,
			@RequestParam("municipality") String municipality,
			@RequestParam("diamOfCanopy") float diamOfCanopy,
			@RequestParam("name") String name) throws Exception {
		Scientist sci = service.findScientistByName(name);
		Survey survey = service.updateTreeData(id, status, height, landType, latitude, longitude, municipality, diamOfCanopy, sci);
		return convertToDto(survey);
	}
	
	//url format http://localhost:8088/scientist/sci?email=123@123&id=a
	@PostMapping(value= {"/scientist/{name}","/scientist/{name}/"})
	public ScientistDto createScientist(
			@PathVariable("name") String name,
			@RequestParam("email") String email,
			@RequestParam("id") String id) throws Exception {
		Scientist sci = service.createScientist(name, email, id);
		//System.out.println("2");
		return convertToDto(sci);
	}
	
	/*
	//http://localhost:8088/user/u1?email=123@123&id=a
	@PostMapping (value= {"/user/{name}", "/user/{name}/"})
	public UserDto addUser(
			@PathVariable("name") String name,
			@RequestParam("email") String email,
			@RequestParam("id") String id){
		User u = service.addUser(name, email, id);
		return convertToDto(u);	
	}*/
	
	@GetMapping(value= {"/landtype","/landtype/"})
	public List<String> getLandType(){
		List<String> list = new ArrayList<String>();
		for(LandTypeEnum le: LandTypeEnum.values()) {
			list.add(le.name());
		}
		return list;
	}
	
	@GetMapping(value = {"/trees/landtype/{landtype}", "/trees/landtype/{landtype}/"})
	public List<TreeDto> getTreesByLandType(
			@PathVariable("landtype") String landtype) throws InvalidInputException{
		List<TreeDto> trees = new ArrayList<TreeDto>();
		for(Tree tree: service.listTreesByLandType(landtype)) {
			trees.add(convertToDto(tree));
		}
		return trees;
	}
	
	@GetMapping(value= {"/status","/status/"})
	public List<String> getStatus(){
		List<String> list = new ArrayList<String>();
		for(StatusEnum se: StatusEnum.values()) {
			list.add(se.name());
		}
		return list;
	}
	
		// url format http://localhost:8088/forecast/municipality/montreal
	
//	@GetMapping(value = {"/forecastMunicipality/municipality/"})
//	public List<SpeciesEnum> forecastMunicipality(@PathVariable("municipality")String municipality) throws Exception {
//	return service.forecastMunicipality(municipality);
//	}
//	
//
//	// url format http://localhost:8088/forecast/species/oaktree
//	
//	@GetMapping(value = {"/forecast/species/{species}"})
//	public List<String> forecastSpecies(@PathVariable("species")SpeciesEnum species) throws Exception {
//	return service.forcastSpecies(species);
//	}
//	
//	//  url format http://localhost:8088/forecast/landTypeChange?original=aLandType1&new=aLandType2
//	@PostMapping(value = { "/forecast/landTypeChange", "/forecast/landTypeChange/" })
//	public HashMap<String, Integer> landTypeForecast(
//	@RequestParam("landTypeOriginal") LandTypeEnum aLandType1,
//	@RequestParam("landTypeNew") LandTypeEnum aLandType2) {
//		return service.landTypeForecast(aLandType1, aLandType2);
//	}
	
	
	//forecastMunicipality
	//URL format http://localhost:8088/trees?municipality=Montreal&species=Species1
	@PostMapping(value = { "/forecastMunicipality", "/forecastMunicipality/" })
	public ForecastDto forecastMunicipalityDto(
			@RequestParam ("municipality") String aMunicipality) throws Exception {
		String nums = service.forecastMunicipality(aMunicipality);		

		String ans = "Species to plant in order to improve biodiversity in this area: " + nums;
		Forecast f = service.makeForecast(ans);
		System.out.println(f.getDescription());
		return convertToDto(f);
	}

	//	//url http://localhost:8088/trees
	//	@GetMapping(value = {"/forecastMunicipality/municipality"})
	//	public ForecastDto forecastMunicipalityDescription(
	//			@RequestParam ("municipality") String aMunicipality) throws Exception {
	//		List<SpeciesEnum> list = service.forecastMunicipality(aMunicipality);
	//		String ans = "abcd";
	//		ForecastDto indexDto = new ForecastDto(ans);
	//		return indexDto;
	//	}


	//forecastSpecies
	//URL format http://localhost:8088/trees?municipality=Montreal&species=Species1
	@PostMapping(value = { "/forecastSpecies", "/forecastSpecies/" })
	public ForecastDto forecastSpeciesDto(
			@RequestParam ("species") SpeciesEnum aSpecies) throws Exception {
		List<String> description = service.forcastSpecies(aSpecies);
		String ans = "";
		if (description.size()==0) {
			ans = "Species is diverse";
		}
		else {
		ans = "Municipalities in which to plant in order to improve biodiversity of this species: " + description;	
		}
		Forecast f = service.makeForecast(ans);
		System.out.println(f.getDescription());
		return convertToDto(f);
	}
	
//	//url http://localhost:8088/trees
//	@GetMapping(value = {"/forecastSpecies/species"})
//	public ForecastDto forecastSpeciesDescription(
//			@RequestParam ("species") SpeciesEnum aSpecies) throws Exception {
//		List<String> description = service.forcastSpecies(aSpecies);
//		String ans = "abcd" + description;
//		ForecastDto indexDto = new ForecastDto(ans);
//		return indexDto;
//	}
	
		//forecastLandTypes
	//URL format http://localhost:8088/trees?municipality=Montreal&species=Species1
	@PostMapping(value = { "/forecastLandType", "/forecastLandType/" })
	public ForecastDto forecastLandTypeDto(
			@RequestParam ("selectedLandTypeOld") LandTypeEnum originalLandType,
			@RequestParam ("selectedLandTypeNew") LandTypeEnum newLandType) throws Exception {
		HashMap<String, Double> values = service.landTypeForecast(originalLandType, newLandType);
		String ans = "This conversion of land type will have the following impacts: The initial percentage of trees in the "
				+ "old land type is " + values.values().toArray()[0] + ", the initial percentage of trees in the new land type is "
				+ values.values().toArray()[1] + ", the final percentage of trees in the old land type is " 
		+ values.values().toArray()[2] + ", the final percentage of trees in the new land type is " 
				+ values.values().toArray()[3] + ", the total change is " + values.values().toArray()[2] 
						+ ", and the oxygen lost is " + values.values().toArray()[4];
		Forecast f = service.makeForecast(ans);
		System.out.println(f.getDescription());
		return convertToDto(f);
	}

	
	@GetMapping(value= {"/localresident","/localresident/"})
	public List<LocalResidentDto> findAllLocalResidents(){
		List<LocalResidentDto> lrList = Lists.newArrayList();
		for (LocalResident lr : service.findAllLocalResidents()) {
			lrList.add(convertToDto(lr));
		}
		return lrList;
	}
	

	@GetMapping(value= {"/scientist","/scientist/"})
	public List<ScientistDto> findAllScientists(){
		List<ScientistDto> scList = Lists.newArrayList();
		for (Scientist sc : service.findAllScientists()) {
			scList.add(convertToDto(sc));
		}
		return scList;
	}
	
	@GetMapping(value= {"/findtree/{id}","/findtree/{id}/"})
	public TreeDto findTreeById(
			@PathVariable float id) throws InvalidInputException{
		Tree tree = service.findTreeById(id);
		return convertToDto(tree);
	}
	
}

