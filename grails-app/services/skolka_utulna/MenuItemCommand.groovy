package skolka_utulna

/**
 * User: freeman
 * Date: 20.8.13
 */
@grails.validation.Validateable
class MenuItemCommand {

    String title

    Integer putAfterId

    Integer mainMenuItemId

    Integer pageId

    static constraints = {
        importFrom MenuItem
        pageId(nullable: true)
        putAfterId(nullable: true)
    }
}
