package com.example.stargazer.database.entity

import androidx.room.*
import com.example.stargazer.model.Stargazer
import com.example.stargazer.model.User
import java.io.Serializable

@Entity(tableName = "star_table",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = RoomRepo::class,
            parentColumns = arrayOf("repo_name"),
            childColumns = arrayOf("repo_star"),
            onDelete = ForeignKey.CASCADE
        )
    ), indices = arrayOf(Index(value = arrayOf("repo_star"),unique = true)))
data class RoomStar (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_star")
    val id: Int = 0,
    @ColumnInfo(name = "repo_star")
    val repoName: String,
    override val starred_at: String,
    @Embedded
    override val user: RoomUser
): Stargazer, Serializable {

    data class RoomUser (
        override val login: String,
        override val id: Int,
        override val avatar_url: String
    ): User, Serializable

}