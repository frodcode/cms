package skolka_utulna

import frod.routing.domain.Page


class MenuItem {

    Website website

    MenuItem parent

    Page page

    String title

    Integer position

    Integer level

    static hasMany = [subItems: MenuItem]

    static mappedBy = [subItems: 'parent']

    static constraints = {
        level(unique: ['position', 'parent', 'website'])
        page(nullable: true)
        title(nullable: false)
        parent(nullable:true)
        website(nullable: false)
    }

    public MenuItem getRoot(){
        if (parent) {
            return parent.getRoot()
        }
        return this
    }

    def beforeValidate() {
        if (!website) {
            website = getRoot().website
        }
    }

}
