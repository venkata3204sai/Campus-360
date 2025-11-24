# Campus360

## Introduction

TODO: Add brief introduction to the apps goal.

TODO: Add one or more screenshots of the app, when you finalized the UI.

## Architecture Overview

TODO: Add simple diagram that explains the architecture.

## User Stories

| **#** | **As a <type of user>** | **I want/need to <perform a task>**                                | **so that <achieve some goal>**                  | **Acceptance Criteria** |
| ----- | ----------------------- | ------------------------------------------------------------------ | ------------------------------------------------ | ----------------------- |
| US1   | As a user,              | I want to search for a lecture room by name or number,             | so that I can arrive on time without confusion.  | Enter room name/number. Shows building + floor. Error if not found                |
| US2   | As a user,              | I want to filter locations based on their type,                    | so that I have an overview of all meeting rooms. | Filters: classrooms, labs,offices, points of interest. Shows only matching rooms                 |
| US3   | As a user,              | I want visual navigation instructions for a location,              | so that I know in which direction I have to go.  | Directions shown as text steps.No map navigation (can be  implemented in future)                  |
| US4   | As a user,              | I want to explore points of interest like the library or cafeteria,| so that I can experience campus life.            | Points of interest listed. Each has a description|
| US5   | As a user,              |I want to scan QR codes at entrances or doors,                      |so that I can know my exact current location.     | Scans instantly. Shows “You are at: ___”. Invalid QR → error message|
| US6   | As a user,              |I want to preview all steps before I start walking,                 |so that I understand the full route in advance.   | Full list shown. Scrollable. Start button|
| US7   | As a user,              |I want a cancel button,                                             |so that I can stop the directions if I change my mind.| A “Cancel” button is visible during step-bystep directions. App returns to the home screen         |
| US8   | As a user,              |I want to see the estimated time required to reach the destination, |so that I know whether I will arrive on time          | Shows estimated walking time.Based on predefined speed|
| US9   | As a user,              |I want the app to work offline,                                     |so that I can get directions without network issues.  | Scanning QR codes works offline. Searching for rooms works offline. No features require the internet to function              |


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

<img width="541" height="441" alt="Campus360_context_diagram" src="https://github.com/user-attachments/assets/69cfb3a9-c4d3-4d5c-a61d-1a975db7eee3" />

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
