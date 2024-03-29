package skolka_utulna

import frod.routing.domain.Page

class Article {

    Page page

    String headline

    String text

    Date published

    MenuItem menuItem

    ArticleStatusEnum status = ArticleStatusEnum.DRAFT

    static constraints = {
        published(nullable: true)
        page(nullable: false)
        headline(maxSize: 255, nullable: false)
        text(maxSize: 10000, nullable: false)
        status(nullable: false)
        menuItem(nullable:true)
    }

    public def publish(Date when)
    {
        published = when
        status = ArticleStatusEnum.PUBLISHED
    }

    public def publish()
    {
        publish(new Date())
    }
}
