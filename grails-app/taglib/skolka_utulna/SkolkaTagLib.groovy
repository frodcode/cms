package skolka_utulna

class SkolkaTagLib {

    static namespace = "skolka"

    MenuItemService menuItemService

    def mainMenu = { attrs, body ->
        def page = pageScope.page
        def menuItems = MainMenuItem.list(sort: 'position', order: 'ASC')
        def activeItem = menuItemService.findItemByPage(page).mainMenuItem
        out << render(template:"/shared/menu/mainMenu", model:[menuItems: menuItems, activeItem: activeItem])
    }

    def submenu = { attrs, body ->
        def page = pageScope.page
        def menuItems = menuItemService.findMenuItemsForPage(page)
        def activeItem = menuItemService.findItemByPage(page)
        out << render(template:"/shared/menu/submenu", model:[menuItems: menuItems, activeItem: activeItem])
    }

}
