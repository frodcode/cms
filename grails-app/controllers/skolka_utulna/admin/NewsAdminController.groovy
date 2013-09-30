package skolka_utulna.admin

import skolka_utulna.news.NewsService
import skolka_utulna.Website
import skolka_utulna.News
import skolka_utulna.news.NewsCommand

class NewsAdminController {

    def grailsApplication

    NewsService newsService

    static namespace = 'admin'

    static layout = "admin"

    def index() {
        def count = grailsApplication.config.skolka.newsCount;
        def news = newsService.findLast(count)
        Website website = getWebsite()
        [count: count, allNews: news, website: website]
    }

    def detail() {
        Website website = getWebsite()
        def id = params.id
        News news;
        if (id) {
            news = News.get(params.id)
        } else {
            news = new News();
            news.date = new Date()
        }
        [news: news, website: website, errors: flash.errors]
    }

    def getWebsite() {
        return  Website.findBySlug(params.websiteSlug)
    }

    def save(NewsCommand newsCommand) {
        Website website = getWebsite()
        if (newsCommand.hasErrors()) {
            flash.erros = newsCommand.getErrors()
            redirect(action: 'detail', params: [websiteSlug: website.slug])
        }
        if (params.id) {
            newsService.update(params.id, newsCommand)
            flash.message = 'Aktualita byla uložena'
        } else {
            flash.message = 'Aktualita byla vytvořena'
            newsService.create(newsCommand)
        }
        redirect(action: 'index', params: [websiteSlug: website.slug])
    }

    def delete() {
        Website website = getWebsite()
        withForm {
            newsService.delete(params.id)
            flash.message = 'Aktualita byla smazána'
        }.invalidToken {
            flash.errors = ['Špatný token. Zkuste prosím akci opakovat']
        }

        redirect(action: 'index', params: [websiteSlug: website.slug])
    }
}
