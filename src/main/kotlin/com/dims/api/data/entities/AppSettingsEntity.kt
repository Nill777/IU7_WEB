package com.dims.api.data.entities

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "app_settings")
class AppSettingsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    var theme: Int = 0, // 0: System
    var maxFileSize: Int = 10, // 10 MB
    var messageEncryption: Int = 1, // Enabled
    var historyStorage: Int = 30, // 30 days

    // Указываем, что эта сущность связана с UserEntity
    // `mappedBy` говорит, что `UserEntity` является "владельцем" этой связи
    @OneToOne(mappedBy = "appSettings")
    var user: UserEntity? = null
)
