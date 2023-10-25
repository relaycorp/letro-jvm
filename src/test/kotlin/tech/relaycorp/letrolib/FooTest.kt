import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FooTest {
    @Test
    fun `foo`() {
        assertEquals(Foo().bar(), "Hello, world!")
    }
}
