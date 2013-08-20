package skolka_utulna

import static org.junit.Assert.*
import org.junit.*
import frod.utils.commandObject.CommandObjectService
import frod.routing.service.PageCommand
import frod.routing.domain.UrlTypeEnum
import frod.routing.domain.PageType
import frod.routing.domain.Page
import frod.routing.domain.Domain
import org.springframework.context.annotation.DependsOn
import javax.persistence.EntityManager
import org.springframework.orm.jpa.EntityManagerFactoryUtils
import javax.persistence.EntityManagerFactory

class ArticleServiceIntegrationTests {

    CommandObjectService commandObjectService

    ArticleService articleService

    EntityManagerFactory entityManagerFactory

    EntityManager entityManager

    def sessionFactory

    @Before
    void setUp() {
        //dump(entityManagerFactory)
        //entityManager = EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory)
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    private getTestPageType()
    {
        return PageType.findBySlug('article')
    }

    private getHomepage()
    {
        return Page.findByParentIsNull();
    }

    private getDefaultDomain()
    {
        return Domain.findByHost('localhost')
    }

    private getMenuItemByPositionAndLevel(def position, def level)
    {
        return MenuItem.findByPositionAndLevel(position, level)
    }

    @Test
    void testCreate() {
        MenuItemCommand menuItemCommand = commandObjectService.create(MenuItemCommand, [
                title: 'Test page menu item',
                putAfterId: null,
                parentId: getMenuItemByPositionAndLevel(2, 1).id
        ])

        PageCommand pageCommand = commandObjectService.create(PageCommand, [
                urlPart: '/test-page',
                urlType: UrlTypeEnum.FROM_PARENT,
                pageTypeId: getTestPageType().id,
                parentId: getHomepage().id,
                domainId: defaultDomain.id,
        ])

        ArticleCommand articleCommand = commandObjectService.create(ArticleCommand, [
                headline: 'Test page',
                status: ArticleStatusEnum.PUBLISHED,
                text: 'Lorem ipsum'
        ])

        Article article = articleService.create(articleCommand, menuItemCommand, pageCommand)
        assertNotNull(article)

        assertNotNull(article.page)
        assertSame(getHomepage(), article.page.parent)

        assertNotNull(article.menuItem)
        assertEquals('Test page menu item', article.menuItem.title)

        assertEquals(articleCommand.headline, article.headline)
        assertEquals(articleCommand.status, article.status)
        assertNotNull(article.published)
    }

    def getDataForTestArticles() {
        return [
                [
                        headline: 'Test page 1',
                        status: ArticleStatusEnum.PUBLISHED,
                        text: 'Lorem ipsum 1',
                        page: [
                                urlPart: '/test-page-1',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: getTestPageType().id,
                                parentId: getHomepage().id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Test page menu item 1',
                                parentId: getMenuItemByPositionAndLevel(2, 1).id,
                                putAfterId: null,
                        ]
                ],
                [
                        headline: 'Test page 2',
                        status: ArticleStatusEnum.PUBLISHED,
                        text: 'Lorem ipsum 2',
                        page: [
                                urlPart: '/test-page-2',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: getTestPageType().id,
                                parentId: getHomepage().id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Test page menu item 2',
                                parentId: getMenuItemByPositionAndLevel(2, 1).id,
                                putAfterId: null,
                        ]
                ],
                [
                        headline: 'Test page 3',
                        status: ArticleStatusEnum.PUBLISHED,
                        text: 'Lorem ipsum 3',
                        page: [
                                urlPart: '/test-page-3',
                                urlType: UrlTypeEnum.FROM_PARENT,
                                pageTypeId: getTestPageType().id,
                                parentId: getHomepage().id,
                                domainId: defaultDomain.id,
                        ],
                        menuItem: [
                                title: 'Test page menu item 3',
                                parentId: getMenuItemByPositionAndLevel(2, 1).id,
                                putAfterId: null,
                        ]
                ],
        ]
    }

    def createTestArticles() {
        def allArticles = []
        def allData = getDataForTestArticles()
        allData.each { data ->
            def index = allData.indexOf(data)
            if (index > 0) {
                data.menuItem.putAfterId = allArticles[index-1].menuItem.id
            }
            allArticles << createFromData(data)
        }

        return allArticles
    }

    private Article createFromData(def data) {
        MenuItemCommand menuItemCommand = commandObjectService.create(MenuItemCommand, data.menuItem)
        PageCommand pageCommand = commandObjectService.create(PageCommand, data.page)
        ArticleCommand articleCommand = commandObjectService.create(ArticleCommand, data.findAll{ !['page', 'menuItem'].contains(it.key)})
        return articleService.create(articleCommand, menuItemCommand, pageCommand)
    }

    @Test
    @DependsOn('testCreate')
    void testCreateInLine() {
        def allArticles = createTestArticles()
        def allData = getDataForTestArticles()
        allArticles.each { testingArticle ->
            def index = allArticles.indexOf(testingArticle)
            def data = allData[index]
            doTestArticleAgainstData(testingArticle, data, index+1, 2)
        }
    }

    void doTestArticleAgainstData(Article article, def data, Number position, Number expectedLevel) {
        assertEquals(data.headline, article.headline)
        assertEquals(data.text, article.text)
        assertEquals(data.status, article.status)
        if (data.status == ArticleStatusEnum.PUBLISHED && !data.published) {
            assertNotNull(article.published)
        }

        assertNotNull(article.page)
        Page page = article.page
        def pageData = data.page

        assertEquals(pageData.urlPart, page.urlPart)
        if (pageData.parentId) {
            assertEquals(Page.get(pageData.parentId), article.page.parent)
        } else {
            assertNull(article.page.parent)
        }
        assertNotNull(article.page.domain)

        assertNotNull(article.menuItem)
        def menuItemData = data.menuItem;
        MenuItem menuItem = article.menuItem
        assertEquals(menuItemData.title, menuItem.title)
        assertEquals(MenuItem.get(menuItemData.parentId)?.id, menuItem.parentId)
        assertEquals(position, menuItem.position)
        assertEquals(expectedLevel, menuItem.level)
    }

    @Test
    @DependsOn('testCreateInLine')
    void testCreateBetween() {
        def allArticles = createTestArticles()
        def allData = getDataForTestArticles()

        def betweenData = [
                headline: 'Test page 2 - between',
                status: ArticleStatusEnum.PUBLISHED,
                text: 'Lorem ipsum 2 - between',
                page: [
                        urlPart: '/test-page-2-between',
                        urlType: UrlTypeEnum.FROM_PARENT,
                        pageTypeId: getTestPageType().id,
                        parentId: getHomepage().id,
                        domainId: defaultDomain.id,
                ],
                menuItem: [
                        title: 'Test page menu item 2 - between',
                        parentId: getMenuItemByPositionAndLevel(2, 1).id,
                        putAfterId: allArticles[0].menuItem.id,
                ]
        ]

        Article betweenArticle = createFromData(betweenData)
        doTestArticleAgainstData(betweenArticle, betweenData, 2, 2)

        doTestArticleAgainstData(allArticles[0], allData[0], 1, 2)
        doTestArticleAgainstData(allArticles[1], allData[1], 3, 2)
        doTestArticleAgainstData(allArticles[2], allData[2], 4, 2)
    }

}
