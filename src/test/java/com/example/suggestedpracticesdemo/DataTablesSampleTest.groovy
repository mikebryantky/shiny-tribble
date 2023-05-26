package com.example.suggestedpracticesdemo

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest(
        classes = SuggestedPracticesDemoApplication.class
)
@ContextConfiguration
@ActiveProfiles(value = "test")
class DataTablesSampleTest extends Specification {
    def "sample parameterized/data table test"() {
        when:
        def resultSum = input1 + input2

        then:
        resultSum == expectedResult

        where:
        input1 |input2  |expectedResult
        10     |15      |25
        -4     |6       |2
        1      |6       |7
    }

}
