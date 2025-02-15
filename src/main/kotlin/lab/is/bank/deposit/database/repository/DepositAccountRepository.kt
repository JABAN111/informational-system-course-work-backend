package lab.`is`.bank.deposit.database.repository

import lab.`is`.bank.deposit.database.entity.DepositAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.UUID

@Repository
interface DepositAccountRepository : JpaRepository<DepositAccount, UUID> {
    fun findDepositAccountsByOwnerPassportID(ownerPassportId: String): List<DepositAccount>

    @Query(nativeQuery = true, value = "SELECT transfer_deposit_accounts(:fromAccountId, :toAccountId, :amount)")
    fun transfer(
        fromAccountId: UUID,
        toAccountId: UUID,
        amount: BigDecimal,
    ): String
}
