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

        def pages = [
                homepage: new Page(
                        domain: defaultHost,
                        urlPart: '/',
                        urlType: UrlTypeEnum.ROOT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.homepagePageType
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
