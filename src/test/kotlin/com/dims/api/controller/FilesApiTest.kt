package com.dims.api.controller

import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class FilesApiTest {

    private val service: FilesApiService = FilesApiServiceImpl()
    private val api: FilesApiController = FilesApiController(service)

    /**
     * To test FilesApiController.deleteFile
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun deleteFileTest() {
        val fileId: java.util.UUID = TODO()
        val response: ResponseEntity<Unit> = api.deleteFile(fileId)

        // TODO: test validations
    }

    /**
     * To test FilesApiController.downloadFile
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun downloadFileTest() {
        val fileId: java.util.UUID = TODO()
        val response: ResponseEntity<org.springframework.core.io.Resource> = api.downloadFile(fileId)

        // TODO: test validations
    }

    /**
     * To test FilesApiController.getFileMetadata
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getFileMetadataTest() {
        val fileId: java.util.UUID = TODO()
        val response: ResponseEntity<java.io.File> = api.getFileMetadata(fileId)

        // TODO: test validations
    }

    /**
     * To test FilesApiController.uploadFile
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun uploadFileTest() {
        val file: org.springframework.web.multipart.MultipartFile? = TODO()
        val response: ResponseEntity<java.io.File> = api.uploadFile(file)

        // TODO: test validations
    }
}
