package skolka_utulna

import skolka_utulna.data.AdminMenuFactory

class SkolkaTagLib {

    static namespace = "skolka"

    AdminMenuFactory adminMenuFactory

    MenuItemService menuItemService

    WebsiteService websiteService

    def mainMenu = { attrs, body ->
        def page = pageScope.page
        def website = websiteService.getWebsite(page)
        def menuItems = MainMenuItem.findAllByWebsite(website)

        def activeItem
        def homepage
        def submenuItem = menuItemService.findItemByPage(page)
        if (submenuItem) {
            activeItem = submenuItem.mainMenuItem
            homepage = activeItem.website.homepage
        } else {
            activeItem = MainMenuItem.findByPage(page)
            homepage = activeItem.website.homepage
        }
        out << render(template:"/shared/menu/mainMenu", model:[menuItems: menuItems, activeItem: activeItem, homepage: homepage])
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

    def messages = {attrs, body ->
        out << render(template:"/shared/admin/messages", model:[flash: flash])
    }

}
