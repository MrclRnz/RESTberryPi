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
    @Path("/power")
    public String turnOnPower() {
        if (GPIOController.checkInitialization()) {
            return GPIOController.togglePower();
        }
        return "Fehler bei der Initialisierung der Pins!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/eine_tasse")
    public String makeOneCup() {
        if (GPIOController.checkInitialization()) {
            GPIOController.toggleOneCup();
            return "ok!";
        }
        return "Fehler bei der Initialisierung der Pins!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/zwei_tassen")
    public String makeTwoCups() {
        if (GPIOController.checkInitialization()) {
            GPIOController.toggleTwoCups();
            return "ok!";
        }
        return "Fehler bei der Initialisierung der Pins!";
    }
}
