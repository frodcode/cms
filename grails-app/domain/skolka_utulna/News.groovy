package skolka_utulna

class News {

    String name

    Date date

    String content

    Website website

    static constraints = {
        content(maxSize: 10000)
    }
}
