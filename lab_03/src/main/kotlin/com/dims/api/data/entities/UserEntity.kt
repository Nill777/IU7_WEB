package com.dims.api.data.entities

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "users")
class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID? = null,
    @Column(unique = true, nullable = false)
    var username: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var role: String,

    // CascadeType.ALL при изменениях User, то же самое произойдет и с его AppSettings
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "app_settings_id", referencedColumnName = "id") // внешний ключ
    var appSettings: AppSettingsEntity? = null
)
