# Campus360

## Introduction

The goal of the Campus 360 app is to provide students, visitors, and staff with a seamless and intuitive indoor–outdoor navigation experience within the university campus. The app aims to centralize all essential campus information—buildings, rooms, services, facilities, and pathways—into one unified platform. By combining interactive maps, floor-level navigation, and contextual details about different locations, Campus 360 helps users quickly find their way, explore the campus, and access relevant information without confusion. Ultimately, the app improves campus accessibility, reduces navigation time, and enhances the overall user experience for new and existing members of the university community.

TODO: Add one or more screenshots of the app, when you finalized the UI.

## Architecture Overview

TODO: Add simple diagram that explains the architecture.

## User Persona
Campus360 is primarily designed for individuals who frequently navigate the 
BTH campus and still encounter minor navigation issues that slow them down. 
One common user type is someone like Maria, a 22-year-old student in her 
second semester. Even though she already knows the main buildings, she told 
us that she still struggles when her classes are scheduled in rooms she hasn't 
visited before. For example, some rooms are located deep inside long corridors, 
and sometimes two different wings look almost identical until you walk all the 
way in. She said she often checks the floor plans at the floor entrance but still 
gets confused because they only show a rough layout and not the exact route. 
For students like Maria, Campus360 would help by showing a clear sequence of 
steps, so she doesn't waste time searching from corridor to corridor.   

Another type of user is Rishi, a 23-year-old Master student who moves 
between various rooms for research meetings and lab work. When I spoke with 
him, he said the challenge isn’t the main hallways, but the smaller rooms 
tucked away behind labs or in corners that aren’t easy to spot. Since he uses 
many different rooms throughout the semester, it’s hard for him to remember 
all the locations. He said a tool like Campus360 would save him time, especially 
when he has back-to-back meetings and needs to quickly check the fastest way 
to reach a less common room. For him, the usefulness comes from accuracy 
rather than memorizing the layout. 

## User Stories

| **#** | **As a <type of user>** | **I want/need to <perform a task>**                                | **so that <achieve some goal>**                  | **Acceptance Criteria** |
| ----- | ----------------------- | ------------------------------------------------------------------ | ------------------------------------------------ | ----------------------- |
| US1   | As a user,              | I want to search for a lecture room by name or number,             | so that I can arrive on time without confusion.  | The user can type a room name or number into the search bar. The app displays the building and floor where the room is located. If the room does not exist, an error message is shown.                |
| US2   | As a user,              | I want to filter locations based on their type,                    | so that I have an overview of all meeting rooms. | The app displays filter options such as classrooms, labs, offices, and POIs. Selecting a category shows only matching rooms                 |
| US3   | As a user,              | I want visual navigation instructions for a location,              | so that I know in which direction I have to go.  | The app displays step by-step text directions. No live map navigation is included.                  |
| US4   | As a user,              | I want to explore points of interest like the library or cafeteria,| so that I can experience campus life.            | Points of interest are listed Each POI includes a short description.|
| US5   | As a user,              |I want to scan QR codes at entrances or doors,                      |so that I can know my exact current location.     | Scanning a valid QR instantly shows the current location. Invalid QR codes display an error message.|
| US6   | As a user,              |I want to preview all steps before I start walking,                 |so that I understand the full route in advance.   | The app displays the full list of steps in a scrollable view. A 'Start' button begins navigation.|
| US7   | As a user,              |I want a cancel button,                                             |so that I can stop the directions if I change my mind.| A visible 'Cancel' button appears during navigation. Tapping it stops navigation and returns to home.         |
| US8   | As a user,              |I want to see the estimated time required to reach the destination, |so that I know whether I will arrive on time          | The app calculates the estimated walking time using a predefined speed. The estimated time is displayed with directions.|
| US9   | As a user,              |I want the app to work offline,                                     |so that I can get directions without network issues.  | Searching, QR scanning, and navigation work without the internet. No features require online connectivity. |


### Development Status

Track the development status of the app.

Use
[ ] for not implemented
[x] for implemented.

[ ] US1

[ ] US2

[ ] US3

[ ] US4

[ ] US5

[ ] US6

[ ] US7

[ ] US8

## App Context and Data

### Context Diagram

<img width="751" height="141" alt="context_diagram_1234" src="https://github.com/user-attachments/assets/ace7ad2a-a7cd-4641-bcd2-3e2439dc514b" />

### What is the Targeted Hardware?

Platform: Android

OS version: Android 10 + 

Screen Size: 6.1-7 inches 

Sensors: None 

Connectivity: None (Not for core functionality)

### What data is being collected from the user?

Search queries 

Selected locations 

Saved locations 

Recently viewed locations 

No GPS data 

No user's personal information

### What are the sources of the data

Manually created buildings, floors, rooms... JSON files. 

Static images for floor maps 

User inputs

### Data Lifecycle

#### Session Data 

Current search 

Temporary navigation path 

Floor map view state 

#### Stored Locally 

Saved rooms 

Recently Viewed rooms 

Cached floor map images 

#### Stored on Server (Initially, this will be stored locally) 

Room Data

Building Data, …

## How to Use

### Build

TODO: Explain how the whole app can be build as an APK.

### Test

```sh
./gradlew test --rerun-tasks
```

### Run

TODO: Explain how to run the app on device or emulator.

## License

TODO: Add license and copyright notice. If you are not sure which license you should chose, have a look at [Choose a License](https://choosealicense.com).
