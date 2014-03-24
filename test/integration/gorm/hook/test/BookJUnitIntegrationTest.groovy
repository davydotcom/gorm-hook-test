package gorm.hook.test

import org.junit.Test

class BookJUnitIntegrationTest extends GroovyTestCase {
	static transactional = false
	def sessionFactory

	void testDoSomething() {
				assert !sessionFactory.currentSession.isTransactionInProgress()

	}
}