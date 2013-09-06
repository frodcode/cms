package skolka_utulna

class SkolkaTagLib {

    static namespace = "skolka"

    MenuItemService menuItemService

    WebsiteService websiteService

    def mainMenu = { attrs, body ->
        def page = pageScope.page
        def menuItems = MainMenuItem.list(sort: 'position', order: 'ASC')
        def website = Website.findByHomepage(page)
        def activeItem
        def homepage
        def isHomepage = false;
        if (website) {
            activeItem = MainMenuItem.findByPositionAndWebsite(1, website)
            isHomepage = true;
            homepage = website.homepage
        } else {
            activeItem = menuItemService.findItemByPage(page).mainMenuItem
            homepage = activeItem.website.homepage
        }
        out << render(template:"/shared/menu/mainMenu", model:[menuItems: menuItems, activeItem: activeItem, homepage: homepage, isHomepage: isHomepage])
    }

    def submenu = { attrs, body ->
        def page = pageScope.page
        def menuItems = menuItemService.findMenuItemsForPage(page)
        def activeItem = menuItemService.findItemByPage(page)
        out << render(template:"/shared/menu/submenu", model:[menuItems: menuItems, activeItem: activeItem])
    }

    def head = {
        def page = pageScope.page
        def website = websiteService.getWebsite(page)
        out << render(template:"/$website.slug/head", model: [homepage: website.homepage])
    }

}
