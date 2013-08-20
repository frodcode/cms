package skolka_utulna

/**
 * User: freeman
 * Date: 20.8.13
 */
class MenuItemService {

    public MenuItem create(MenuItemCommand menuItemCommand) {
        MenuItem menuItem = new MenuItem()
        MenuItem parentItem = null;
        def position = 1
        def level = 1;

        if (menuItemCommand.parentId) {
            parentItem = MenuItem.get(menuItemCommand.parentId)
            level = parentItem.level + 1
        }

        MenuItem prevSibling = null
        if (menuItemCommand.putAfterId) {
            prevSibling = MenuItem.get(menuItemCommand.putAfterId)
            position = prevSibling.position + 1
            level = prevSibling.level
        }

        if (prevSibling) {
            createGapFor(position, level)
        }
        menuItem.properties = menuItemCommand.properties
        menuItem.position = position
        menuItem.level = level
        menuItem.parent = parentItem
        menuItem.save(flush:true)
        return menuItem
    }


    private def createGapFor(Number position, Number level) {
        def movedItems = MenuItem.findAll("FROM MenuItem WHERE level = :level AND position >= :position ORDER BY position DESC", [level: level, position: position])
        movedItems.each { item ->
            item.position = item.position + 1;
            item.save(flush:true)
        }
    }

}