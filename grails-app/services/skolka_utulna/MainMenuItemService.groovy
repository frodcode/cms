package skolka_utulna

class MainMenuItemService {

    def findPageForMainMenuItemWithSlug(String slug) {
        return MainMenuItem.findBySlug(slug).firstItem?.page
    }
}
