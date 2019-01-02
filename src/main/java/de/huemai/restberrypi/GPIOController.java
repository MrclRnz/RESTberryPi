package de.huemai.restberrypi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GPIOController {

    static {
        gpio = GpioFactory.getInstance();
        initializeCofeePins();
    }

    final static GpioController gpio;
    static GpioPinDigitalOutput one_cup;
    static GpioPinDigitalOutput power;
    static GpioPinDigitalOutput two_cup;
    static boolean powerTurnedOn = false;
    static Calendar turnedOnDate = null;

    public static void initializeCofeePins() {
        one_cup = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "one_cup", PinState.HIGH);
        power = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "power", PinState.HIGH);
        two_cup = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "two_cup", PinState.HIGH);

        one_cup.setShutdownOptions(true, PinState.LOW);
        power.setShutdownOptions(true, PinState.LOW);
        two_cup.setShutdownOptions(true, PinState.LOW);
    }

    public static boolean checkInitialization() {
        boolean successful = true;

        if (gpio == null || one_cup == null || power == null || two_cup == null) {
            successful = false;
        }

        return successful;
    }

    public static boolean isTurnedOn() {
        return powerTurnedOn;
    }

    public static boolean isBoiled() {
        boolean boiled = false;
        if (turnedOnDate != null) {
            turnedOnDate.add(Calendar.SECOND, 30);
            if (turnedOnDate.getTimeInMillis() - (Calendar.getInstance().getTimeInMillis()) < 0) {
                boiled = true;
            }
        }

        return boiled;
    }

    public static void togglePower() {
        power.toggle();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GPIOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        powerTurnedOn = !powerTurnedOn;
        if (powerTurnedOn) {
            turnedOnDate = Calendar.getInstance();
        } else {
            turnedOnDate = null;
        }
        power.toggle();
    }

    public static void toggleOneCup() {
        one_cup.toggle();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GPIOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        one_cup.toggle();
    }

    public static void toggleTwoCups() {
        two_cup.toggle();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GPIOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        one_cup.toggle();
    }

    public static void shutdownGPIO() {
        gpio.shutdown();
    }
}
