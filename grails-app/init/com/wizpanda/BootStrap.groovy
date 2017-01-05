package com.wizpanda

import com.wizpanda.city.City
import com.wizpanda.state.State

class BootStrap {

    def init = { servletContext ->
        State stateData = State.findOrCreateByName("CG")
        if (!stateData.id) {
            stateData.save(flush: true, failOnError: true)
        }

        City cityData = City.findOrCreateByName("Bhilai")
        if (!cityData.id) {
            cityData.state = stateData
            cityData.save(flush: true, failOnError: true)
        }
    }
    def destroy = {
    }
}
