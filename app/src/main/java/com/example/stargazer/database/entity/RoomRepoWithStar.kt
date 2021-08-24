package com.example.stargazer.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class RoomRepoWithStar (
    @Embedded val repo: RoomRepo,
    @Relation(
        parentColumn = "repo_name",
        entity = RoomStar::class,
        entityColumn = "repo_star"
    )val starList: List<RoomStar>
    )