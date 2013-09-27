package skolka_utulna.admin.menu

/**
 * User: freeman
 * Date: 26.9.13
 */
class MenuItem {

    String controller

    String action = 'index'

    String name

    String websiteSlug

    def params = [:]

    String ico

    public boolean isCurrent(def templateParams)
    {
        getAllParams().each {
            if (!templateParams[it.key] || templateParams[it.key] == it.value) {
                return false;
            }
        }
        if (templateParams.controller == controller && templateParams.action == action) {
            return true;
        }
        return false;
    }

    public def getAllParams()
    {
        return [websiteSlug: websiteSlug] + this.params
    }
}
