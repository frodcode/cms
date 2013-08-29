package skolka_utulna

class Website {

    String slug

    String name

    static hasMany = [menuItems: MenuItem]

    static constraints = {
        slug(unique:true)
    }

}
