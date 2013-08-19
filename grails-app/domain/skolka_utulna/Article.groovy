package skolka_utulna

import frod.routing.domain.Page

class Article {

    Page page

    String headline

    String text

    Date published

    ArticleStatusEnum status = ArticleStatusEnum.DRAFT

    static constraints = {
        published(nullable: true)
        page(nullable: false)
        headline(maxSize: 255)
        text(maxSize: 10000)
        status(nullable: false)
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
