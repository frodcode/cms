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
        items.add(new MenuItem(controller: 'mealAdmin', name: 'Jídelníček', ico: 'icon-leaf', websiteSlug: websiteSlug))
        items.add(new MenuItem(controller: 'newsAdmin', name: 'Novinky', ico: 'icon-star', websiteSlug: websiteSlug))
        return items
    }
}
