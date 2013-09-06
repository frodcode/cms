package skolka_utulna

import frod.routing.domain.Page

class Website {

    String slug

    String name

    Page homepage

    static hasMany = [mainMenuItems: MainMenuItem]

    static constraints = {
        slug(unique:true)
        homepage(nullable: true)
    }

}
