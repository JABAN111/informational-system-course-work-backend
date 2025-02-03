package lab.`is`.bank.services.artifactManagement.impl

import jakarta.persistence.Tuple
import jakarta.transaction.Transactional
import lab.`is`.bank.database.entity.artifact.Artifact
import lab.`is`.bank.database.repository.artifactManagement.ArtifactRepository
import lab.`is`.bank.dto.ArtifactExportData
import lab.`is`.bank.dto.artifact.ArtifactDto
import lab.`is`.bank.mapper.artifact.ArtifactMapper
import lab.`is`.bank.services.exception.ArtifactAlreadySaved
import lab.`is`.bank.services.artifactManagement.interfaces.ArtifactService
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.util.*

@Service
@Transactional
class ArtifactServiceImpl(
    private val artifactRepository: ArtifactRepository
) : ArtifactService {
    override fun save(artifact: Artifact): Artifact {
        if(artifactRepository.findByName(artifact.name) != null) {
            throw ArtifactAlreadySaved("Artifact already in storage")
        }
        return artifactRepository.save(artifact)
    }

    override fun save(artifactDto: ArtifactDto): Artifact {
        if(artifactRepository.findByName(artifactDto.name) != null) {
            throw ArtifactAlreadySaved("Artifact already in storage")
        }
        return save(ArtifactMapper.toEntity(artifactDto))
    }

    override fun getArtifactById(artifactId: UUID): Artifact {
        return artifactRepository.findByUuid(artifactId)
            ?: throw NoSuchElementException("Artifact with id $artifactId not found")
    }

    override fun getDataForExport(someOwner: String?, someMagicProperty: List<String>?): List<ArtifactExportData> {
        val magicPropertiesString = someMagicProperty?.joinToString(",")
        return mapTuplesToArtifactExportData(artifactRepository.getFilteredArtifacts(someOwner, magicPropertiesString))
    }

    private fun mapTuplesToArtifactExportData(tuples: List<Tuple>): List<ArtifactExportData> {
        return tuples.map { tuple ->
            val artifactId = tuple.get("artifact_id", UUID::class.java)
            val artifactName = tuple.get("artifact_name", String::class.java)
            val createdDate = tuple.get("created_date", Timestamp::class.java)
            val ownerPassportId = tuple.get("owner_passport_id", String::class.java)
            val magicalDangerLevel = tuple.get("magical_danger_level", String::class.java)
            val lastChangeDate = tuple.get("last_change_date", Timestamp::class.java)
            val lastReasonToSave = tuple.get("last_reason_to_save", String::class.java)

            ArtifactExportData(
                artifactId = artifactId,
                artifactName = artifactName,
                createdDate = createdDate,
                ownerPassportId = ownerPassportId,
                magicalDangerLevel = magicalDangerLevel,
                lastChangeDate = lastChangeDate,
                lastReasonToSave = lastReasonToSave
            )
        }
    }


}