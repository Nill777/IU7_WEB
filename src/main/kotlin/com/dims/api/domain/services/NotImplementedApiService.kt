package com.dims.api.domain.services
//
//import com.dims.api.controller.*
//import com.dims.api.model.*
//import org.springframework.http.HttpStatus
//import org.springframework.stereotype.Service
//import org.springframework.web.server.ResponseStatusException
//import java.util.UUID
//
///**
// * Этот сервис-заглушка реализует все недостающие API-сервисы.
// * Он позволяет приложению запуститься.
// * Все его методы просто возвращают ошибку 501 Not Implemented.
// */
//@Service
//class NotImplementedApiService :
//    BlockingApiService,
//    ChatsApiService,
//    FilesApiService,
//    MessagesApiService,
//    SettingsApiService {

//    private fun notImplemented(): Nothing = throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Method not implemented yet")

//    // --- BlockingApiService ---
//    override fun blockUser(userId: UUID, blockCreate: BlockCreate): Block = notImplemented()
//    override fun getBlockedUsers(userId: UUID, page: Int, limit: Int): PaginatedBlocks = notImplemented()
//    override fun unblockUser(userId: UUID, blockedUserId: UUID) = notImplemented()

//    // --- ChatsApiService ---
//    override fun createChat(chatCreate: ChatCreate): Chat = notImplemented()
//    override fun deleteChat(chatId: UUID) = notImplemented()
//    override fun getChatById(chatId: UUID): Chat = notImplemented()
//    override fun getChats(userId: UUID, page: Int, limit: Int): PaginatedChats = notImplemented()
//    override fun updateChat(chatId: UUID, chatUpdate: ChatUpdate?): Chat = notImplemented()

//    // --- FilesApiService ---
//    override fun deleteFile(fileId: UUID) = notImplemented()
//    override fun downloadFile(fileId: UUID): org.springframework.core.io.Resource = notImplemented()
//    override fun getFileMetadata(fileId: UUID): File = notImplemented()
//    override fun uploadFile(file: org.springframework.web.multipart.MultipartFile?): File = notImplemented()

//    // --- MessagesApiService ---
//    override fun deleteMessage(chatId: UUID, messageId: UUID) = notImplemented()
//    override fun editMessage(chatId: UUID, messageId: UUID, messageUpdate: MessageUpdate): Message = notImplemented()
//    override fun getChatMessages(chatId: UUID, limit: Int, beforeTimestamp: java.time.OffsetDateTime?): PaginatedMessages = notImplemented()
//    override fun getMessageById(chatId: UUID, messageId: UUID): Message = notImplemented()
//    override fun getMessageHistory(chatId: UUID, messageId: UUID): List<MessageHistory> = notImplemented()
//    override fun sendMessage(chatId: UUID, messageCreate: MessageCreate): Message = notImplemented()

//    // --- SettingsApiService ---
//    override fun getAppSettings(userId: UUID): AppSettings = notImplemented()
//    override fun updateAppSettings(userId: UUID, appSettings: AppSettings?): AppSettings = notImplemented()
//}
