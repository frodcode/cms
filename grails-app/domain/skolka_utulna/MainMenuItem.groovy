package skolka_utulna

/**
 * User: freeman
 * Date: 6.9.13
 */
class MainMenuItem {

    Website website

    String title

    Integer position

    static hasMany = [menuItems: MenuItem]

    static constraints = {
        position(unique: true)
        title(nullable: false)
        website(nullable: false)
    }


    public MenuItem getFirstItem() {
        if (!menuItems) {
            return null;
        }
        return menuItems.first()
    }

}