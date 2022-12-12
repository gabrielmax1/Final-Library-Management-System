package coursework.IO

import coursework.database.AUTHOR
import coursework.database.BOOK
import coursework.database.Book_by_Author
import coursework.database.*
import coursework.database.PUBLISHER
import coursework.model.DDBB
import coursework.sorting.Bubble
import coursework.sorting.QuickSort

object Interpreter {

    fun BOOK.toRow(): String =
        "${this.id}\t${this.TITLE}\t${this.AUTHOR}" +
        "\t${this.YEAR_OF_PUBLICATION}\t${this.PUBLISHER}\t${this.SUBJECT}\t${this.AUTHOR_ID}\t${this.PUBLISHER_ID}"

    fun AUTHOR.toRow(): String = "${this.id}\t${this.FIRSTNAME}\t${this.SURNAME}"

    fun PUBLISHER.toRow(): String = "${this.id}\t${this.NAME}"

    fun Book_by_Author.toRow(): String = "${this.TITLE}\t${this.AUTHOR}" +
            "\t${this.YEAR_OF_PUBLICATION}\t${this.PUBLISHER}\t${this.SUBJECT}"

//TODO MIRROR FROM UTILITIES!!
    fun main() {

        Utilities.help()
        var code = 1 // Hack boh Show Books
        do {
            try {
                code = Utilities.prompt_Int()
                when (code) {
                    1 -> DDBB.getBooks().forEach { book ->
                        println(book.toRow())
                    }

                    2 -> DDBB.getAuthors().forEach { author ->
                        println(author.toRow())
                    }

                    3 -> {
                        val firstname = Utilities.prompt_String("\t fullname : ")
                        val surname = Utilities.prompt_String("\t Surname : ")
                        DDBB.addAuthor(firstname, surname)
                    }

                    4 -> {
                        val title = Utilities.prompt_String("\t Title : ")
                        val author = Utilities.prompt_String("\t Author : ")
                        val year_of_publication = Utilities.prompt_String("\t Year of publication : ").toLong()
                        val publisher = Utilities.prompt_String("\t Publisher : ")
                        val subject = Utilities.prompt_String("\t Subject : ")
//                        val author_id= Utilities.prompt_String("\t author_id : ")
//                        val publisher_id = Utilities.prompt_String("\t publisher_id : ")
                        DDBB.addBook(title, author, year_of_publication, publisher, subject)
                    }

                    5 -> {
                        val name = Utilities.prompt_String("\t Name : ")
                        DDBB.addPublisher(name)
                    }

                    6 -> {
                        val scrambled = ArrayList(DDBB.getBooks())
                        val choice = Utilities.prompt_Int("\tBubble(1),MergeSort(2),QuickSort(3) : ")
                        var n = 0
                        when (choice) {
                            1 -> {
                                n = Bubble.sortAuthorName(scrambled)
                                scrambled.forEach { book ->
                                    println(book.toRow())
                                }
                            }
                                //TODO Finish MergeSort.
//                            2 -> {
//                                val pair = MergeSort.sort(scrambled)
//                                n = pair.second
//                                pair.first.forEach { lec ->
//                                    println(lec.toRow())
//                                }
//                            }

                            3 -> {
                                n = QuickSort.sort_author(scrambled)
                                scrambled.forEach { book ->
                                    println(book.toRow())
                                }
                            }
                        }
                        println("--------------------")
                        println("Ticks ${n}")
                    }

                    7 -> {
                        val id = Utilities.prompt_Int("\t Code : ")
                        DDBB.booksByAuthor(id.toLong()).forEach {
                            println(it.toRow())
                        }
                    }

                    8 -> {
                        val scrambled = ArrayList(DDBB.getBooks())

                    }

                    0 -> println("Bye!")
                    10 -> Utilities.help()
                    else -> println("Uncorrect command")
                }
            } catch (e: Exception) {
                println("Uncorrect command")
            }
        } while (code != 0)
    }
}



