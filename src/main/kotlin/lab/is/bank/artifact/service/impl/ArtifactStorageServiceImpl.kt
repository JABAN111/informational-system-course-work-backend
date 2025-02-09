package lab.`is`.bank.artifact.service.impl

import lab.`is`.bank.artifact.database.entity.ArtifactStorage
import lab.`is`.bank.artifact.database.repository.ArtifactStorageRepository
import lab.`is`.bank.artifact.dto.ArtifactStorageDto
import lab.`is`.bank.artifact.mapper.ArtifactStorageMapper
import lab.`is`.bank.artifact.service.interfaces.ArtifactStorageService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ArtifactStorageServiceImpl(
    private val artifactStorageRepository: ArtifactStorageRepository
) : ArtifactStorageService {
    override fun save(dto: ArtifactStorageDto): ArtifactStorage {
        return save(ArtifactStorageMapper.toEntity(dto))
    }

    override fun save(artifactStorage: ArtifactStorage): ArtifactStorage {
        return artifactStorageRepository.save(artifactStorage)
    }

    override fun getInfo(id: UUID): ArtifactStorage {
        val result = artifactStorageRepository.findByUuid(id)
        println(result)
        if(result != null) {
            return result
        }
        TODO("da")
    }

    override fun getArtifactStorage(id: UUID): ArtifactStorage? {
        return artifactStorageRepository.findByUuid(id)
    }


}