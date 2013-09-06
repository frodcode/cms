package skolka_utulna

class Website {

    String slug

    String name

    static hasMany = [mainMenuItems: MainMenuItem]

    static constraints = {
        slug(unique:true)
    }

}
