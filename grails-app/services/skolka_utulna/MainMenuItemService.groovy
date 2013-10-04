package skolka_utulna

class MainMenuItemService {

    def findPageForMainMenuItemWithSlug(String slug) {
        return MainMenuItem.findBySlug(slug).firstItem?.page
    }

    def findBySlugAndWebsite(String slug, Website website)
    {
        return MainMenuItem.findBySlugAndWebsite(slug, website)
    }

    def findAllArticleBasedByWebsite(Website website) {
        return MainMenuItem.findAll('FROM MainMenuItem mmi JOIN FETCH mmi.page mmip JOIN FETCH mmip.pageType mpt WHERE mpt.slug = :slug AND mmi.website = :website', [slug: 'article', website: website])
    }
}
