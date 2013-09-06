package skolka_utulna

import frod.routing.domain.Page


class MenuItem {

    MainMenuItem mainMenuItem

    Page page

    String title

    Integer position

    static constraints = {
        position(unique: ['mainMenuItem'])
        page(nullable: true)
        title(nullable: false)
        mainMenuItem(nullable:false)
    }

    static mapping = {
        sort position: "asc"
    }

}
