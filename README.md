# User Documentation for Project-16

## I. Badge:

- Aakarsh Shekhar: https://www.linkedin.com/in/aakarshshekhar/
- Alain Daccache
- Hisham Hawara: https://www.linkedin.com/in/hisham-hawara/, http://cs.mcgill.ca/~hhawar1/
- Ketan Rampurkar: https://www.linkedin.com/in/ketan-rampurkar/, http://krampu.ml
- Sandra Deng

*As a team, we wish to make the repository public after the end of the semester*

## II. Scope of Project:

- The TreePLE system has been implemented for the ECSE 321 course at McGill University, Winter 2018 Semester, Prof. Daniel Varro. 
- The TreePLE system provides urban scientists with an useful tool to assist them with urban development projects, and raises awareness to, as well as involves, the public about sustainability and its challenges.

## III. Feature List:

a. With the TreePLE system, the user can, via a Web frontend (intended for scientists and urban decision makers):
- Load initial tree data from a text file (.xml)
- Update tree data within the database (i.e. for correcting information)
- List all (or specific subset of) trees (filtering by municipalities, filtering by species)
- Automatically calculate sustainability attributes (biodiversity index, carbon sequestration)
- Provide forecasts for what-if scenarios (impact of changing land types, where and what type to plant to improve biodiversity)
- Bonus Feature: Locate trees on a map

b. With the TreePLE system, the user can, via an Android frontend (intended for foresters and local residents):
- Report if a tree is planted or cut down by the owner
- Mark trees which are diseased or to be cut down

