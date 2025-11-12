package com.dims.api.controller

import com.dims.api.model.File


interface FilesApiService {

    /**
     * DELETE /files/{fileId} : Удалить файл
     *
     * @param fileId  (required)
     * @return Файл удален. (status code 204)
     *         or Файл не найден. (status code 404)
     * @see FilesApi#deleteFile
     */
    fun deleteFile(fileId: java.util.UUID): Unit

    /**
     * GET /files/{fileId}/download : Скачать файл по ID
     *
     * @param fileId  (required)
     * @return Содержимое файла. (status code 200)
     *         or Файл не найден. (status code 404)
     * @see FilesApi#downloadFile
     */
    fun downloadFile(fileId: java.util.UUID): org.springframework.core.io.Resource

    /**
     * GET /files/{fileId} : Получить метаданные файла
     *
     * @param fileId  (required)
     * @return Метаданные файла. (status code 200)
     *         or Файл не найден. (status code 404)
     * @see FilesApi#getFileMetadata
     */
    fun getFileMetadata(fileId: java.util.UUID): File

    /**
     * POST /files : Загрузить новый файл
     *
     * @param file  (optional)
     * @return Файл успешно загружен. (status code 201)
     * @see FilesApi#uploadFile
     */
    fun uploadFile(file: org.springframework.web.multipart.MultipartFile?): File
}
