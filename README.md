# weather

![Build status](https://travis-ci.org/vikrambodicherla/weather.svg?branch=master)

This unique Weather application is one of it's kind, letting you get the latest weather updates for over 5 hardcoded cities. This highly optimized application polls for updates every couple of seconds, ensuring that your battery doesn't last very long.

This application is a work-in-progress and will be for sometime. This application is a work-in-progress and will be some time. This application will be used as a schowcase for some concepts for AnDevCon 2017, including 
* Google's ![Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html) for a good architecture, 
* ![Danger](http://danger.systems) for automating code review chores, 
* ![Sonar](https://sonarcloud.io) for static code analysis, 
* deploying artifacts to ![Artifactory](https://www.jfrog.com/artifactory), 
* automated APK deployment to the playstore using ![Fastlane](https://fastlane.tools/).

Overall, this app should be a template project to achieve everything in the list above.

### Architecture components
This application tries to employ some of the new Arcitecture Components introduced by Google: https://developer.android.com/topic/libraries/architecture/index.html. Starting with ![Room](https://developer.android.com/topic/libraries/architecture/room.html) captured in the `com.markiv.weather.data` package, ![`WeatherConditions`](https://github.com/vikrambodicherla/weather/blob/master/weather-app/src/main/java/com/markiv/weather/data/WeatherConditions.java) is the model object and ![`WeatherConditionsDao`](https://github.com/vikrambodicherla/weather/blob/master/weather-app/src/main/java/com/markiv/weather/data/WeatherConditionsDao.java) is the corresponding DAO. 
