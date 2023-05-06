package com.example.cinema.mapper.utils

interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(domainModel: DomainModel): Entity

    fun fromEntityList(initial: List<Entity>): List<DomainModel> {
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<DomainModel>): List<Entity> {
        return initial.map { mapToEntity(it) }
    }
}