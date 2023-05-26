package com.example.suggestedpracticesdemo

import com.example.suggestedpracticesdemo.data.dto.TransactionDto
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
class DtoToEntityMappingTests extends Specification {

    def "when mapping DTO to entity the mapping works"() {
        setup:
        def dto = new TransactionDto()
        dto.setId("ID")
        dto.setAccountNumber("AccountNumber")
        dto.setTransactionAmount(new BigDecimal("999.99"))
        dto.setCreateDate(Instant.MIN)
        dto.setTransactionDate(Instant.MAX)

        when:
        def entity = TransactionDto.toEntity(dto)

        then:
        with(entity) {
            id == dto.id
            accountNumber == dto.accountNumber
            createDate == dto.createDate
            transactionDate == dto.transactionDate
            transactionAmount == dto.transactionAmount
        }
    }

}