### Screenshots of some key features: 
![Login](https://github.com/AlainDaccache98/TreePle/blob/master/img/Login.png)
![Overview](https://github.com/AlainDaccache98/TreePle/blob/master/img/List.png)
![Map](https://github.com/AlainDaccache98/TreePle/blob/master/img/Map.png)
![PlantTreeWeb](https://github.com/AlainDaccache98/TreePle/blob/master/img/Plant.png)
![Sustainability Attributes](https://github.com/AlainDaccache98/TreePle/blob/master/img/Attributes.png)
![Forecast](https://github.com/AlainDaccache98/TreePle/blob/master/img/Forecast.png)
![PlantTreeAndroid](https://github.com/AlainDaccache98/TreePle/blob/master/img/PlantAndroid.png)
![RemoveTreeAndroid](https://github.com/AlainDaccache98/TreePle/blob/master/img/RemoveAndroid.png)
![UpdateTreeAndroid](https://github.com/AlainDaccache98/TreePle/blob/master/img/UpdateAndroid.png)


## IV. Setup Instructions:

Tools needed: Ubuntu OS (or a virtual image), Android device (or Emulator), Eclipse, Tomcat server, REST client, Spring Framework
1. Clone the repository (https://github.mcgill.ca/ECSE321-2018-Winter/Project-16)
2. Import the project on Eclipse (File -> Import -> Gradle -> Existing Gradle Project -> Project Directory -> Finish)
3. From Eclipse, run the Spring Application class (Project-16/src/main/java/ca.mcgill.ecse321.TreePle/TreePleSpringApplication)
4. In a terminal go to the project directory's Web folder (i.e. cd git/Project-16/TreePle-Web) and run the command "npm install", then "npm run dev"
5. After the default browser pops up, go to the link: http://127.0.0.1:8087/#/home

## V. Project Management Report:

### Main responsibilities and leadership roles of each team member:

| Team Member     | Main Responsibilities                              | Leadership Roles
| ----------------| ---------------------------------------------------|--------------------------------|
| Alain Daccache  | Backend service (overview methods) & Web Frontend  | Reviewing deliverables         |
| Sandra Deng     | Persistence & Controller functionality             | Ensuring overall understanding |
| Hisham Hawara   | Backend service (forecast methods)                 | Assigning tasks                |
| Ketan Rampurkar | Android Frontend & Unit tests                      | Setting up meetings            |
| Aakarsh Shekhar | Backend service (management methods) & Web Frontend| Documenting meetings           |

### Hourly efforts dedicated by each member to the project:

| Team Member            | Deliverable 1 | Deliverable 2 | Deliverable 3 | Deliverable 4 | Deliverable 5 | Deliverable 6 | Total Sum |
| -----------------------|-------------- |---------------|---------------|---------------|---------------|---------------|-----------|
| **Alain Daccache**     | 5             |  13           | 10            | 23            |  5            |  22           | 78        |
| **Sandra Deng**        | 5             | 17            | 13.5          | 11.5          | 1             |   31          | 80        |
| **Hisham Hawara**      | 7             | 11            | 8             | 10            | 1             | 27            | 74        |
| **Ketan Rampurkar**    | 6             | 13            | 8             | 10            | 1             | 37            | 75        |
| **Aakarsh Shekhar**    | 7             | 11            | 9             | 24            | 1             | 25            | 77        |
| **Total Sum**          |       30      | 65            | 48.5          | 78.5          | 9             |   142         | 374       |

### Overview of major project iterations and progress:

**Deliverable 1:**
- We described the functional and non-functional system requirements.
- We pinpointed the actors and use cases of this project that will act on those requirements, as well as provided a detailed specification for the most important uses cases.
- We designed the domain model of the user story, the statechart for the class “Tree”, as well as a requirement-level activity diagram covering the main scenario for managing trees for all stakeholders
- We agreed on the work plan for the remaining iterations
- This deliverable has been submitted on Deadline (February 11)

**Deliverable 2:**
- We addressed the prototype implementation of the “plant tree”, “cut down tree” and “list all trees” use cases (Java Spring backend + Android frontend).
- We developed the architecture (block diagrams) and detailed design (class diagrams) of the proposed solution. We will split the group in two to tackle these two tasks, which should be done maximum one week before the deadline
- We implemented the sequence diagram for “Plant Tree” and “List All Trees” use cases covering all architectural layers. After describing and verifying with the teacher that the architecture is correct, we will again split the group in two, each responsible of a use case.
- This iteration was challenging to execute and requires the most effort compared to other deliverables, as we were newly introduced to the different types of diagrams, and the flowchart was not be completely clear at that stage.
- This deliverable has been submitted on Deadline (February 26)

**Deliverable 3:**
- We implemented the use cases that will provide the scientists the ability to analyze the impacts of their potential projects. This includes ”Analyze sustainability attributes”, “Calculate sustainability attributes”, and “Generate forecast”.
- We organized a quality assurance plan that documents on unit testing, component testing, and system testing. Therefore, we tested all implemented use cases.
- This iteration modified the nonfunctional requirements as we got a more concrete result of the predetermined measurements.
- Since the work regarding this iteration aligns with the spring break, it was challenging to find times to work altogether as a group. Since each testing phase requires that the previous one is completed, we cannot split the work for each phase. Therefore, we communicated through Skype and stay synchronized as much as possible.
- This deliverable has been submitted on Deadline (March 14)

**Deliverable 4:**
- We implemented the “locate tree on a map” use case.
- We provided a release pipeline plan along with a description.
- We conducted a controlled trial with randomly assigned waves of intervention.
- This iteration did not take much effort documentation, but we encountered some problems during the build phase with Jenkins regarding space allocation
- This deliverable has been submitted on Deadline (March 25)

**Deliverable 5:**
- We prepared slides for the presentation.
- We gave a presentation about our project to the class explaining features in detail.
- This task did not take much effort, so we prepared for deliverable 6 at the same time.
- This deliverable was the presentation, which took place on April 10th (class time)

**Deliverable 6:**
- We implemented all the features of the project
- We implemented unit test cases for each backend service method and commented the methods
- We commited the completed work to GitHub.
- This deliverable was due on April 11.
