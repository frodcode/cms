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
import skolka_utulna.meal.MealMenuType
import skolka_utulna.meal.MealMenu
import frod.media.domain.MediaGroupType
import skolka_utulna.acl.Role
import skolka_utulna.acl.User
import skolka_utulna.acl.UserRole

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
                        name: 'Školka Útulná',
                ),
                troilova: new Website(
                        slug: 'troilova',
                        name: 'Školka Troilova',
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
                        singleton: false,
                        controller: 'Homepage',
                        action: 'index'),
                articlePageType: new PageType(
                        slug: 'article',
                        description: 'Článek',
                        singleton: false,
                        controller: 'Article',
                        action: 'index'),
                mealMenuPageType: new PageType(
                        slug: 'meal',
                        description: 'Jídelníček',
                        singleton: false,
                        controller: 'Meal',
                        action: 'index'),
                galleryPageType: new PageType(
                        slug: 'gallery',
                        description: 'Fotogalerie',
                        singleton: false,
                        controller: 'Gallery',
                        action: 'index'),
                newsPageType: new PageType(
                        slug: 'news',
                        description: 'Novinky - archiv',
                        singleton: false,
                        controller: 'News',
                        action: 'index'),
        ]

        pageTypes*.value*.save(failOnError: true);

        def mainMenuItems = [:]
        mainMenuItems.utulna = loadUtulnaMenuItems(websites)
        mainMenuItems.troilova = loadTroilovaMenuItems(websites)


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

        pages*.value*.each {
            pageService.setDefaults(it)
            routingService.regenerateUrl(it)
        }
        pages*.value*.save(flush: true, failOnError: true);
        def mealMenuTypes = loadMealTypes()
        def galleryMediaGroupType = loadMedia()
        loadAcl()

        return [root: pages.root, pageTypes: pageTypes, websites: websites, mainMenuItems: mainMenuItems, mealMenuTypes: mealMenuTypes, galleryMediaGroupType: galleryMediaGroupType]
    }

    public static def loadUtulnaMenuItems(websites) {
        def mainMenuItems = [
                uvod:new MainMenuItem(
                        title: 'Úvod',
                        position: 1,
                        slug: 'homepage',
                        website: websites.utulna
                ),
                nase_tridy:new MainMenuItem(
                        title: 'Naše třídy',
                        slug: 'nase_tridy',
                        position: 2,
                        website: websites.utulna
                ),
                o_skolce:new MainMenuItem(
                        title: 'O školce',
                        slug: 'o_skolce',
                        position: 3,
                        website: websites.utulna
                ),
                akce:new MainMenuItem(
                        title: 'Akce',
                        slug: 'akce',
                        position: 4,
                        website: websites.utulna
                ),
                fotogalerie:new MainMenuItem(
                        title: 'Fotogalerie',
                        position: 5,
                        slug: 'fotogalerie',
                        website: websites.utulna
                ),
                jidelnicek:new MainMenuItem(
                        title: 'Jídelníček',
                        slug: 'jidelnicek',
                        position: 6,
                        website: websites.utulna
                ),
                kontakt:new MainMenuItem(
                        title: 'Kontakt',
                        slug: 'kontakt',
                        position: 7,
                        website: websites.utulna
                ),

        ]

        mainMenuItems*.value*.save(flush: true, failOnError: true);
        return mainMenuItems
    }


    public static def loadTroilovaMenuItems(websites) {
        def mainMenuItems = [
                uvod:new MainMenuItem(
                        title: 'Úvod',
                        position: 1,
                        slug: 'homepage',
                        website: websites.troilova
                ),
                nase_tridy:new MainMenuItem(
                        title: 'Naše třídy',
                        slug: 'nase_tridy',
                        position: 2,
                        website: websites.troilova
                ),
                o_skolce:new MainMenuItem(
                        title: 'O školce',
                        slug: 'o_skolce',
                        position: 3,
                        website: websites.troilova
                ),
                akce:new MainMenuItem(
                        title: 'Akce',
                        slug: 'akce',
                        position: 4,
                        website: websites.troilova
                ),
                fotogalerie:new MainMenuItem(
                        title: 'Fotogalerie',
                        position: 5,
                        slug: 'fotogalerie',
                        website: websites.troilova
                ),
                jidelnicek:new MainMenuItem(
                        title: 'Jídelníček',
                        slug: 'jidelnicek',
                        position: 6,
                        website: websites.troilova
                ),
                kontakt:new MainMenuItem(
                        title: 'Kontakt',
                        slug: 'kontakt',
                        position: 7,
                        website: websites.troilova
                ),

        ]

        mainMenuItems*.value*.save(flush: true, failOnError: true);
        return mainMenuItems
    }

    public static def loadMealTypes() {
        def mealMenuTypes = [
             presnidavka: new MealMenuType(
                     name: 'Přesnídávka',
                     slug: 'presnidavka',
                     position: 1
             ),
                obed: new MealMenuType(
                        name: 'Oběd',
                        slug: 'obed',
                        position: 2
                ),
                svacina: new MealMenuType(
                        name: 'Svačina',
                        slug: 'svacina',
                        position: 3
                ),
        ]
        mealMenuTypes*.value*.save(flush: true, failOnError: true);
        return mealMenuTypes
    }

    public static def loadMedia() {
        MediaGroupType galleryMediaGroupType = new MediaGroupType(name:'Galerie')
        galleryMediaGroupType.save(flush:true)
        return galleryMediaGroupType
    }

    public static def loadAcl() {
        def utulnaAdmin = new Role(authority: 'ROLE_ADMIN_UTULNA').save(flush: true)
        def troilovaAdmin = new Role(authority: 'ROLE_ADMIN_TROILOVA').save(flush: true)
        def superadmin = new Role(authority: 'ROLE_SUPERADMIN').save(flush: true)

        def admin = new User(username: 'admin', enabled: true, password: 'admin')
        admin.save(flush: true)

        UserRole.create admin, utulnaAdmin, true
        UserRole.create admin, troilovaAdmin, true
        UserRole.create admin, superadmin, true

        assert User.count() == 1
        assert Role.count() == 3
        assert UserRole.count() == 3
    }

}
