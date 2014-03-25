package gorm.hook.test

import org.junit.Test

class BookJUnitIntegrationTest extends GroovyTestCase {
	static transactional = false
	def sessionFactory
	def cacheUpdateService

	void testDoSomething() {
		def book = new Book(name: 'TestPersist', description: "Test")
    		book.save(flush:true)
    		book = book.get(book.id)
    	
    		book.description = "Nice!"
    		book.save(flush:true)
		
			sleep(2000)
			assert book.description  == 'Nice!'
			assert cacheUpdateService.lastValue == 'Nice!'
			// assert book.afterDescription == 'Nice!'
	}
}