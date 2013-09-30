package skolka_utulna

import frod.routing.domain.Page
import frod.media.domain.MediaGroup

class Website {

    String slug

    String name

    Page homepage

    static hasMany = [mainMenuItems: MainMenuItem, mediaGroups: MediaGroup]

    static constraints = {
        slug(unique:true)
        homepage(nullable: true)
    }

}
