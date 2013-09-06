package skolka_utulna.meal

class MealMenuType {

    String name

    String slug

    Integer position

    static constraints = {
        name (maxSize: 255)
        slug (maxSize: 255, unique: true)
    }

    static mapping = {
        sort "position", order: 'asc'
    }

}
