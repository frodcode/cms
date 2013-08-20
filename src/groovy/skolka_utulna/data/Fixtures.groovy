package skolka_utulna.data

import frod.routing.service.PageService
import frod.routing.service.PageCommand
import frod.routing.domain.Page
import frod.routing.domain.PageType
import frod.routing.domain.UrlTypeEnum
import frod.routing.domain.RequestTypeEnum
import frod.routing.domain.HttpMethodEnum
import skolka_utulna.ArticleService
import skolka_utulna.ArticleCommand
import frod.routing.service.RoutingService
import skolka_utulna.MenuItem

/**
 * User: freeman
 * Date: 19.8.13
 */
class Fixtures {
    public static def load(def ctx, def defaultHost) {
        PageService pageService = ctx.pageService
        RoutingService routingService = ctx.routingService
        def pageTypes = [
                homepagePageType: new PageType(
                        slug: 'homepage',
                        description: 'Domovská stránka',
                        singleton: true,
                        controller: 'Homepage',
                        action: 'index'),
                menuArticlePageType: new PageType(
                        slug: 'main_article',
                        description: 'Položka hlavního menu',
                        singleton: false,
                        controller: 'Article',
                        action: 'main'),
                articlePageType: new PageType(
                        slug: 'article',
                        description: 'Článek',
                        singleton: false,
                        controller: 'Article',
                        action: 'detail'),
        ]

        pageTypes*.value*.save(failOnError: true);

        def menuItems = [
                uvod: new MenuItem(
                        title: 'Úvod',
                        position: 1,
                        level: 1
                ),
                nase_tridy: new MenuItem(
                        title: 'Naše třídy',
                        position: 2,
                        level: 1
                ),
                o_skolce: new MenuItem(
                        title: 'O školce',
                        position: 3,
                        level: 1
                ),
                akce: new MenuItem(
                        title: 'Akce',
                        position: 4,
                        level: 1
                ),
                fotogalerie: new MenuItem(
                        title: 'Fotogalerie',
                        position: 5,
                        level: 1
                ),
                fotogalerie: new MenuItem(
                        title: 'Jídelníček',
                        position: 6,
                        level: 1
                ),
                kontakt: new MenuItem(
                        title: 'Kontakt',
                        position: 7,
                        level: 1
                ),

        ]

        menuItems*.value*.save(flush: true, failOnError: true);

        def pages = [
                homepage: new Page(
                        domain: defaultHost,
                        urlPart: '/',
                        urlType: UrlTypeEnum.ROOT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.homepagePageType,
                        menuItem: menuItems.uvod
                ),
        ]

        pages*.value*.each {
            pageService.setDefaults(it)
            routingService.regenerateUrl(it)
        }
        pages*.value*.save(flush: true, failOnError: true);

        return [homepage: pages.homepage, pageTypes: pageTypes]
    }
}
