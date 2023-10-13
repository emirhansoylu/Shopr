## Shopr
Simple Android application cart application that shows and caches product from API.

The project developed with multi-moduled architecure. This provides separation of concerns, and all of modules has own responsibilities.
<br />
 - Model module includes database and network models.

 - Database module includes model module and reads and writes data to local storage with room library. In the database module, dependency injection used with simple kotlin object. I only expose repository class for other modules, other classes are internal or private. Database repository developed as an api and it does the processes in background and error safety. Database module also has instrumented test, that coverages database repository.

 - Network module developed as same as database module. I’ve used Ktor library for network requests. This module has simple dependency injection, error handling and repository does the tasks in background thread. Network module also has unit tests that coverages NetworkRepository functions.
 - Application module imports all of the modules. I’ve used feature based packaging. There are four packages in app module. DI includes Hilt dependency injection module. Domain package includes some extensions for prevent code repetition and repository for handle database and network requests and caching. While developing application, I’ve used __single activity pattern__ and I’ve used _Navigation Component_ for switching fragments. With the MVVM pattern view should dumb, that only observes the changes of ViewModel. When the ViewModel initializes, it has loading state then it gets product list from repository. When the response comes, ViewModel emits the UI state with StateFlow then UI collects the result.

## Here are the screen recordings of application

### Light & Portrait Mode

![Light Mode](portrait_light.gif)

### Dark & Portrait Mode

![Light Mode](portrait_dark.gif)

### Light & Land Mode

![Light Mode](land_light.gif)

### Dark & Land Mode

![Light Mode](land_dark.gif)

## Highlighted Specs
<img align="left" alt="Deno" width="24px" src="https://user-images.githubusercontent.com/6463980/28998869-97bca9dc-7a03-11e7-8a95-3bbe9c1f7926.png"/> Developed with Kotlin!
- ⚡ Dark/Light, Portrait/Land and different density support.
- ⚡ Multi moduled architecture. (app, database, network, model)
- ⚡ Unit and instrumented tests on database and network modules.
- ⚡ Used Android Architecture and modern programming libraries. (such as Ktor, Hilt, Glide, Navigation Component, Splash Screen Api, Room, Coroutines, Flow)
- ⚡ Preferred Material 3 design principles.
- ⚡ Single Activity pattern and transaction animations.
- ⚡ Caching and error handling.
<br />

## Extras
- 💪 MVVM Pattern
- 💪 Reactive programming with flows and coroutines
- 💪 Received sensitive properties from BuildConfigField
