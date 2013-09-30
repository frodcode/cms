package skolka_utulna

import frod.routing.domain.Page

/**
 * User: freeman
 * Date: 20.8.13
 */
class MenuItemService {

    public MenuItem create(MenuItemCommand menuItemCommand) {
        MenuItem menuItem = new MenuItem()
        def position = 1
        menuItem.mainMenuItem = MainMenuItem.get(menuItemCommand.mainMenuItemId)
        MenuItem prevSibling = null
        if (menuItemCommand.putAfterId) {
            prevSibling = MenuItem.get(menuItemCommand.putAfterId)
            position = prevSibling.position + 1
        }

        if (prevSibling) {
            createGapFor(position, menuItem.mainMenuItem)
        }
        if (menuItemCommand.pageId) {
            def page = Page.get(menuItemCommand.pageId)
            menuItem.page = page;
        }
        menuItem.properties = menuItemCommand.properties
        menuItem.position = position
        menuItem.save(flush:true, failOnError: true)
        return menuItem
    }


    private def createGapFor(Number position, MainMenuItem mainMenuItem) {
        def movedItems = MenuItem.findAll("FROM MenuItem WHERE position >= :position AND mainMenuItem = :mainMenuItem ORDER BY position DESC", [position: position, mainMenuItem: mainMenuItem])
        movedItems.each { item ->
            item.position = item.position + 1;
            item.save(flush:true)
        }
    }

    public def findMenuItemsForPage(Page page) {
        MenuItem currentMenuItem = MenuItem.findByPage(page)
        return currentMenuItem.mainMenuItem.menuItems
    }

    MenuItem findItemByPage(Page page) {
        return MenuItem.findByPage(page)
    }
}
