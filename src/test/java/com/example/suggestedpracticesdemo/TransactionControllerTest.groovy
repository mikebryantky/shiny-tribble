package com.example.suggestedpracticesdemo

import groovy.json.JsonSlurper
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import wslite.rest.RESTClient

@SpringBootTest(
        classes = SuggestedPracticesDemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@ContextConfiguration
@ActiveProfiles(value = "test")
class TransactionControllerTest extends Specification {

    def "when get is called the status is 200 and six transactions are returned"() {
        setup:
        def path = "/api/v1/transaction"
        RESTClient restClient = new RESTClient("http://localhost:8080")

        when:
        def response = restClient.get(path: path)

        then:
        with(response) {
            statusCode == 200
            contentType == "application/json"
        }

        def stringData = response.parsedResponseContent.text
        def json = new JsonSlurper().parseText(stringData)
        with(json) {
            assert json.results.size == 6
        }
    }

    def "when get is called for one id the status is 200 the correct transaction is returned"() {
        setup:
        def path = "/api/v1/transaction/afa75cea-431c-45da-b85f-b5fa3fff8a32"
        RESTClient restClient = new RESTClient("http://localhost:8080")

        when:
        def response = restClient.get(path: path)

        then:
        with(response) {
            statusCode == 200
            contentType == "application/json"
        }

        def stringData = response.parsedResponseContent.text
        def json = new JsonSlurper().parseText(stringData)
        with(json) {
            id == 'afa75cea-431c-45da-b85f-b5fa3fff8a32'
            accountNumber == 'account-123'
            transactionAmount == 1654.43
        }
    }
}

