package gorm.hook.test



import spock.lang.*

/**
 *
 */
class BookIntegrationSpec extends Specification {
	static transactional = false
	def sessionFactory

    def setup() {
    }

    def cleanup() {
    }

    void "test if session is transactional"() {
		assert !sessionFactory.currentSession.isTransactionInProgress()

    }

    void "test if gorm hook is called"() {
    	given:
	    	def book = new Book(name: "Test", description: "Test")
    	when:
	    	book.save(flush:true)
    	then:
	    	assert book.description == 'beforeInsert'
    }

    void "test if gorm hook is called in session Context"() {
    	given:
	    	def book
	    	Book.withNewSession() { session ->
		    	book = new Book(name: "Test2", description: "Test2")
		    	book.save(flush:true)
		    }
		expect:
	    	assert book.description == 'beforeInsert'
	    
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
			assert book.description  == 'Nice!'
			assert book.afterDescription == 'Nice!'

    }
}
