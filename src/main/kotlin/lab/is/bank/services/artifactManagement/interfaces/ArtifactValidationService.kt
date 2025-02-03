package lab.`is`.bank.services.artifactManagement.interfaces

import lab.`is`.bank.dto.artifact.ArtifactDto

interface ArtifactValidationService {

    fun validateArtifact(artifactName: String, userAccountName: String): Boolean
    fun levelOfDanger(artifactName: String): String

    /**
     * Request for python AI service, return all actual artifact
     */
    fun getAllArtifact(): List<ArtifactDto>

    /**
     * Same as `getAllArtifact()`, but could take specified value of dangerous
     */
    fun getAllArtifact(dangerousLevel: String): List<ArtifactDto>
    fun addBannedWord(word: String): Boolean
    fun addBannedUser(userAccountName: String): Boolean
    fun validateDescription(reasonToSave: String): Boolean
}