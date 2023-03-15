package nl.avisi.structurizr.site.generatr.site.model

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.structurizr.documentation.Format
import com.structurizr.documentation.Section
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SectionTitleTest {

    @Test
    fun `no content`() {
        val section = Section(Format.Markdown, "")
        assertThat(section.title()).isEqualTo("no title")
    }

    @Test
    fun `only whitespaces`() {
        val section = Section(Format.Markdown, " \n ")
        assertThat(section.title()).isEqualTo("no title")
    }

    @Test
    fun `short paragraph`() {
        val section = Section(Format.Markdown, "some content")
        assertThat(section.title()).isEqualTo("some content")
    }

    @Test
    fun `long paragraph`() {
        val section = Section(
            Format.Markdown,
            "some very very long content we really need to truncate since no one wants to read such an exhausting title"
        )
        assertThat(section.title()).isEqualTo("some very very long content we really need to")
    }

    @Test
    fun `long paragraph without whitespaces`() {
        val section = Section(
            Format.Markdown,
            "some-very-very-long-content-we-really-need-to-truncate-since-no-one-wants-to-read-such-an-exhausting-title"
        )
        assertThat(section.title()).isEqualTo("some-very-very-long-content-we-really-need-to-trun")
    }

    @ParameterizedTest
    @ValueSource(strings = ["# header", "## header", "### header"])
    fun `with heading`(content: String) {
        val section = Section(Format.Markdown, content)
        assertThat(section.title()).isEqualTo("header")
    }
}
