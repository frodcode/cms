package skolka_utulna

import frod.routing.domain.Page

/**
 * User: freeman
 * Date: 6.9.13
 */
class MainMenuItem {

    Website website

    String title

    Integer position

    String slug

    /**
     * For meal menu and contact
     */
    Page page

    static hasMany = [menuItems: MenuItem]

    static mapping = {
        menuItems sort: 'position', order: 'asc'
    }

    static constraints = {
        position(unique: ['website'])
        title(nullable: false)
        website(nullable: false)
        page(nullable: true)
    }


    public MenuItem getFirstItem() {
        if (!menuItems) {
            return null;
        }
        return menuItems.first()
    }

}
