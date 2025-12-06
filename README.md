# Campus360

## Introduction

The goal of the Campus 360 app is to provide students, visitors, and staff with a seamless and intuitive indoor–outdoor navigation experience within the university campus. The app aims to centralize all essential campus information—buildings, rooms, services, facilities, and pathways—into one unified platform. By combining interactive maps, floor-level navigation, and contextual details about different locations, Campus 360 helps users quickly find their way, explore the campus, and access relevant information without confusion. Ultimately, the app improves campus accessibility, reduces navigation time, and enhances the overall user experience for new and existing members of the university community.

## Targeted Users
Campus360 is designed for individuals who frequently navigate the BTH campus but still face
minor difficulties locating specific rooms or facilities. Although many students and staff
members are familiar with the main buildings, they often experience confusion when they
must find rooms located deep inside long corridors or in sections of the campus that look
very similar. The existing floor plans at building entrances provide only a rough overview and
do not help users understand the exact route they need to take.

The app, therefore, targets students, staff, and visitors who want clearer, more reliable
indoor navigation support. Whether they are attending a lecture, moving between labs for
research, or locating campus facilities such as cafeterias or study areas, Campus360 offers
simple text-based guidance that helps users move efficiently within the campus.

## User Personas
Persona 1: Maria – The New Student
Maria is a 22-year-old student in her second semester at BTH. Although she knows the main
buildings, she often struggles to find rooms she has never visited before, especially when
they are located far inside long corridors. She mentioned that two different wings
sometimes look nearly identical, which makes it easy to walk in the wrong direction. Even
when she uses the floor plans near the entrances, she still finds them confusing because
they only give a general layout without showing the precise route.
Campus360 helps Maria by giving her clear, ordered steps that guide her directly to her
room, allowing her to navigate confidently without wasting time.

Persona 2: Dr Sahadev – The Professor
Dr Sahadev is a 45-year-old professor who frequently moves between different classrooms,
meeting rooms, and labs to teach, supervise thesis students, and attend departmental
discussions. Although he has been at BTH for several years, he still finds it challenging to
remember the exact location of certain rooms that he visits less often, especially those
situated deep inside long corridors or in areas with a similar layout. On days when his
schedule is tight, even a small delay caused by searching for the right room can disrupt his
teaching or meeting plans.
Campus360 supports Dr Sahadev by providing quick and accurate text-based indoor
directions, allowing him to easily find rooms he does not visit regularly and ensuring he can
move efficiently between locations throughout his busy day.

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

To generate an APK for the Campus360 app:
1. Ensure you have the required tools installed:
   * Android Studio
   * Android SDK 33 or higher
2. Clone the repository:
   ```sh
   git clone https://github.com/bth-dipt-pa1469/h25-team07-campus360
   cd Campus360
   ```
3. Build the APK using Gradle:
   ```sh
   ./gradlew assembleRelease
   ```
4. After the build completes, the release APK will be available at:
   ```sh
   app/build/outputs/apk/release/app-release.apk
   ```
You can also build the APK directly from Android Studio using:

Build → Build Bundle(s) / APK(s) → Build APK(s).

### Test

```sh
./gradlew test --rerun-tasks
```

### Run

You can run the Campus360 app either on an Android emulator or a physical device.

Run on an Emulator:
1. Open the project in Android Studio.
2. Create or select an existing Android Virtual Device.
3. Press Run to install and launch the app on the emulator.

Run on a Physical Device:
1. Enable Developer Options on your device.
2. Turn on USB Debugging.
3. Connect the device to your PC via USB.
4. Install the APK manually:
   ```sh
   adb install app-release.apk
   ```
   Or press Run in Android Studio and choose your device.

## License

TODO: Add license and copyright notice. If you are not sure which license you should chose, have a look at [Choose a License](https://choosealicense.com).
