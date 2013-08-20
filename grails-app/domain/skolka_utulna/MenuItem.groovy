package skolka_utulna

import frod.routing.domain.Page


class MenuItem {

    MenuItem parent

    Page page

    String title

    Integer position

    Integer level

    static hasMany = [subItems: MenuItem]

    static constraints = {
        level(unique: ['position', 'parent'])
        page(nullable: true)
        title(nullable: false)
        parent(nullable:true)
    }
}
