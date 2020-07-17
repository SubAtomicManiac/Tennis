# TennisApp

A simple app to keep track of the score for a single game of tennis

  - App can be ran by starting the project up in Android Studio and hitting run
  - Top the left and right sides of the screen to score for each player
  - Rename each player by tapping their name
  - Reset the game to 0 - 0 with the reset button at the bottom

# Architecture

This app uses a MVVMPC architecture with the following components

  - Model: Is effectively the output from the domain layer
  - View: The android activity and it's layout responsible for UI and user/system events
  - ViewModel: A simple datastore that is observed by the view to populate it's UI
  - Presenter: The gateway mechanism that translates the domains output for UI consumption
  - Controller: The mechanism where the view can trigger and make requests against the domain

![Clean Architecture](https://jeremiahflaga.github.io/images/2017/CleanArchitectureDesignByUncleBobMartin.png)
This is Robert C. Martin's diagram of what clean architecture would typically look like in an application.
The diagram specifically represents the flow of a synchronous web application hosted by a server.
Mobile applications and asynchronous web applications would have another link between the view and the controller.
Mobile applications will not always have a significant domain in the source of the application, as this is more often than not sitting on a server.

# An example flow using MVVMPC:

  - A user opens up the tennis application and it's view is instantiated
  - The view will have it's controller and viewmodel injected
  - This controller will have the domain components it needs injected
  - The controller can now request the domain to create a game
  - When a user taps for a player score a message is sent to the domain
  - The domain will handle the message and pass it's output to the presenter
  - The presenter will translate the output into a simple type ready for viewmodel's consumption
  - The viewmodel will update it's value and the view will observe and reflect the changes to the UI

# Advantage to MVVMPC to MVVM

  - Very clear separations of concerns which simplifies keeping logic out of the viewmodel
  - With an event driven approach it's easy to summarise the applications functionality
  - Smaller classes are faster to setup and easier to test
  - Completely decoupled as only the View has a reference to other components (viewmodel & controller)

# Improvements to make on app

  - Use Dagger2 to inject the TennisGame and TennisPresenter into the TennisController
  - Make events cleaner to use if domain, presenter or neither are needed


# final notes

  - Please treat the library package as a Work in progress library and not considered part of the app
  - More documentation in app source
