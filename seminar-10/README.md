# Weather Map Application

A Kotlin-based web application that displays weather information for various cities around the world on an interactive map. The application fetches real-time weather data and city coordinates from the OpenWeather API and presents them through a web interface.

## Features

- Displays weather information for multiple cities worldwide
- Shows current temperature and weather conditions
- Provides current local time for each city
- Interactive web interface with a world map visualization
- RESTful API endpoints for accessing city data and time information

## Technologies Used

- **Kotlin**: Primary programming language
- **Ktor**: Web server and HTTP client framework
- **Kotlinx Serialization**: JSON serialization/deserialization
- **Kotlinx DateTime**: Date and time handling
- **OpenWeather API**: External service for weather data and geocoding

## Prerequisites

- JDK 21 or higher
- Gradle build tool
- OpenWeather API key

## Setup

1. Clone the repository
2. Set the OpenWeather API key as an environment variable:
   ```
   export WEATHER_API_KEY=your_api_key_here
   ```
3. Build the project:
   ```
   ./gradlew build
   ```

## Running the Application

Start the application using Gradle:

```
./gradlew run
```

The application will:
1. Initialize city coordinates from the OpenWeather API
2. Fetch weather data for all cities
3. Start a web server on http://localhost:8080

## API Endpoints

- **World Map UI**: `http://localhost:8080/`
- **City Time**: `http://localhost:8080/api/time/{cityName}`
- **All Cities Data**: `http://localhost:8080/api/cities`

## Project Structure

- **Main.kt**: Application entry point
- **Models**:
  - **City.kt**: Enum class representing cities with their properties
  - **Coordinates.kt**: Data class for geographical coordinates
- **API**:
  - **OpenWeatherApiClient.kt**: Client for interacting with the OpenWeather API
- **Services**:
  - **CityCoordinatesService.kt**: Service for initializing city coordinates
  - **CityWeatherService.kt**: Service for fetching and updating weather information
- **App**:
  - **WeatherApp.kt**: Web server and application logic
- **Serializers**:
  - **CitySerializer.kt**: Custom serializer for the City enum

## Getting an OpenWeather API Key

1. Register at [OpenWeather](https://openweathermap.org/)
2. Navigate to your account's "API keys" section
3. Generate a new API key or use the default one provided
4. Set up an environment variable named `WEATHER_API_KEY`, you can set it up in your [run configuration](https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html#add-environment-variables).
