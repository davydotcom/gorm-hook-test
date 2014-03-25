package gorm.hook.test

class Book {
	static transients = ['afterDescription']
	String name
	String description
	
	String afterDescription

	def beforeInsert() {
		log.error("WOAH WE INSERTED")
		println "WOAH WE INSERTED"
		description = 'beforeInsert'		
	}

	def beforeUpdate() {
		// description = 'beforeInsert'		
	}

	def afterUpdate() {
		event('bookCacheUpdate', [bookId:id], [namespace:'gorm-test'])

		// def id = this.id
		// Book.withNewSession { session ->
		// 	def book = Book.read(id)
		// 	afterDescription = book.description
		// 	log.error("WOAH WE INSERTED afterInsert ${book.description}")	
		// }
		

	}

    static constraints = {
    }
}
