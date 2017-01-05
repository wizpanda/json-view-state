package com.wizpanda.city

import grails.transaction.Transactional

@Transactional(readOnly = true)
class CityController {
    static responseFormats = ['json']

    def index(Integer max) {
        params.max = Math.min(params.max ?: 10, 100)
        params.offset = params.offset ?: 0
        params.sort = params.sort ?: "name"
        params.order = params.order ?: "asc"
        Map filters = params.filters ? JSON.parse(params.filters) : [:]

        List cityInstanceList = City.createCriteria().list(params) {

            createAlias("state", "_state")
            if (filters.query) {
                or {
                    ["name", "_state.name"].each { field ->
                        ilike(field, "%${filters.query}%")
                    }
                }
            }
        }

        println(cityInstanceList)
        respond(cityInstanceList)
    }
}
