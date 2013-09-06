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
import skolka_utulna.Website
import skolka_utulna.MainMenuItem

/**
 * User: freeman
 * Date: 19.8.13
 */
class Fixtures {
    public static def load(def ctx, def defaultHost) {
        PageService pageService = ctx.pageService
        RoutingService routingService = ctx.routingService

        def websites = [
                utulna: new Website(
                        slug: 'utulna',
                        name: 'Školka útulna',
                )
        ]
        websites*.value*.save(flush: true, failOnError: true);

        def pageTypes = [
                rootPageType: new PageType(
                        slug: 'root',
                        description: 'Rozcestník',
                        singleton: true,
                        controller: 'Root',
                        action: 'index'),
                homepagePageType: new PageType(
                        slug: 'homepage',
                        description: 'Úvodní stránka',
                        singleton: true,
                        controller: 'Homepage',
                        action: 'index'),
                articlePageType: new PageType(
                        slug: 'article',
                        description: 'Článek',
                        singleton: false,
                        controller: 'Article',
                        action: 'detail'),
        ]

        pageTypes*.value*.save(failOnError: true);

        def mainMenuItems = [
                uvod:new MainMenuItem(
                        title: 'Úvod',
                        position: 1,
                        website: websites.utulna
                ),
                nase_tridy:new MainMenuItem(
                        title: 'Naše třídy',
                        position: 2,
                        website: websites.utulna
                ),
                o_skolce:new MainMenuItem(
                        title: 'O školce',
                        position: 3,
                        website: websites.utulna
                ),
                akce:new MainMenuItem(
                        title: 'Akce',
                        position: 4,
                        website: websites.utulna
                ),
                fotogalerie:new MainMenuItem(
                        title: 'Fotogalerie',
                        position: 5,
                        website: websites.utulna
                ),
                fotogalerie:new MainMenuItem(
                        title: 'Jídelníček',
                        position: 6,
                        website: websites.utulna
                ),
                kontakt:new MainMenuItem(
                        title: 'Kontakt',
                        position: 7,
                        website: websites.utulna
                ),

        ]

        mainMenuItems*.value*.save(flush: true, failOnError: true);

        def pages = [
                root: new Page(
                        domain: defaultHost,
                        urlPart: '/',
                        urlType: UrlTypeEnum.ROOT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.rootPageType,
                ),
        ]

//        pages.utulnaHomepage = new Page(
//                domain: defaultHost,
//                urlPart: '/utulna',
//                urlType: UrlTypeEnum.FROM_PARENT,
//                requestType: RequestTypeEnum.REGULAR,
//                httpMethod: HttpMethodEnum.GET,
//                pageType: pageTypes.homepagePageType,
//                parent: pages.homepage
//        )

        pages*.value*.each {
            pageService.setDefaults(it)
            routingService.regenerateUrl(it)
        }
        pages*.value*.save(flush: true, failOnError: true);

        return [root: pages.root, pageTypes: pageTypes, websites: websites, mainMenuItems: mainMenuItems]
    }
}
