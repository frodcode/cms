package skolka_utulna.data

import skolka_utulna.admin.menu.MenuItem
import skolka_utulna.Website

/**
 * User: freeman
 * Date: 26.9.13
 */
class AdminMenuFactory {

    public List<MenuItem> create(String websiteSlug) {
        def items = []
        items.add(new MenuItem(controller: 'meal-admin', name: 'Jídelníček', ico: 'icon-leaf', websiteSlug: websiteSlug))
        items.add(new MenuItem(controller: 'news-admin', name: 'Aktuality', ico: 'icon-star', websiteSlug: websiteSlug))
        items.add(new MenuItem(controller: 'article-admin', name: 'Články', ico: 'icon-pencil', websiteSlug: websiteSlug))
        items.add(new MenuItem(controller: 'media-admin', name: 'Galerie', ico: 'icon-camera', websiteSlug: websiteSlug))
        return items
    }
}
