package skolka_utulna.news

import skolka_utulna.News

class NewsService {

    def findLast(limit, website) {
        return News.findAllByWebsite(website, [sort: 'date', order: 'desc', max: limit])
    }

    def create(NewsCommand newsCommand) {
        News news = new News()
        populate(news, newsCommand)
        news.save(flush: true, failOnError: true)
    }

    private def populate(News news, NewsCommand newsCommand) {
        news.date = newsCommand.getDateFromDateString()
        news.name = newsCommand.name
        news.content = newsCommand.content
        news.website = newsCommand.getWebsiteFromSlug()
    }

    def update(def id, NewsCommand newsCommand) {
        News news = News.get(id)
        populate(news, newsCommand)
        news.save(flush: true, failOnError: true)
    }

    def delete(def id) {
        News news = News.get(id)
        news.delete(flush: true)
    }

}
