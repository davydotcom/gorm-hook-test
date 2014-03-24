import gorm.hook.test.Book

class BootStrap {

    def init = { servletContext ->
    	// def book = new Book(name: "Test", description:"Test")
    	// book.save(flush:true)
    	// println book.description
    }
    def destroy = {
    }
}
