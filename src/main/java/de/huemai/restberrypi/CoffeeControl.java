/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.huemai.restberrypi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/CoffeeControl")
public class CoffeeControl {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/power_an")
    public String turnOnPower() {
        if (GPIOController.checkInitialization()) {
            if (GPIOController.isTurnedOn()) {
                return "Kaffeemaschine ist schon an!";
            } else {
                GPIOController.togglePower();
                return "ok!";
            }
        } else {
            return "Fehler bei der Initialisierung der Pins!";
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/power_aus")
    public String turnOffPower() {
        if (GPIOController.checkInitialization()) {
            if (!GPIOController.isTurnedOn()) {
                return "Kaffeemaschine ist schon aus!";
            } else {
                GPIOController.togglePower();
                return "ok!";
            }
        } else {
            return "Fehler bei der Initialisierung der Pins!";
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/eine_tasse")
    public String makeOneCup() {
        if (GPIOController.checkInitialization()) {
            if (GPIOController.isTurnedOn()) {
                if (GPIOController.isBoiled()) {
                    GPIOController.toggleOneCup();
                    return "ok!";
                } else {
                    return "Kaffeemaschine heizt noch auf!";
                }
            } else {
                return "Kaffeemaschine ist aus!";
            }
        } else {
            return "Fehler bei der Initialisierung der Pins!";
        }
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/zwei_tassen")
    public String makeTwoCups() {
        if (GPIOController.checkInitialization()) {
            if (GPIOController.isTurnedOn()) {
                if (GPIOController.isBoiled()) {
                    GPIOController.toggleTwoCups();
                    return "ok!";
                } else {
                    return "Kaffeemaschine heizt noch auf!";
                }
            } else {
                return "Kaffeemaschine ist aus!";
            }
        } else {
            return "Fehler bei der Initialisierung der Pins!";
        }
    }
}
