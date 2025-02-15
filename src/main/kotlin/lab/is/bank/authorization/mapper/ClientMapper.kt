package lab.`is`.bank.authorization.mapper

import lab.`is`.bank.authorization.database.entity.Client
import lab.`is`.bank.authorization.dto.ClientDto

class ClientMapper {
    companion object {
        fun toEntity(dto: ClientDto): Client {
            require(dto.passportID.isNotBlank()) { "Passport ID must not be blank" }
            val client =
                Client().apply {
                    this.passportID = dto.passportID
                }
            return client
        }
    }
}
