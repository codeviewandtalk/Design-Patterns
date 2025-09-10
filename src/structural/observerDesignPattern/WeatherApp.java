package structural.observerDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class WeatherApp {

    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        Observer phoneDisplay = new PhoneDisplay();
        Observer tvDisplay = new TVDisplay();

        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(tvDisplay);


        //weatherStation.setWeather("Sunny");
        weatherStation.setWeather("Rainy");

    }

}

// Subject Interface
interface Subject {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver();
}
// Observer Interface
interface Observer {
    void update(String weather);
}

// ConcreteSubject Class
class WeatherStation implements Subject {
    List<Observer> observers = new ArrayList();
    private String weather;


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(weather);
        }
    }

    public void setWeather(String newWeather) {
        this.weather = newWeather;
        notifyObserver();
    }
}

// ConcreteObserver Class
class PhoneDisplay implements Observer {

    private String weather;

    @Override
    public void update(String weather) {
        this.weather = weather;
        display();
    }

    private void display() {
        System.out.println("Phone Display: Weather updated - " + weather);
    }
}

// ConcreteObserver Class
class TVDisplay implements Observer {
    private String weather;

    @Override
    public void update(String weather) {
        this.weather = weather;
        display();
    }

    private void display() {
        System.out.println("TV Display: Weather updated - " + weather);
    }
}