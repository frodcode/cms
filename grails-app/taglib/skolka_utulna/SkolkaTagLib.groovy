package skolka_utulna

import skolka_utulna.data.AdminMenuFactory

class SkolkaTagLib {

    static namespace = "skolka"

    AdminMenuFactory adminMenuFactory

    MenuItemService menuItemService

    WebsiteService websiteService

    def mainMenu = { attrs, body ->
        def page = pageScope.page
        def menuItems = MainMenuItem.list(sort: 'position', order: 'ASC')
        def website = Website.findByHomepage(page)
        def activeItem
        def homepage
        def submenuItem = menuItemService.findItemByPage(page)
        def isHomepage = false;
        if (website) {
            activeItem = MainMenuItem.findByPositionAndWebsite(1, website)
            isHomepage = true;
            homepage = website.homepage
        } else if (submenuItem) {
            activeItem = submenuItem.mainMenuItem
            homepage = activeItem.website.homepage
        } else {
            activeItem = MainMenuItem.findByPage(page)
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

    def adminMenu = {attrs, body ->
        def items = adminMenuFactory.create(params.websiteSlug)
        out << render(template:"/shared/admin/menu", model:[items: items])
    }

}
