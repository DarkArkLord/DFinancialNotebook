package dark.andapp.dfinnb.presentaion.extensions

import dark.andapp.dfinnb.domain.entity.NamedEntity

typealias DataN = dark.andapp.dfinnb.data.local.entity.INamedEntity
typealias DataT = dark.andapp.dfinnb.data.local.entity.TransactionEntity

typealias DomainT = dark.andapp.dfinnb.domain.entity.TransactionEntity
typealias DomainNT = dark.andapp.dfinnb.domain.entity.NullableTransactionEntity

fun DataN.toDomain(): NamedEntity {
    return NamedEntity(
        id = this.id,
        name = this.name,
    )
}

fun DataT.toDomain(bankAccount: NamedEntity, category: NamedEntity): DomainT {
    return DomainT(
        id = this.id,
        bank = bankAccount,
        category = category,
        amount = this.amount,
        createdAt = this.createdAt,
        comment = this.comment,
    )
}

fun DomainT.toData(): DataT {
    return DataT(
        id = this.id,
        bankId = this.bank.id,
        categoryId = this.category.id,
        amount = this.amount,
        createdAt = this.createdAt,
        comment = this.comment,
    )
}

fun DomainNT.toDomain(): DomainT {
    return DomainT(
        id = 0,
        bank = this.bank!!,
        category = this.category!!,
        amount = this.amount!!,
        createdAt = this.createdAt!!,
        comment = this.comment,
    )
}