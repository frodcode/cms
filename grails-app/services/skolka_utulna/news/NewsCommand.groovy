package skolka_utulna.news

import skolka_utulna.News
import java.text.SimpleDateFormat
import skolka_utulna.Website

/**
 * User: freeman
 * Date: 30.9.13
 */
@grails.validation.Validateable
class NewsCommand {

    String name

    String dateString

    String websiteSlug

    String content

    static constraints = {
        websiteSlug(nullable: false)
        dateString(nullable: true)
        importFrom News
    }

    public Date getDateFromDateString() {
        if (!dateString) {
            return new Date()
        }
        return new SimpleDateFormat("d.M.yyyy").parse(dateString);
    }

    public Website getWebsiteFromSlug() {
        return Website.findBySlug(websiteSlug)
    }

}
