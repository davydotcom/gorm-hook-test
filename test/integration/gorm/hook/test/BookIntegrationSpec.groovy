package gorm.hook.test



import spock.lang.*

/**
 *
 */
class BookIntegrationSpec extends Specification {
	static transactional = false
	def sessionFactory
	def cacheUpdateService

    def setup() {
    }

    def cleanup() {
    }


    void "test if gorm hook is called"() {
    	given:
	    	def book = new Book(name: "Test", description: "Test")
    	when:
	    	book.save(flush:true)
    	then:
	    	book.description == 'beforeInsert'
    }

    void "test if gorm hook is called in session Context"() {
    	given:
	    	def book
	    	Book.withNewSession() { session ->
		    	book = new Book(name: "Test2", description: "Test2")
		    	book.save(flush:true)
		    }
		expect:
	    	book.description == 'beforeInsert'
	    
    }

    void "test if afterUpdate is called after persist"() {
    	given:
    		def book = new Book(name: 'TestPersist', description: "Test")
    		book.save(flush:true)
    		book = book.get(book.id)
    	when:
    		book.description = "Nice!"
    		book.save(flush:true)
		then:
			sleep(2000)
			book.description  == 'Nice!'
			cacheUpdateService.lastValue == 'Nice!'
    }
}
