package com.example.suggestedpracticesdemo

import com.example.suggestedpracticesdemo.data.dto.TransactionDto
import com.example.suggestedpracticesdemo.data.model.Transaction
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.time.Instant

@SpringBootTest(
        classes = SuggestedPracticesDemoApplication.class
)
@ContextConfiguration
@ActiveProfiles(value = "test")
class EntityToDtoMappingTests extends Specification {

    def "when mapping entity to DTO the mapping works"() {
        setup:
        def entity = new Transaction()
        entity.setId("ID")
        entity.setAccountNumber("AccountNumber")
        entity.setTransactionAmount(new BigDecimal("999.99"))
        entity.setCreateDate(Instant.MIN)
        entity.setTransactionDate(Instant.MAX)

        when:
        def dto = TransactionDto.fromEntity(entity)

        then:
        with(dto) {
            id == entity.id
            accountNumber == entity.accountNumber
            createDate == entity.createDate
            transactionDate == entity.transactionDate
            transactionAmount == entity.transactionAmount
        }
    }

}
