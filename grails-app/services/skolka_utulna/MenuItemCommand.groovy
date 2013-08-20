package skolka_utulna

/**
 * User: freeman
 * Date: 20.8.13
 */
@grails.validation.Validateable
class MenuItemCommand {

    String title

    Integer putAfterId

    Integer parentId

    static constraints = {
        importFrom MenuItem

        parentId(nullable:true, validator: { val, obj ->
            if (obj.parentId && !MenuItem.get(obj.parentId)) {
                return ['parentId.notFound']
            }
            return true;
        })
        putAfterId(nullable: true)
    }
}
